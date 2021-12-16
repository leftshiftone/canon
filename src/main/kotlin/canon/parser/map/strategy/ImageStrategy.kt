package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Image

class ImageStrategy : AbstractParseStrategy<Image>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Image {
        return Image(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["src"]?.toString()?.ifEmpty { null },
            map["width"]?.toString()?.ifEmpty { null },
            map["height"]?.toString()?.ifEmpty { null },
            map["alt"]?.toString()?.ifEmpty { null })
    }
}