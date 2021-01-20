package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Rating

class RatingStrategy : AbstractParseStrategy<Rating>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Rating {
        return Rating(map["enabled"]?.toString(), factory(map))
    }
}