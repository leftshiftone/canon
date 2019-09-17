package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.ReelValue
import org.w3c.dom.Node

class ReelValueStrategy : AbstractParseStrategy<ReelValue>() {
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): ReelValue {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val value = node.attrAsText("value")
        val valueType = node.attrAsText("valueType")

        return ReelValue(id, `class`, value, valueType)
    }
}