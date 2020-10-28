package canon.parser.map

import canon.api.IRenderable
import canon.parser.map.strategy.BasketStrategy

class CanonMapParser {

    companion object {
        fun parse(map: Map<String, Any>): IRenderable {
            val factory: (Map<String, Any>) -> List<IRenderable> = { m: Map<String, Any> ->
                @Suppress("UNCHECKED_CAST")
                if (m.containsKey("elements")) (m["elements"] as List<Map<String, Any>>).map(this::parse)
                else emptyList()
            }

            return when (map["type"]) {
                "basket" -> BasketStrategy().parse(map, factory)
                else -> throw RuntimeException("cannot convert $map")
            }
        }
    }

}