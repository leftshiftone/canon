package canon.parser.xml.validation

import canon.parser.xml.upgrade.xslt.XSLTTransformSupport
import org.xml.sax.SAXException
import java.io.InputStream
import java.nio.charset.StandardCharsets

open class CanonXmlValidator {

    companion object {
        // XMLValidator is not thread safe but resource intensive instances
        private val VALIDATOR =  ThreadLocal.withInitial {
            XmlValidator(CanonXmlValidator::class.java.getResourceAsStream("/xml/canon.xsd"))
        }

        @JvmStatic
        fun getValidator() = VALIDATOR.get()!!
    }

    /**
     * Provides the canon version used in the validation
     * @return version of canon
     */
    fun getVersion(): String {
        return XSLTTransformSupport.getCanonVersion()
    }

    /**
     * Validates the given input stream.
     *
     * @param stream the input stream
     * @return boolean
     */
    fun validate(stream: InputStream):XmlValidation {
        return getValidator().validate(stream)
    }

    /**
     * Validates the given markup text.
     *
     * @param markup the markup text
     * @return boolean
     */
    fun validate(markup: String) = validate(markup.byteInputStream(StandardCharsets.UTF_8))


}
