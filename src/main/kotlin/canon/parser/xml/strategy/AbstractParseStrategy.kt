package canon.parser.xml.strategy

import canon.api.IRenderable
import org.w3c.dom.Node

abstract class AbstractParseStrategy<out TYPE> {

    abstract fun parse(node: Node, factory: (Node) -> List<IRenderable>): TYPE
}
