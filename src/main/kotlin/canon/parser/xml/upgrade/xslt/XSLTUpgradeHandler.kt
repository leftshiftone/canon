package canon.parser.xml.upgrade.xslt

import canon.parser.xml.upgrade.CanonUpgradeHandler
import canon.parser.xml.upgrade.SemanticVersion
import canon.parser.xml.upgrade.xslt.XSLTTransformSupport.Companion.getCanonVersion
import canon.parser.xml.upgrade.xslt.XSLTTransformerConfiguration.Companion.isValidTransformerPath
import org.slf4j.LoggerFactory

class XSLTUpgradeHandler : CanonUpgradeHandler {

    companion object {
        @Suppress("unused")
        const val MANUAL_ACTION_COMMENT = "<!--manual action needed-->"
        const val DEFAULT_VERSION = "1.9.0"
        private val log = LoggerFactory.getLogger(XSLTUpgradeHandler::class.java)
    }

    private val transformerConfig: List<XSLTTransformerConfiguration>
    private val upgradeLogTransformer: XSLTTransformer?
    private val transformerCache: TransformerCache


    constructor(xsltSheets: List<String>) {
        this.transformerConfig = xsltSheets.filter { isValidTransformerPath(it) }.map { sheet -> XSLTTransformerConfiguration(sheet) }.toList()
        val zeroTransformer = this.transformerConfig.firstOrNull { it.version == SemanticVersion("0", "0", "0") }
        upgradeLogTransformer = zeroTransformer?.let { XSLTTransformer(it) }
        transformerCache = TransformerCache(transformerConfig)
    }

    constructor() : this(XSLTTransformSupport.getDefaultTransformers())

    override fun getLatestVersion() = getCanonVersion()

    override fun isUpgradeRequired(version: String?): Boolean {
        if (version == null) return true
        log.debug("Check if there are transformations for version: $version")
        val requiredTransformations = transformerCache.retrieveAvailableTransformationsForAVersion(version)
        val numberOfRequiredTransformations = requiredTransformations.count()
        log.debug("$numberOfRequiredTransformations transformations were found for version $version")
        return numberOfRequiredTransformations > 0
    }

    override fun upgrade(rawXml: String?, rawXmlVersion: String?): String {
        if (rawXml == null || !isUpgradeRequired(rawXmlVersion)) {
            return rawXml ?: ""
        }
        val transformers = transformersForVersion(getVersion(rawXmlVersion))
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
        val transformers = transformersForVersion(rawXmlVersion)
        utterance.entries.map { utteranceEntry ->
            val transformedListOfUtterances = utteranceEntry.value
                .map { utteranceValue -> transform(transformers, utteranceValue) }
                .toList()

            return upgradeUtterance(utterance.minus(utteranceEntry.key), rawXmlVersion, result.plus(utteranceEntry.key to transformedListOfUtterances))
        }

        return result
    }

    private fun transform(transformers: List<XSLTTransformer>, initialXml: String): String {
        return transformers.sortedByDescending { it.config.version }
                .fold(initialXml) { xmlToUpgrade, it ->
                    it.execute(xmlToUpgrade)
                }.let { upgradeLogTransformer?.execute(it) ?: it }
    }

    fun transformersForVersion(version: String): List<XSLTTransformer> {
        return transformerCache.transformersForVersion(version)
    }

}