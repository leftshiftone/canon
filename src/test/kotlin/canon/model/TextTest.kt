package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class TextTest {

    @Test
    fun testTextMapping() {
        val mapped = Text("testId", "testClass", "regEx", "placeHolder", false,
                "testName","testValue").toMap(HashMap())

        assertEquals(5, mapped.size)
        assertEquals("regEx", mapped.get("regex"))
        assertEquals("placeHolder", mapped.get("placeholder"))
        assertEquals(false, mapped.get("required"))
        assertEquals("testName", mapped.get("name"))
        assertEquals("testValue", mapped.get("value"))
    }
}
