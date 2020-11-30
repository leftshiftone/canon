package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Carousel
import org.w3c.dom.Node

open class CarouselStrategy : AbstractParseStrategy<Carousel>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Carousel {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val text = node.attrAsText("text").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val selected = node.attrAsBoolean("selected", false)

        return Carousel(id, `class`, text, name, selected, factory(node))
    }

}