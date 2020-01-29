package canon.parser.xml.validation

import org.apache.commons.lang3.StringUtils
import org.xml.sax.SAXException

interface XmlValidation {

    class Success : XmlValidation

    class Failure(val ex: SAXException) : XmlValidation {
        fun getMessage(): String {
            val msg = ex.message ?: ex.javaClass.simpleName
            if (msg.startsWith("Referenz zu Entity") && msg.endsWith("muss mit dem Begrenzungszeichen \";\" enden."))
                return "special xml characters (e.g. <>&) must be escaped"

            return StringUtils.trim(StringUtils.substringBetween(msg, ":", ".") ?: msg)
        }
    }

}
