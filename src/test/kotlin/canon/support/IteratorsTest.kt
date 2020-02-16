package canon.support

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

@Suppress("USELESS_IS_CHECK")
class IteratorsTest {

    @Test
    fun `iterator is returned for array`() {
        assertTrue(Iterators.toIterator(arrayOf("test", "test2", "test3")) is Iterator)
    }

    @Test
    fun `iterator is returned for collection`() {
        assertTrue(Iterators.toIterator(listOf("test", "test2", "test3")) is Iterator)
    }

    @Test
    fun `iterator which holds string items is returned for collection`() {
        val result = Iterators.toIterator(listOf("test", "test2", "test3"))
        assertTrue(result is Iterator)
        assertTrue(result.next() is String)
    }

    @Test
    fun `ordinary object throws exception`() {
        assertThrows(IllegalArgumentException::class.java) {
            Iterators.toIterator("test")
        }
    }

    @Test
    fun `primitive array throws exception`() {
        assertThrows(IllegalArgumentException::class.java) {
            Iterators.toIterator(intArrayOf(1, 2, 3))
        }
    }
}
