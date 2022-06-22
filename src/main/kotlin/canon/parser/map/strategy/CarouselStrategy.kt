package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Carousel

class CarouselStrategy : AbstractParseStrategy<Carousel>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Carousel {
        return Carousel(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["text"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["selected"]?.toString()?.ifEmpty { null }?.toBoolean(),
            factory(map)
        )
    }
}