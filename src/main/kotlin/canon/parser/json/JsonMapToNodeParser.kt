package canon.parser.json

import canon.parser.xml.CanonXmlParser
import org.w3c.dom.Document
import org.w3c.dom.Node

class JsonMapToNodeParser {
    companion object {
        @JvmStatic
        fun parse(map: Map<String, Any?>): Node {
            val root = CanonXmlParser.getDocumentBuilder().newDocument()
            parse(map, root, root)
            return root
        }

        @JvmStatic
        fun parse(map: Map<String, Any?>, parent: Node, root: Document) {
            val name: String = (map["type"] as String).decapitalize()
            val element = root.createElement(name)
            if (map.containsKey("text")) element.textContent = map["text"] as String
            map.filter { it.key != "elements" }.forEach { element.setAttribute(it.key, it.value.toString()) }
            (map["elements"] as List<*>?)?.filterIsInstance<Map<String, Any?>>()?.forEach { parse(it, element, root) }
            parent.appendChild(element)
        }
    }


}