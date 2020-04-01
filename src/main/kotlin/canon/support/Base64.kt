package canon.support

import canon.exception.CanonException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

object Base64 {

    fun encode(obj: Any): String {
        try {
            val bytes = ObjectMapper().writeValueAsBytes(obj)
            val encoded = org.apache.commons.codec.binary.Base64.encodeBase64(bytes)
            return String(encoded)
        } catch (e: JsonProcessingException) {
            throw CanonException(e)
        }
    }

    fun encodeUTF8String(string: String?): String? {
        val bytes = string!!.toByteArray(Charsets.UTF_8)
        val encoded = org.apache.commons.codec.binary.Base64.encodeBase64(bytes)
        return String(encoded)
    }

    fun decode(encoded: String?): String? {
        if (encoded == null) {
            throw CanonException("String mustn't be null")
        }

        val decoded = org.apache.commons.codec.binary.Base64.decodeBase64(encoded.toByteArray());
        return String(decoded)
    }
}