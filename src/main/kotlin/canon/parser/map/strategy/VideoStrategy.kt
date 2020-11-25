package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Video

class VideoStrategy : AbstractParseStrategy<Video>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Video {
        return Video(map["id"]?.toString(),
                map["class"]?.toString(),
                map["src"]?.toString())
    }
}