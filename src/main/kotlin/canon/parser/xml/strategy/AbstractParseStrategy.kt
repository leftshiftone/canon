package canon.parser.xml.strategy

import canon.api.IRenderable
import canon.support.Base64
import org.w3c.dom.Node
import java.util.HashMap

abstract class AbstractParseStrategy<out TYPE> {

    abstract fun parse(node: Node, context: Map<String, Any?>, factory: (Node, Map<String, Any?>) -> List<IRenderable>): TYPE;
}
