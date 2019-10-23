package canon.extension

import canon.support.Base64
import org.w3c.dom.Node
import java.nio.charset.StandardCharsets
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory

internal fun String.toNode(): Node {
    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()

    val document = builder.parse(this.byteInputStream(StandardCharsets.UTF_8))
    return document.childNodes.item(0)
}

@Deprecated("Removed soon - value should be called in rain")
fun String?.encodeAsMap(): String? {
    if (this == null) {
        return null
    }
    val map = HashMap<String, Any>()
    map["payload"] = this
    return Base64.encode(map)
}
