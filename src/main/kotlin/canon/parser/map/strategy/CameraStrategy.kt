package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Camera

class CameraStrategy : AbstractParseStrategy<Camera>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Camera {
        return Camera(
            map["id"]?.toString()?.ifEmpty { null },
            map["class"]?.toString()?.ifEmpty { null },
            map["ariaLabel"]?.toString()?.ifEmpty { null },
            map["name"]?.toString()?.ifEmpty { null },
            map["required"]?.toString()?.ifEmpty { null }?.toBoolean(),
            map["maxCompressSize"]?.toString()?.ifEmpty { null }?.toDouble(),
            factory(map)
        )
    }
}