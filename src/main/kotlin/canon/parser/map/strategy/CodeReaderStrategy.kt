package canon.parser.map.strategy

import canon.api.IRenderable
import canon.model.CodeReader

class CodeReaderStrategy : AbstractParseStrategy<CodeReader>() {
    override fun parse(map: Map<String, Any>, factory: (Map<String, Any>) -> List<IRenderable>): CodeReader {
        return CodeReader(map["id"]?.toString(),
                map["class"]?.toString(),
                map["name"]?.toString(),
                map["format"]?.toString())
    }
}