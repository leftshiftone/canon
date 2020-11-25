package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Phone

class PhoneStrategy : AbstractParseStrategy<Phone>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Phone {
        return Phone(map["id"]?.toString(),
                map["class"]?.toString(),
                map["placeholder"]?.toString(),
                map["required"] as Boolean?,
                map["name"]?.toString(),
                map["value"]?.toString())
    }
}