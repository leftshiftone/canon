package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class PhoneTest {

    @Test
    fun testPhoneMapping() {
        val mapped = Phone("testId", "testClass", "testAriaLabel", "placeHolder", false,
                "testName", "testValue").toMap(HashMap(), TestEvaluator())

        assertEquals(7, mapped.size)
        assertEquals("placeHolder", mapped["placeholder"])
        assertEquals(false, mapped["required"])
        assertEquals("testName", mapped["name"])
        assertEquals("testValue", mapped["value"])

    }
}
