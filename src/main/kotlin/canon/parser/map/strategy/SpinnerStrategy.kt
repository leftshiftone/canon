package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Spinner

class SpinnerStrategy : AbstractParseStrategy<Spinner>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Spinner {
        return Spinner(map["id"]?.toString(),
                map["class"]?.toString(),
                map["min"] as Double?,
                map["max"] as Double?,
                map["step"] as Double?,
                map["value"] as Double?,
                map["name"]?.toString())
    }
}