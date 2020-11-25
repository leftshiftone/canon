package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Image

class ImageStrategy : AbstractParseStrategy<Image>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Image {
        return Image(map["id"]?.toString(),
                map["class"]?.toString(),
                map["src"]?.toString(),
                map["width"]?.toString(),
                map["height"]?.toString(),
                map["alt"]?.toString())
    }
}