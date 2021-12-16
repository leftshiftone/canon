package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsBoolean
import canon.extension.attrAsDouble
import canon.extension.attrAsText
import canon.model.Camera
import org.w3c.dom.Node

open class CameraStrategy : AbstractParseStrategy<Camera>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Camera {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val ariaLabel = node.attrAsText("ariaLabel")
        val name = node.attrAsText("name")
        val required = node.attrAsBoolean("required", false)
        val maxCompressSize = node.attrAsDouble("maxCompressSize", 0.0)

        return Camera(id, `class`, ariaLabel, name, required, maxCompressSize, factory(node))
    }

}