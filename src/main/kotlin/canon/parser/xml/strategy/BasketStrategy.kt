package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Basket
import org.w3c.dom.Node

open class BasketStrategy : AbstractParseStrategy<Basket>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Basket {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val name = node.attrAsText("name")
        val required = node.attrAsBoolean("required", false)

        return Basket(id, `class`, ariaLabel, name, required, factory(node))
    }
}
