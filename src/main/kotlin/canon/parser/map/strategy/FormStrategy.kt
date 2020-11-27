package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.Form

class FormStrategy : AbstractParseStrategy<Form>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): Form {
        return Form(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["name"]?.toString()?.ifEmpty { null },
                factory(map))
    }
}