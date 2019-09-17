package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Calendar
import org.w3c.dom.Node

class CalendarStrategy : AbstractParseStrategy<Calendar>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Calendar {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")

        return Calendar(id, `class`, name)
    }

}