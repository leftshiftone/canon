package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.CodeReader
import org.w3c.dom.Node

open class CodeReaderStrategy : AbstractParseStrategy<CodeReader>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): CodeReader {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val name = node.attrAsText("name")
        val format = node.attrAsText("format")

        return CodeReader(id, `class`, name, format)
    }

}