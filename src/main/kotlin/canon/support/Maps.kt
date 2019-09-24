package canon.support

object Maps {

    fun <T> getDeep (map: Map<*, *>?, path: String?) : T? {
        if (map == null || path == null) return null

        var currentMap = map
        val iterator = path.split(".").iterator()
        while (iterator.hasNext()) {
            val obj = currentMap?.get(iterator.next())
            if (!iterator.hasNext()) return obj as T?
            if (obj is Map<*, *>) currentMap = obj else return null
        }

        return null;
    }
}