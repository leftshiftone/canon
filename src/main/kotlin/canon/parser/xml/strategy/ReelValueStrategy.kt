package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.ReelValue
import org.w3c.dom.Node

open class ReelValueStrategy : AbstractParseStrategy<ReelValue>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): ReelValue {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val value = node.attrAsText("value").ifEmpty { null }
        val valueType = node.attrAsText("valueType").ifEmpty { null }

        return ReelValue(id, `class`, value, valueType)
    }
}