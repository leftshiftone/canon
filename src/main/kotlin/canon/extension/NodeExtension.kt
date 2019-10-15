package canon.extension

import org.w3c.dom.Node
import java.lang.RuntimeException
import java.util.*

internal fun Node.attrAsText(name:String, defaultValue:String = ""):String {
    if (this.attributes == null) return defaultValue
    val optional = Optional.ofNullable(this.attributes.getNamedItem(name))
    return optional.map { it.textContent }.orElse(defaultValue)
}
internal fun Node.attrAsBoolean(name:String, defaultValue:Boolean):Boolean {
    if (this.attributes == null) return defaultValue
    val optional = Optional.ofNullable(this.attributes.getNamedItem(name))
    return optional.map { it.textContent.equals("true", true) }.orElse(defaultValue)
}
internal fun Node.attrAsDouble(name:String, defaultValue:Double) : Double {
    return try {
        if (this.attributes == null) return defaultValue
        this.attributes.getNamedItem(name).textContent.toDouble()
    } catch (ex : RuntimeException) {
        defaultValue
    }
}
internal fun Node.attrAsInt(name:String, defaultValue:Int) : Int {
    return try {
        if (this.attributes == null) return defaultValue
        this.attributes.getNamedItem(name).textContent.toInt()
    } catch (ex : RuntimeException) {
        defaultValue
    }
}
