package canon.parser.xml.upgrade.xslt

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl
import org.slf4j.LoggerFactory
import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 *  This class transforms a XML String with a xslt file. The location of the xslt file will be determined by its configuration
 *  provided in the constructor
 */
object XSLTTransformerFactoryProvider {
    // use the standard java transformer factory
    // to avoid any encoding or other issues
    // see: [XSLTUpgradeHandlerTest]
    val factory = TransformerFactoryImpl()
}

class XSLTTransformer(val config: XSLTTransformerConfiguration) {

    companion object {
        private val log = LoggerFactory.getLogger(XSLTTransformer::class.java)
        private const val CLEAN_UP_XSLT = "/xml/xslt/transformers/cleanup/transform_cleanup.xslt"
    }

    fun execute(xml: String): String {
        val markupXml = "<markup><container>$xml</container></markup>"
        val transformedXML = applyXSLT(markupXml, false)
        val normalizedTransformedXML = applyXSLT(transformedXML, true)
        log.trace("Transformed xml: \nfrom $markupXml \nto${normalizedTransformedXML}")
        return normalizedTransformedXML
    }

    private fun applyXSLT(xml: String, useCleanupTransformer: Boolean): String {
        val transformer = if (useCleanupTransformer) {
            val streamSource = StreamSource(XSLTTransformer::class.java.getResourceAsStream(CLEAN_UP_XSLT))
            XSLTTransformerFactoryProvider.factory.newTransformer(streamSource)
        } else {
            val streamSource = StreamSource(XSLTTransformer::class.java.getResourceAsStream(config.transformerLocation))
            XSLTTransformerFactoryProvider.factory.newTransformer(streamSource)
        }
        log.trace("Xml to transformed: $xml")
        val text = StreamSource(StringReader(xml))
        val transformedXML = StringWriter()
        transformer.transform(text, StreamResult(transformedXML))
        log.trace("Transformed xml: \nfrom $xml \nto${transformedXML}")
        return transformedXML.toString()
    }
}
