package canon.parser.xml.validation

import org.apache.commons.lang3.StringUtils
import org.xml.sax.SAXException

interface XmlValidation {

    class Success : XmlValidation

    class Failure(val ex: SAXException) : XmlValidation {
        fun getErrorMessage(): String {
            return StringUtils.trim(StringUtils.substringBetween(ex.message, ":", ".") ?: ex.message)
        }
    }

}
