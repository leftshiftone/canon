package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Submit
import org.w3c.dom.Node

open class SubmitStrategy : AbstractParseStrategy<Submit>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Submit {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }

        return Submit(id, `class`, node.textContent, name)
    }
}