package canon.support


object Iterators {

    @Suppress("UNCHECKED_CAST")
    fun toIterator(obj: Any): Iterator<Any> {
        return when (obj) {
            is Array<*> -> obj.filter { it != null }.iterator() as Iterator<Any>
            is Collection<*> -> obj.filter { it != null }.iterator() as Iterator<Any>
            is IntArray, is ShortArray, is LongArray, is ByteArray -> throw IllegalArgumentException("cannot return an iterator from primitive array")
            else -> throw IllegalArgumentException("cannot return iterator for $obj")
        }
    }
}
