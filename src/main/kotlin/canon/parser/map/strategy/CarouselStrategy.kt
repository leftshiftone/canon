package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Carousel

class CarouselStrategy : AbstractParseStrategy<Carousel>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Carousel {
        return Carousel(map["id"]?.toString(),
                map["class"]?.toString(),
                map["text"]?.toString(),
                map["name"]?.toString(),
                map["selected"] as Boolean?,
                factory(map))
    }
}