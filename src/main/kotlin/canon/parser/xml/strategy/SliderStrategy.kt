package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsDouble
import canon.extension.attrAsText
import canon.model.Slider
import org.w3c.dom.Node

open class SliderStrategy : AbstractParseStrategy<Slider>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Slider {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val min = node.attrAsDouble("min", 0.0)
        val max = node.attrAsDouble("max", 10.0)
        val step = node.attrAsDouble("step", 1.0)
        val value = node.attrAsDouble("value", 1.0)
        val name = node.attrAsText("name")
        val values = node.attrAsText("values")

        return Slider(id, `class`, ariaLabel, min, max, step, value, name, values)
    }
}