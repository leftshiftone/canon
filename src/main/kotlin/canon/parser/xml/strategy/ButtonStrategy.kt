package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Button
import canon.support.Base64
import org.w3c.dom.Node

open class ButtonStrategy : AbstractParseStrategy<Button>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Button {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val value = node.attrAsText("value")

        return if(node.firstChild.nodeName == "#text" && node.firstChild.textContent.trim().isNotEmpty()) {
            Button(id, `class`, name, Base64.encode(mapOf("payload" to value)), node.textContent, ArrayList())
        } else {
            Button(id, `class`, name, Base64.encode(mapOf("payload" to value)), null, factory(node))
        }
    }
}