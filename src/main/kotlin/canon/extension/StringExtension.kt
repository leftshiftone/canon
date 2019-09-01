package canon.extension

import org.w3c.dom.Node
import java.nio.charset.StandardCharsets
import javax.xml.parsers.DocumentBuilderFactory

internal fun String.toNode(): Node {
    val factory = DocumentBuilderFactory.newInstance()
    val builder = factory.newDocumentBuilder()

    val document = builder.parse(this.byteInputStream(StandardCharsets.UTF_8))
    return document.childNodes.item(0)
}
