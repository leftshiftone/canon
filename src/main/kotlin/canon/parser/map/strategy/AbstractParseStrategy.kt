package canon.parser.map.strategy

import canon.api.IRenderable

abstract class AbstractParseStrategy<out TYPE> {

    abstract fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): TYPE
}
