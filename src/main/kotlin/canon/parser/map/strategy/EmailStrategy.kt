package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Email

class EmailStrategy : AbstractParseStrategy<Email>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Email {
        return Email(map["id"]?.toString(),
                map["class"]?.toString(),
                map["placeholder"]?.toString(),
                map["required"] as Boolean?,
                map["name"]?.toString(),
                map["value"]?.toString())
    }
}