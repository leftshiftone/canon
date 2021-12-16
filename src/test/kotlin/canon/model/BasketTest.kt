package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BasketTest {

    @Test
    fun testBasketMapping() {
        val basket = Basket("testId", "block", "testAriaLabel", "testName", true, ArrayList())

        val mapped = basket.toMap(HashMap(), TestEvaluator())
        assertEquals(5, mapped.size)
        assertEquals("testName", mapped.get("name"))
    }
}
