package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Video

class VideoStrategy : AbstractParseStrategy<Video>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Video {
        return Video(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["src"]?.toString()?.ifEmpty { null })
    }
}