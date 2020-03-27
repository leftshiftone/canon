package canon.parser.xml.xlst

import canon.parser.xml.xlst.SemanticVersion.Companion.isValidVersion
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*


class TransformationManagement  (val relativePath : String){

    fun buildTransformationPath(currentVersion: String): TransformerIterator? {
        val curVersion = SemanticVersion(currentVersion)
        val transformations = extractTransformations()
        val stack  = Stack<CanonXlstTransformer>()
        transformations
                .filter {it > curVersion}
                .sortedDescending()
                .map {
                    CanonXlstTransformer(relativePath,it)}
                .forEach {stack.push(it)}
        return if(stack.isNullOrEmpty()) return null else TransformerIterator(stack)
    }

    fun transform(rawXml : String, rawXmlVersion: String): String{
        val transformerIterator = buildTransformationPath(rawXmlVersion)
        if(transformerIterator==null || !transformerIterator.hasNext()){
            return rawXml
        }
        return transform(transformerIterator,rawXml)
    }

    private fun transform(iterator : TransformerIterator, xml: String): String{
        while(iterator.hasNext()){
            val transformer = iterator.next()
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