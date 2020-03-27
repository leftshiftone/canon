package canon.parser.xml.xlst

import org.slf4j.LoggerFactory
import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource


class CanonXlstTransformer (val relativePath : String, val version: SemanticVersion){

    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute(xml: String): String {

        val factory = TransformerFactory.newInstance()
        log.debug("Loading XLST transform file  $relativePath/transform_$version.xlst")
        val xslt = StreamSource(CanonXlstTransformer::class.java.getResourceAsStream("$relativePath/transform_$version.xlst"))
        val transformer = factory.newTransformer(xslt)
        log.debug("Xml to transformed: $xml")
        val text = StreamSource(StringReader(xml))
        val transformedXML = StringWriter()
        transformer.transform(text, StreamResult(transformedXML))
        log.debug("Transformed xml: \nfrom $xml \nto${transformedXML}")
        return transformedXML.toString()
    }


}