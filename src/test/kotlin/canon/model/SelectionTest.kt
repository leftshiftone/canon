package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class SelectionTest {

    @Test
    fun testSelectionMapping() {
        val mapped = Selection("testId", "testClass", "testName", 12,
                ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(4, mapped.size)
        assertEquals("testName", mapped.get("name"))
        assertEquals(12, mapped.get("countdownInSec"))
    }

    /*
    @Test
    fun testSelectionWithChoiceMapping() {
        val item = Item("testId", "testClass", ArrayList()).toMap(HashMap(), TestEvaluator())

        val selection = Selection("testId", "testClass", "testName", 12,
                listOf(item)).toMap(HashMap(), TestEvaluator())

        assertEquals(4, selection.size)
        assertEquals("testName", selection.get("name"))
        assertEquals(12, selection.get("countdownInSec"))
    }*/
}
