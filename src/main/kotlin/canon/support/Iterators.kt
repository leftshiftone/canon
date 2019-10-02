package canon.support

import kotlin.IllegalArgumentException
import kotlin.collections.ArrayList

object Iterators {

    fun toIterator(obj: Any?) : Iterator<Any> {
        when (obj) {
            null -> return ArrayList<Any>().iterator()
            is Array<*> -> return toIterator(listOf(obj))
            is Collection<*> -> return (mutableListOf<Any>(obj)).iterator()
        }

        if (obj is IntArray || obj is ShortArray || obj is ByteArray || obj is LongArray)
            throw IllegalArgumentException("cannot return an iterator from primitive array")
        throw IllegalArgumentException("cannot return iterator from $obj")
    }
}