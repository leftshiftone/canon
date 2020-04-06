package canon.support

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapsTest {

    private val testString : String = "test1.test2"

    @Test
    fun testGetDeepMapOrPathNull() {
        var testMap : Map<String, Any>? = null
        var nullString : String? = null
        assertThat(Maps.getDeep<Any?>(testMap, testString)).isNull()
        assertThat(Maps.getDeep<Any?>(HashMap<String, Any>(), nullString)).isNull()
    }

    @Test
    fun testGetDeepOneObject() {
        var testMap : Map<String, Any> = hashMapOf("test1" to "value1")
        assertThat(Maps.getDeep<Any?>(testMap, "test1")).isEqualTo("value1")
    }

    @Test
    fun testGetDeepMultiMap() {
        var testMap : Map<String, Any> = hashMapOf("test1" to hashMapOf
                    ("test2" to hashMapOf("test3" to "value1")))
        assertThat(Maps.getDeep<Any?>(testMap, "test1.test2.test3")).isEqualTo("value1")
    }
}