package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Form
import org.w3c.dom.Node

open class FormStrategy : AbstractParseStrategy<Form>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Form {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }

        return Form(id, `class`, name, factory(node))
    }
}