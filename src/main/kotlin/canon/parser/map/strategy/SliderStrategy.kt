package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Slider

class SliderStrategy : AbstractParseStrategy<Slider>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Slider {
        return Slider(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["min"]?.toString()?.ifEmpty { null }?.toDouble(),
                map["max"]?.toString()?.ifEmpty { null }?.toDouble(),
                map["step"]?.toString()?.ifEmpty { null }?.toDouble(),
                map["value"]?.toString()?.ifEmpty { null }?.toDouble(),
                map["name"]?.toString()?.ifEmpty { null },
                map["values"]?.toString()?.ifEmpty { null })
    }
}