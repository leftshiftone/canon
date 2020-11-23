package canon.parser.xml.upgrade.xslt

import org.slf4j.LoggerFactory
import java.io.StringReader
import java.io.StringWriter
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerFactory
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.stream.StreamSource

/**
 *  This class transforms a XML String with a xslt file. The location of the xslt file will be determined by its configuration
 *  provided in the constructor
 */
class XSLTTransformer(val config: XSLTTransformerConfiguration) {

    companion object {
        private val log = LoggerFactory.getLogger(XSLTTransformer::class.java)
        private const val CLEAN_UP_XSLT = "/xml/xslt/transformers/cleanup/transform_cleanup.xslt"
        private val JDK8_DEFAULT_TRANSFORMER_FACTORY = "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl"
    }

    private val cleanupTransformer: Transformer by lazy {
        val factory = TransformerFactory.newInstance(JDK8_DEFAULT_TRANSFORMER_FACTORY, Thread.currentThread().contextClassLoader)
        val streamSource = StreamSource(XSLTTransformer::class.java.getResourceAsStream(CLEAN_UP_XSLT))
        factory.newTransformer(streamSource)
    }
    private val transformer: Transformer by lazy {
        val factory = TransformerFactory.newInstance(JDK8_DEFAULT_TRANSFORMER_FACTORY, Thread.currentThread().contextClassLoader)
        val streamSource = StreamSource(XSLTTransformer::class.java.getResourceAsStream(config.transformerLocation))
        factory.newTransformer(streamSource)
    }

    fun execute(xml: String): String {
        val markupXml = "<markup><container>$xml</container></markup>"
        val transformedXML = applyXSLT(markupXml, false)
        val normalizedTransformedXML = applyXSLT(transformedXML, true)
        log.debug("Transformed xml: \nfrom $markupXml \nto${normalizedTransformedXML}")
        return normalizedTransformedXML
    }

    private fun applyXSLT(xml: String, useCleanupTransformer: Boolean): String {
        val transformer = if (useCleanupTransformer) {
            cleanupTransformer
        } else {
            transformer
        }
        log.debug("Xml to transformed: $xml")
        val text = StreamSource(StringReader(xml))
        val transformedXML = StringWriter()
        transformer.transform(text, StreamResult(transformedXML))
        log.debug("Transformed xml: \nfrom $xml \nto${transformedXML}")
        return transformedXML.toString()
    }
}
