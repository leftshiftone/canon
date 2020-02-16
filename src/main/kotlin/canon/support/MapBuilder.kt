package canon.support

class MapBuilder {

    private val map:HashMap<String, Any> = HashMap()

    fun put(key:String, value:String?, defaultValue:String):MapBuilder {
        if (!value.isNullOrBlank())
            this.map[key] = value
        else
            this.map[key] = defaultValue
        return this
    }

    fun put(key:String, value:String?, transformer:(String) -> Any = {it}):MapBuilder {
        if (!value.isNullOrBlank())
            this.map[key] = transformer(value)
        return this
    }

    fun put(key:String, value:String?, defaultValue:Any, transformer:(String) -> Any = {it}):MapBuilder {
        if (!value.isNullOrBlank())
            this.map[key] = transformer(value)
        else
            this.map[key] = defaultValue
        return this
    }

    fun put(key:String, value:Boolean?):MapBuilder {
        if (value != null)
            this.map[key] = value
        return this
    }

    fun put(key:String, value:Double?):MapBuilder {
        if (value != null)
            this.map[key] = value
        return this
    }

    fun put(key:String, value:Int?):MapBuilder {
        if (value != null)
            this.map[key] = value
        return this
    }

    fun toMap() = map

}
