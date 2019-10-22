package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Image
import org.w3c.dom.Node

class ImageStrategy : AbstractParseStrategy<Image>() {
    
    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Image {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val src = node.attrAsText("src")
        val width = node.attrAsText("width")
        val height = node.attrAsText("height")
        val alt = node.attrAsText("alt")

        return Image(id, `class`, src, width, height, alt)
    }
}