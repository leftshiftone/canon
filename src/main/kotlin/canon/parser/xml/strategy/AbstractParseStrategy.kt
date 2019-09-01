package canon.parser.xml.strategy

import org.w3c.dom.Node

abstract class AbstractParseStrategy<out TYPE> {

    abstract fun parse(node: Node):TYPE;

}
