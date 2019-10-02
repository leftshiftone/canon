package canon.support

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MapsTest {

    private val testString : String = "test1.test2"

    @Test
    fun testGetDeepMapOrPathNull() {
        var testMap : Map<String, Any>? = null
        var nullString : String? = null
        assertNull(Maps.getDeep<Any?>(testMap, testString))
        assertNull(Maps.getDeep<Any?>(HashMap<String, Any>(), nullString))
    }

    @Test
    fun testGetDeepOneObject() {
        var testMap : Map<String, Any> = hashMapOf("test1" to "value1")
        assertEquals("value1", Maps.getDeep<Any?>(testMap, "test1"))
    }

    @Test
    fun testGetDeepMultiMap() {
        var testMap : Map<String, Any> = hashMapOf("test1" to hashMapOf
                    ("test2" to hashMapOf("test3" to "value1")))
        assertEquals("value1", Maps.getDeep<Any?>(testMap, "test1.test2.test3"))

    }



}