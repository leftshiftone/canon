package canon.parser.xml.xlst

import canon.parser.xml.xlst.SemanticVersion.Companion.isValidVersion
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class TransformationManagement  (val relativePath : String){

    val log = LoggerFactory.getLogger(this::class.java)

    fun buildTransformationPath(currentVersion: String): TransformerIterator? {
        log.debug("Build transformation path for version $currentVersion")
        val curVersion = SemanticVersion(currentVersion)
        val transformations = extractTransformations()
        log.debug("Transformations found for $currentVersion : ${transformations.joinToString(",")}")
        val stack  = Stack<CanonXlstTransformer>()
        transformations
                .filter {it > curVersion}
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

    fun transform(rawXml : String, rawXmlVersion: String?): String{
        val xmlVersion = rawXmlVersion ?: "1.9.9"
        val transformerIterator = buildTransformationPath(xmlVersion)
        if(transformerIterator==null || !transformerIterator.hasNext()){
            return rawXml
        }
        return transform(transformerIterator,rawXml)
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
                .map { version -> SemanticVersion(version)}
        }

    @Throws(IOException::class)
    fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use{
        return if(it == null) emptyList()
        else BufferedReader(InputStreamReader(it)).readLines()
    }

    private fun getResourceAsStream(resource: String): InputStream? = this::class.java.getResourceAsStream(resource)

}