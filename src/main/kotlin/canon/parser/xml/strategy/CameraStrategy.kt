package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsDouble
import canon.extension.attrAsText
import canon.model.Camera
import org.w3c.dom.Node

open class CameraStrategy : AbstractParseStrategy<Camera>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Camera {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val name = node.attrAsText("name").ifEmpty { null }
        val required = node.attrAsBoolean("required", false)
        val maxCompressSize = node.attrAsDouble("maxCompressSize", 0.0)

        return Camera(id, `class`, name, required, maxCompressSize, factory(node))
    }

}