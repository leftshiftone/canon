package canon.parser.xml.upgrade.xslt

import canon.parser.xml.upgrade.CanonUpgradeHandler
import canon.parser.xml.upgrade.SemanticVersion
import canon.parser.xml.upgrade.xslt.XSLTTransformSupport.Companion.getCanonVersion
import canon.parser.xml.upgrade.xslt.XSLTTransformerConfiguration.Companion.isValidTransformerPath
import org.slf4j.LoggerFactory


class XSLTUpgradeHandler : CanonUpgradeHandler {

    companion object {
        const val DEFAULT_VERSION = "1.9.0"
    }

    private val log = LoggerFactory.getLogger(this::class.java)
    private val transformerConfig: List<XSLTTransformerConfiguration>
    private val upgradeLogTransformer: XSLTTransformer?

    constructor(xsltSheets: List<String>) {
        this.transformerConfig = xsltSheets.filter { isValidTransformerPath(it) }.map { sheet -> XSLTTransformerConfiguration(sheet) }.toList()
        val zeroTransformer = this.transformerConfig.firstOrNull { it.version == SemanticVersion("0", "0", "0") }
        upgradeLogTransformer = zeroTransformer?.let { XSLTTransformer(it) }
    }

    constructor() : this(XSLTTransformSupport.getDefaultTransformers())

    override fun getLatestVersion() = getCanonVersion()

    override fun isUpgradeRequired(version: String?): Boolean {
        if (version == null) return true
        log.debug("Check if there are transformations for version: $version")
        val requiredTransformations = retrieveAvailableTransformationsForAVersion(version)
        val numberOfRequiredTransformations = requiredTransformations.count()
        log.debug("$numberOfRequiredTransformations transformations were found for version $version")
        return numberOfRequiredTransformations > 0
    }

    override fun upgrade(rawXml: String?, rawXmlVersion: String?): String {
        if (rawXml == null || !isUpgradeRequired(rawXmlVersion)) {
            return rawXml ?: ""
        }
        val transformers = buildTransformers(getVersion(rawXmlVersion))
        return transform(transformers, rawXml)
    }

    override fun upgrade(utterance: Map<String, List<String>>?, rawXmlVersion: String?): Map<String, List<String>> {
        if (utterance == null || !isUpgradeRequired(rawXmlVersion)) {
            return utterance ?: mapOf()
        }
        return upgradeUtterance(utterance, getVersion(rawXmlVersion), mapOf())
    }

    private fun getVersion(version: String?) = version ?: DEFAULT_VERSION

    private fun upgradeUtterance(utterance: Map<String, List<String>>, rawXmlVersion: String, result: Map<String, List<String>>): Map<String, List<String>> {
        utterance.entries.map { utteranceEntry ->
            val transformedListOfUtterances = utteranceEntry.value
                    .map { utteranceValue ->
                        transform(buildTransformers(rawXmlVersion), utteranceValue)
                    }.toList()

            return upgradeUtterance(utterance.minus(utteranceEntry.key), rawXmlVersion, result.plus(utteranceEntry.key to transformedListOfUtterances))
        }
        return result
    }


    fun buildTransformers(currentVersion: String): List<XSLTTransformer> {
        log.debug("Build transformation path for version $currentVersion")
        val transformations = retrieveAvailableTransformationsForAVersion(currentVersion)
        return transformations
                .map {
                    XSLTTransformer(it)
                }
                .toList()
    }

    private fun retrieveAvailableTransformationsForAVersion(currentVersion: String): List<XSLTTransformerConfiguration> {
        log.debug("Retrieve available transformations for version: $currentVersion")
        val curVersion = SemanticVersion(currentVersion)
        val allAvailableTransformations = this.transformerConfig.map { config -> config.version }.toList()
        log.debug("AvailableTransformations : ${allAvailableTransformations.joinToString(",")}")
        val transformationForGivenVersion = this.transformerConfig
                .filter { it.version > curVersion }
        log.debug("AvailableTransformations for $currentVersion : ${allAvailableTransformations.joinToString(",")}")
        return transformationForGivenVersion

    }


    private fun transform(transformers: List<XSLTTransformer>, initialXml: String): String {
        return transformers.sortedByDescending { it.config.version }
                .fold(initialXml) { xmlToUpgrade, it ->
                    it.execute(xmlToUpgrade)
                }.let { upgradeLogTransformer?.execute(it) ?: it }
    }

}