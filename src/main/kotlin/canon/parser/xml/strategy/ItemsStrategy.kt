package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsText
import canon.model.Items
import org.w3c.dom.Node

class ItemsStrategy : AbstractParseStrategy<Items>() {

    override fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): Items {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ordered = node.attrAsBoolean("ordered", false)
        
        return Items(id, `class`, ordered, factory(node, context))
    }
}