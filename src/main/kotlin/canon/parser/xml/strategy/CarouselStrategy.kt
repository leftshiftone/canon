package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Carousel
import org.w3c.dom.Node

class CarouselStrategy : AbstractParseStrategy<Carousel>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Carousel {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val text = node.attrAsText("text")
        val name = node.attrAsText("name")
        val selected = node.attrAsBoolean("selected", false)

        return Carousel(id, `class`, text, name, selected, factory(node))
    }

}