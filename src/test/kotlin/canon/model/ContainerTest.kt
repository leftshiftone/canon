package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ContainerTest {

    @Test
    fun testContainerMapping() {
        val mapped = Container("testId", "testClass", "testName",
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
