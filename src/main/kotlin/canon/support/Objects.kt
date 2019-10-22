package canon.support

object Objects {

    @SafeVarargs
    fun <T> firstNonNull(vararg objects: T): T? {
        if (objects == null) {
            return null
        }
        for (`object` in objects) {
            if (`object` != null) {
                return `object`
            }
        }
        return null
    }

}