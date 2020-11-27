package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.CodeReader

class CodeReaderStrategy : AbstractParseStrategy<CodeReader>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): CodeReader {
        return CodeReader(map["id"]?.toString()?.ifEmpty { null },
                map["class"]?.toString()?.ifEmpty { null },
                map["name"]?.toString()?.ifEmpty { null },
                map["format"]?.toString()?.ifEmpty { null })
    }
}