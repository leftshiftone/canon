package canon.parser.xml

import canon.api.IRenderable
import canon.parser.xml.strategy.*
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.util.*
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.text.Charsets.UTF_8

class CanonXmlParser {

    companion object {
        val DOCUMENT_BUILDER_FACTORY = ThreadLocal<DocumentBuilderFactory>()
        val DOCUMENT_BUILDER = ThreadLocal<DocumentBuilder>()

        init {
            // DocumentBuilderFactory and DocumentBuilder are not thread safe but resource intensive instances
            DOCUMENT_BUILDER_FACTORY.set(DocumentBuilderFactory.newInstance())
            DOCUMENT_BUILDER.set(DOCUMENT_BUILDER_FACTORY.get().newDocumentBuilder())
        }
    }

    private val parsers:HashMap<String, AbstractParseStrategy<IRenderable>> = HashMap()

    init {
        parsers["text"] = TextStrategy()
        parsers["headline"] = HeadlineStrategy()
        parsers["bold"] = BoldStrategy()
        parsers["email"] = EmailStrategy()
        parsers["label"] = LabelStrategy()
    }

    fun parse(str: String): List<IRenderable> {
        try {
            val xml = "<markup><smallDevice>$str</smallDevice></markup>"

            val builder = DOCUMENT_BUILDER.get()
            val document = builder.parse(ByteArrayInputStream(xml.toByteArray(UTF_8)))

            return toRenderables(document.childNodes, ArrayList())
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private fun toRenderables(nodeList: NodeList, renderables: ArrayList<IRenderable>): List<IRenderable> {
        for (i in 0 until nodeList.length) {
            toRenderable(nodeList.item(i), renderables)
        }
        return renderables
    }

    private fun toRenderable(node: Node, renderables: ArrayList<IRenderable>) {
        if (node.nodeName.equals("markup"))
            toRenderables(node.childNodes, renderables)
        else if (node.nodeName.equals("#text") && node.textContent.isNotBlank())
            renderables.add(parsers.get("text")!!.parse(node))
        else if (parsers.containsKey(node.nodeName))
            renderables.add(parsers.get(node.nodeName)!!.parse(node))
        else
            throw java.lang.IllegalArgumentException("unknown markup elemenent ${node.nodeName}")
    }

}
