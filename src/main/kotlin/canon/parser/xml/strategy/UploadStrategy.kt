package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsDouble
import canon.extension.attrAsText
import canon.model.Upload
import org.w3c.dom.Node

open class UploadStrategy : AbstractParseStrategy<Upload>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Upload {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val accept = node.attrAsText("accept")
        val name = node.attrAsText("name")
        val maxSize = node.attrAsDouble("maxSize", 2.0)
        val maxCompressSize = node.attrAsDouble("maxCompressSize", 0.0)

        return Upload(id, `class`, accept, name, node.textContent, maxSize, maxCompressSize)
    }
}