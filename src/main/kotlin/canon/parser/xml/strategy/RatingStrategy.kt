package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.extension.attrAsText
import canon.model.Rating
import org.w3c.dom.Node

open class RatingStrategy : AbstractParseStrategy<Rating>() {

    override fun parse(node: Node, factory: (Node) -> List<IRenderable>): Rating {
        return Rating(node.attrAsText("enabled", "true"),factory(node))
    }
}