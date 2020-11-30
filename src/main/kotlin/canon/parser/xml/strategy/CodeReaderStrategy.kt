package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.CodeReader
import org.w3c.dom.Node

open class CodeReaderStrategy : AbstractParseStrategy<CodeReader>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): CodeReader {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val format = node.attrAsText("format").ifEmpty { null }

        return CodeReader(id, `class`, name, format)
    }

}