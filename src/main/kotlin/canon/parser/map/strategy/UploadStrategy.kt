package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Upload

class UploadStrategy : AbstractParseStrategy<Upload>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Upload {
        return Upload(map["id"]?.toString(),
                map["class"]?.toString(),
                map["accept"]?.toString(),
                map["name"]?.toString(),
                map["text"]?.toString(),
                map["maxSize"] as Double?,
                map["maxCompressSize"] as Double?,
                map["required"] as Boolean?)
    }
}