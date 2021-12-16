package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Label
import org.w3c.dom.Node

open class LabelStrategy(val trim: Boolean = false) : AbstractParseStrategy<Label>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Label {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        var text = node.textContent

        if(this.trim) {
            text = text.trim()
        }

        return Label(id, `class`, ariaLabel, text)
    }

}
