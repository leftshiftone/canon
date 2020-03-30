package canon.parser.xml.upgrade.xlst

import canon.parser.xml.upgrade.SemanticVersion
import canon.parser.xml.upgrade.SemanticVersion.Companion.isValidVersion
import canon.parser.xml.upgrade.UpgradeHandler
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class XLSTUpgradeHandler  (val relativePath : String) : UpgradeHandler {

    val log = LoggerFactory.getLogger(this::class.java)

    override fun isUpgradeRequired(version: String): Boolean {
        log.debug("Check if there are transformations for version: $version")
        val requiredTransformations = retrieveAvailableTransformationsForAVersion(version)
        val numberOfRequiredTransformations = requiredTransformations?.count()
        log.debug("$numberOfRequiredTransformations transformations were found for version $version")
        return numberOfRequiredTransformations>0
    }

    override fun upgrade(rawXml : String, rawXmlVersion: String): String{
        if(!isUpgradeRequired(rawXmlVersion)){
            return rawXml
        }
        val transformerIterator = buildTransformerIterator(rawXmlVersion)
        if(transformerIterator==null || !transformerIterator.hasNext()){
            return rawXml
        }
        return transform(transformerIterator,rawXml)
    }


    fun buildTransformerIterator(currentVersion: String): TransformerIterator? {
        log.debug("Build transformation path for version $currentVersion")
        val transformations = retrieveAvailableTransformationsForAVersion(currentVersion)
        val stack  = Stack<CanonXlstTransformer>()
        transformations
                .sortedDescending()
                .map {
                    CanonXlstTransformer(relativePath,it)}
                .forEach {stack.push(it)}
        return if(stack.isNullOrEmpty()) {
            log.debug("No transformations found for version $currentVersion")
            return null
        } else {
            log.debug("${stack.size} Transformation found for version $currentVersion")
            TransformerIterator(stack)
        }
    }

    private fun retrieveAvailableTransformationsForAVersion(currentVersion: String):  List<SemanticVersion> {
        log.debug("Retrieve available transformations for version: $currentVersion")
        val curVersion = SemanticVersion(currentVersion)
        val allAvailableTransformations = extractTransformations()
        log.debug("AvailableTransformations : ${allAvailableTransformations.joinToString(",")}")
        val transformationForGivenVersion= allAvailableTransformations
                .filter {it > curVersion}
        log.debug("AvailableTransformations for $currentVersion : ${allAvailableTransformations.joinToString(",")}")
        return transformationForGivenVersion

    }


    private fun transform(iterator : TransformerIterator, xml: String): String{
        while(iterator.hasNext()){
            val transformer = iterator.next()
            log.debug("Applying transformation for version ${transformer.version} from path ${transformer.relativePath}")
            return transform(iterator,transformer.execute(xml))
        }
        return xml
    }

    private fun extractTransformations(): List<SemanticVersion> {
        return getResourceFiles(relativePath)
                .map { fileName -> fileName.substringAfter("transform_").substringBefore(".xlst")  }
                .filter { extractedVersionFromFile -> isValidVersion(extractedVersionFromFile) }
                .map { version -> SemanticVersion(version) }
        }

    @Throws(IOException::class)
    fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use{
        return if(it == null) emptyList()
        else BufferedReader(InputStreamReader(it)).readLines()
    }

    private fun getResourceAsStream(resource: String): InputStream? = this::class.java.getResourceAsStream(resource)

}