package canon.parser.xml.xlst

import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource


class CanonXlstTransformer (val relativePath : String, val version: SemanticVersion){


    fun execute(xml: String): String {

        val factory = TransformerFactory.newInstance()
        val xslt = StreamSource(CanonXlstTransformer::class.java.getResourceAsStream("$relativePath/transform_$version.xlst"))
        val transformer = factory.newTransformer(xslt)

        val text = StreamSource(StringReader(xml))
        val transformedXML = StringWriter()
        transformer.transform(text, StreamResult(transformedXML))
        return transformedXML.toString()
    }


}