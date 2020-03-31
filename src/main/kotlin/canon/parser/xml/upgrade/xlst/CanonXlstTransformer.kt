package canon.parser.xml.upgrade.xlst

import canon.parser.xml.upgrade.SemanticVersion
import org.slf4j.LoggerFactory
import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 *  This class transforms a XML String with a XLST file. The location of the XLST file will be determined by the relativePath and version
 *  provided in the constructor
 */
class CanonXlstTransformer (val relativePath : String, val version: SemanticVersion){

    private val log = LoggerFactory.getLogger(this::class.java)
    val CLEAN_UP_XLST= "/xml/xlst/transformers/cleanup/transform_cleanup.xlst"

    fun execute(xml: String): String {
        val markupXml= "<markup><container>$xml</container></markup>"
        val transformedXML= applyXSLT(markupXml,"$relativePath/transform_$version.xlst")
        val normalizedTransformedXML= applyXSLT(transformedXML,CLEAN_UP_XLST)
        log.debug("Transformed xml: \nfrom $markupXml \nto${normalizedTransformedXML}")
        return normalizedTransformedXML
    }

    private fun applyXSLT(xml: String, xlst: String): String {
        val factory = TransformerFactory.newInstance()
        log.debug("Loading XLST transform file  $xlst")
        val xslt = StreamSource(CanonXlstTransformer::class.java.getResourceAsStream("$xlst"))
        val transformer = factory.newTransformer(xslt)
        log.debug("Xml to transformed: $xml")
        val text = StreamSource(StringReader(xml))
        val transformedXML = StringWriter()
        transformer.transform(text, StreamResult(transformedXML))
        log.debug("Transformed xml: \nfrom $xml \nto${transformedXML}")
        return transformedXML.toString()
    }


}