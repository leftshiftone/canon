package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsDouble
import canon.extension.attrAsText
import canon.model.Spinner
import org.w3c.dom.Node

class SpinnerStrategy : AbstractParseStrategy<Spinner>() {
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Spinner {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val min = node.attrAsDouble("min", 0.0)
        val max = node.attrAsDouble("max", 10.0)
        val step = node.attrAsDouble("step", 1.0)
        val value = node.attrAsDouble("value", 1.0)
        val name = node.attrAsText("name")

        return Spinner(id, `class`, min, max, step, value, name)
    }
}