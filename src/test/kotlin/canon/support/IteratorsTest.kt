package canon.support

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException

class IteratorsTest {

    @Test
    fun testToIteratorObjIsNull() {
       assertTrue(Iterators.toIterator(null) is Iterator<Any>)
    }

    @Test
    fun testToIteratorObjectIsArray() {
        assertTrue(Iterators.toIterator(arrayOf("test", "test2", "test3")) is Iterator<*>)
    }

    @Test
    fun testToIteratorObjIsList() {
        assertTrue(Iterators.toIterator(listOf("test", "test2", "test3")) is Iterator<*>)
    }

    @Test
    fun testToIteratorSimpleObj() {
        assertThrows(IllegalArgumentException::class.java) {
            Iterators.toIterator("test")}
    }

    @Test
    fun testToIteratorPrimitiveArray() {
        assertThrows(IllegalArgumentException::class.java) {
            Iterators.toIterator(intArrayOf(1,2,3))}
    }
}