package canon.parser.xml.upgrade.xslt

import canon.parser.xml.upgrade.CanonUpgradeHandler
import canon.parser.xml.upgrade.SemanticVersion
import canon.parser.xml.upgrade.TransformerIterator
import canon.parser.xml.upgrade.xslt.XSLTTransformSupport.Companion.getCanonVersion
import canon.parser.xml.upgrade.xslt.XSLTTransformerConfiguration.Companion.isValidTransformerPath
import org.slf4j.LoggerFactory
import java.util.*


class XSLTUpgradeHandler: CanonUpgradeHandler {

    val log = LoggerFactory.getLogger(this::class.java)
    val transformerConfig : List<XSLTTransformerConfiguration>
    val UPGRADE_LOG_TRANSFORMER : XSLTTransformer?
    val DEFAULT_VERSION ="1.9.0"


    constructor(xsltSheets : List<String>) {
        this.transformerConfig= xsltSheets.filter { isValidTransformerPath(it) }.map { sheet -> XSLTTransformerConfiguration(sheet) }.toList()
        val zeroTransformer= this.transformerConfig.filter { config -> config.version== SemanticVersion("0","0","0")  }.firstOrNull()
        UPGRADE_LOG_TRANSFORMER= if(zeroTransformer!=null) XSLTTransformer(zeroTransformer) else null
    }

    constructor() : this(XSLTTransformSupport.getDefaultTransformers())

    override fun getLatestVersion() = getCanonVersion()

    override fun isUpgradeRequired(version: String?): Boolean {
        if(version==null) return true
        log.debug("Check if there are transformations for version: $version")
        val requiredTransformations = retrieveAvailableTransformationsForAVersion(version)
        val numberOfRequiredTransformations = requiredTransformations.count()
        log.debug("$numberOfRequiredTransformations transformations were found for version $version")
        return numberOfRequiredTransformations>0
    }

    override fun upgrade(rawXml : String, rawXmlVersion: String?): String{
        if(!isUpgradeRequired(rawXmlVersion)){
            return rawXml
        }
        val transformerIterator = buildTransformerIterator(getVersion(rawXmlVersion))
        return transform(transformerIterator!!,rawXml)
    }

    override fun upgrade(utterance : Map<String, List<String>>, rawXmlVersion: String?): Map<String, List<String>>{
        if(!isUpgradeRequired(rawXmlVersion)){
            return utterance
        }
        return upgradeUtterance(utterance,getVersion(rawXmlVersion), mapOf())
    }

    private fun getVersion(version : String?) = version ?: DEFAULT_VERSION

    private fun upgradeUtterance(utterance : Map<String, List<String>>, rawXmlVersion: String, result:Map<String, List<String>>): Map<String, List<String>>{
        utterance.entries.map { utteranceEntry ->
            val transformedListOfUtterances = utteranceEntry.value
                    .map { utteranceValue ->
                        transform(buildTransformerIterator(rawXmlVersion)!!, utteranceValue)
                    }.toList()

            return upgradeUtterance(utterance.minus(utteranceEntry.key), rawXmlVersion ,result.plus(utteranceEntry.key to transformedListOfUtterances))
        }
        return result
    }


    fun buildTransformerIterator(currentVersion: String): TransformerIterator<XSLTTransformer>? {
        log.debug("Build transformation path for version $currentVersion")
        val transformations = retrieveAvailableTransformationsForAVersion(currentVersion)
        val stack  = Stack<XSLTTransformer>()
        transformations
                .sortedDescending()
                .map {
                    XSLTTransformer(it)
                }
                .forEach {stack.push(it)}
        return if(stack.isNullOrEmpty()) {
            log.debug("No transformations found for version $currentVersion")
            return null
        } else {
            log.debug("${stack.size} Transformation found for version $currentVersion")
            TransformerIterator(stack)
        }
    }

    private fun retrieveAvailableTransformationsForAVersion(currentVersion: String):  List<XSLTTransformerConfiguration> {
        log.debug("Retrieve available transformations for version: $currentVersion")
        val curVersion = SemanticVersion(currentVersion)
        val allAvailableTransformations=this.transformerConfig.map { config-> config.version }.toList()
        log.debug("AvailableTransformations : ${allAvailableTransformations.joinToString(",")}")
        val transformationForGivenVersion= this.transformerConfig
                .filter {it.version > curVersion}
        log.debug("AvailableTransformations for $currentVersion : ${allAvailableTransformations.joinToString(",")}")
        return transformationForGivenVersion

    }


    private fun transform(iterator : TransformerIterator<XSLTTransformer>, xml: String): String{
        while(iterator.hasNext()){
            val transformer = iterator.next()
            log.debug("Applying transformation for version ${transformer.config}")
            return transform(iterator,transformer.execute(xml))
        }
        return UPGRADE_LOG_TRANSFORMER?.execute(xml) ?: return xml
    }


}