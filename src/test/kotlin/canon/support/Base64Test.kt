package canon.support

import canon.exception.CanonException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class Base64Test {

    private val testString = "TestString"
    private val encodedTestString = "IlRlc3RTdHJpbmci"
    private val byteArrayEncoded = "VGVzdFN0cmluZw=="

    @Test
    fun testEncode() {
        assertThat(Base64.encode(testString)).isEqualTo(encodedTestString)
    }

    @Test
    fun testEncodeUTF8String() {
        val encodeUTF8String = Base64.encodeUTF8String(testString)
        assertThat(encodeUTF8String).isNotNull().isEqualTo(byteArrayEncoded)
    }

    @Test
    fun testEncodeUTF8StringNull() {
        assertThatThrownBy { Base64.encodeUTF8String(null) }.isExactlyInstanceOf(NullPointerException::class.java)
    }

    @Test
    fun testDecode() {
        assertThat(Base64.decode(byteArrayEncoded)).isEqualTo(testString)
    }

    @Test
    fun testDecodeNull() {
        assertThatThrownBy { Base64.decode(null) }.isExactlyInstanceOf(CanonException::class.java)
    }

}