package canon.parser.xml.upgrade.xslt

import org.slf4j.LoggerFactory
import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 *  This class transforms a XML String with a xslt file. The location of the xslt file will be determined by its configuration
 *  provided in the constructor
 */
class XSLTTransformer(val config: XSLTTransformerConfiguration) {

    companion object {
        const val CLEAN_UP_XSLT = "/xml/xslt/transformers/cleanup/transform_cleanup.xslt"
    }

    private val log = LoggerFactory.getLogger(this::class.java)

    fun execute(xml: String): String {
        val markupXml = "<markup><container>$xml</container></markup>"
        val transformedXML = applyXSLT(markupXml, config.transformerLocation)
        val normalizedTransformedXML = applyXSLT(transformedXML, CLEAN_UP_XSLT)
        log.debug("Transformed xml: \nfrom $markupXml \nto${normalizedTransformedXML}")
        return normalizedTransformedXML
    }

    private fun applyXSLT(xml: String, xslt: String): String {
        val factory = TransformerFactory.newInstance()
        log.debug("Loading xslt transform file  $xslt")
        val streamSource = StreamSource(XSLTTransformer::class.java.getResourceAsStream(xslt))
        val transformer = factory.newTransformer(streamSource)
        log.debug("Xml to transformed: $xml")
        val text = StreamSource(StringReader(xml))
        val transformedXML = StringWriter()
        transformer.transform(text, StreamResult(transformedXML))
        log.debug("Transformed xml: \nfrom $xml \nto${transformedXML}")
        return transformedXML.toString()
    }

}