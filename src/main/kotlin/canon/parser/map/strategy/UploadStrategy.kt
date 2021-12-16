package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Upload

class UploadStrategy : AbstractParseStrategy<Upload>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Upload {
        return Upload(map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["accept"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["text"]?.toString()?.ifEmpty { null },
            map["maxSize"]?.toString()?.ifEmpty { null }?.toDouble(),
            map["maxCompressSize"]?.toString()?.ifEmpty { null }?.toDouble(),
            map["required"]?.toString()?.ifEmpty { null }?.toBoolean()
        )
    }
}