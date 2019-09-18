package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class FormTest {

    @Test
    fun testFormMapping() {
        val mapped = Form("testId", "testClass", "testName", ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(1, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}