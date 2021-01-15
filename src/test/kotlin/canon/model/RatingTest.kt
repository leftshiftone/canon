package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class RatingTest {

    @Test
    fun testRatingMapping() {
        val mapped = Rating(null, emptyList()).toMap(HashMap(), TestEvaluator())

        assertEquals(0, mapped.size)
    }

    @Test
    fun testRatingMappingWithEnabledSet() {
        val mapped = Rating("false", emptyList()).toMap(HashMap(), TestEvaluator())

        assertEquals(1, mapped.size)
        assertEquals(false, mapped["enabled"])
    }

    @Test
    fun testRatingMappingWithEnabledSetToIncorrectValue() {
        val mapped = Rating("asdf", emptyList()).toMap(HashMap(), TestEvaluator())

        assertEquals(0, mapped.size)
    }
}
