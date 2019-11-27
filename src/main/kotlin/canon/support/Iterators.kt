package canon.support


object Iterators {

    fun toIterator(obj: Any?): Iterator<Any?> {
        return when (obj) {
            null -> emptyList<Any>().iterator()
            is Array<*> -> obj.iterator()
            is Collection<*> -> obj.iterator()
            is IntArray, is ShortArray, is LongArray, is ByteArray -> throw IllegalArgumentException("cannot return an iterator from primitive array")
            else -> throw IllegalArgumentException("cannot return iterator for $obj")
        }
    }
}
