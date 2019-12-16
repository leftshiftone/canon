package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ItemsTest {

    @Test
    fun testItemsMapping() {
        val mapped = Items("testId", "testClass", true, ArrayList()).toMap(HashMap(), TestEvaluator())

        assertEquals(3, mapped.size)
        assertEquals(true, mapped.get("ordered"))
    }
}
