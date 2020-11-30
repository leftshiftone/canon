package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Button
import canon.support.Base64
import org.w3c.dom.Node

open class ButtonStrategy : AbstractParseStrategy<Button>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Button {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val value = node.attrAsText("value").ifEmpty { null }

        var onlyText = true

        for(i in 0 until node.childNodes.length)
            if(node.childNodes.item(i).nodeName != "#text" && node.childNodes.item(i).nodeName != "#comment")
                onlyText = false

        return if(onlyText) {
            Button(id, `class`, node.textContent, name, Base64.encode(mapOf("payload" to value)), ArrayList())
        } else {
            Button(id, `class`, null, name, Base64.encode(mapOf("payload" to value)), factory(node))
        }
    }
}