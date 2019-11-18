package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Video
import org.w3c.dom.Node

class VideoStrategy : AbstractParseStrategy<Video>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Video {
        val id = node.attrAsText("id")
        val `class` = node.attrAsText("class")
        val src = node.attrAsText("src")

        return Video(id, `class`, src)
    }
}