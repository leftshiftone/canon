package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class PhoneTest {

    @Test
    fun testPhoneMapping() {
        val mapped = Phone("testId", "testClass", "placeHolder", false,
                "testName", "testValue").toMap(HashMap())

        assertEquals(4, mapped.size)
        assertEquals("placeHolder", mapped.get("placeholder"))
        assertEquals(false, mapped.get("required"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testValue", mapped.get("value"))

    }
}
