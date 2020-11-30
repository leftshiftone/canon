package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsDouble
import canon.extension.attrAsText
import canon.model.Upload
import org.w3c.dom.Node

open class UploadStrategy : AbstractParseStrategy<Upload>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Upload {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val accept = node.attrAsText("accept").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val maxSize = node.attrAsDouble("maxSize", 2.0)
        val maxCompressSize = node.attrAsDouble("maxCompressSize", 0.0)
        val required = node.attrAsBoolean("required", false)

        return Upload(id, `class`, accept, name, node.textContent, maxSize, maxCompressSize, required)
    }
}