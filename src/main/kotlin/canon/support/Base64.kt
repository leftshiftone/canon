package canon.support

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper

object Base64 {

    fun encode(obj: Any): String {
        try {
            val bytes = ObjectMapper().writeValueAsBytes(obj)
            val encoded = org.apache.commons.codec.binary.Base64.encodeBase64(bytes)

            return String(encoded)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }

    }

    fun encodeUTF8String(string: String?): String? {
        val bytes = string!!.toByteArray(Charsets.UTF_8)
        val encoded = org.apache.commons.codec.binary.Base64.encodeBase64(bytes)
        return String(encoded)
    }

    fun decode(encoded: String?): String? {
        if (encoded == null) {
            throw RuntimeException("String mustn't be null")
        }

        val decoded = org.apache.commons.codec.binary.Base64.decodeBase64(encoded.toByteArray());
        return String(decoded)
    }

    fun decode(obj: Any): String {
        try {
            val bytes = ObjectMapper().writeValueAsBytes(obj)
            val encoded = java.util.Base64.getDecoder().decode(bytes)

            return String(encoded)
        } catch (e: JsonProcessingException) {
            throw RuntimeException(e)
        }

    }
}