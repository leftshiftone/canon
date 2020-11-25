package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Camera

class CameraStrategy : AbstractParseStrategy<Camera>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Camera {
        return Camera(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["required"] as Boolean?,
                map["maxCompressSize"] as Double?,
                factory(map))
    }
}