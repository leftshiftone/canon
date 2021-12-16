package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItemTest {

    @Test
    fun testItemMapping() {
        val mapped = Item("testId", "testClass", "testAriaLabel", ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
    }
}
