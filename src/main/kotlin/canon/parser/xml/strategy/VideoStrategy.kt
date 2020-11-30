package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Video
import org.w3c.dom.Node

open class VideoStrategy : AbstractParseStrategy<Video>() {
    
    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Video {
        val id = node.attrAsText("id").ifEmpty { null }
        val `class` = node.attrAsText("class").ifEmpty { null }
        val src = node.attrAsText("src").ifEmpty { null }

        return Video(id, `class`, src)
    }
}