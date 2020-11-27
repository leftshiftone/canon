package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Phone

class PhoneStrategy : AbstractParseStrategy<Phone>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Phone {
        return Phone(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["placeholder"]?.toString()?.ifEmpty { null },
                map["required"]?.toString()?.ifEmpty { null }?.toBoolean(),
                map["name"]?.toString()?.ifEmpty { null },
                map["value"]?.toString()?.ifEmpty { null })
    }
}