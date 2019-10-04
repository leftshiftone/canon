package canon.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test


class ForeachTest {

    @Test
    fun testForeachMapping() {

        val foreach = Foreach("\$event in \$result", Block("testId", "testClass",
                "testName", ArrayList()))

        assertNotNull(foreach)
        assertEquals(1, foreach.renderables.size)
    }
}
