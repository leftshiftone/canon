package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Image
import org.w3c.dom.Node

open class ImageStrategy : AbstractParseStrategy<Image>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Image {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val src = node.attrAsText("src").ifEmpty { null }
        val width = node.attrAsText("width").ifEmpty { null }
        val height = node.attrAsText("height").ifEmpty { null }
        val alt = node.attrAsText("alt").ifEmpty { null }

        return Image(id, `class`, src, width, height, alt)
    }
}