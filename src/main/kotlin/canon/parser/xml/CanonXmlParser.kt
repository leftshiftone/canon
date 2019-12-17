package canon.parser.xml

import canon.api.IRenderable
import canon.model.Foreach
import canon.model.If
import canon.parser.xml.strategy.*
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.text.Charsets.UTF_8

open class CanonXmlParser {

    companion object {
        val DOCUMENT_BUILDER_FACTORY = ThreadLocal<DocumentBuilderFactory>()
        val DOCUMENT_BUILDER = ThreadLocal<DocumentBuilder>()

        init {
            // DocumentBuilderFactory and DocumentBuilder are not thread safe but resource intensive instances
            DOCUMENT_BUILDER_FACTORY.set(DocumentBuilderFactory.newInstance())
            DOCUMENT_BUILDER.set(DOCUMENT_BUILDER_FACTORY.get().newDocumentBuilder())
        }

        @JvmStatic
        fun getDocumentBuilder(): DocumentBuilder {
            // TODO: refactor!
            if (DOCUMENT_BUILDER_FACTORY.get() == null)
                DOCUMENT_BUILDER_FACTORY.set(DocumentBuilderFactory.newInstance())
            if (DOCUMENT_BUILDER.get() == null)
                DOCUMENT_BUILDER.set(DOCUMENT_BUILDER_FACTORY.get().newDocumentBuilder())
            return DOCUMENT_BUILDER.get()
        }
    }

    private val parsers: MutableMap<String, AbstractParseStrategy<IRenderable>> = mutableMapOf(
            "block" to BlockStrategy(),
            "bold" to BoldStrategy(),
            "break" to BreakStrategy(),
            "button" to ButtonStrategy(),
            "calendar" to CalendarStrategy(),
            "camera" to CameraStrategy(),
            "carousel" to CarouselStrategy(),
            "checkbox" to CheckboxStrategy(),
            "choice" to ChoiceStrategy(),
            "codeReader" to CodeReaderStrategy(),
            "col" to ColStrategy(),
            "email" to EmailStrategy(),
            "form" to FormStrategy(),
            "headline" to HeadlineStrategy(),
            "image" to ImageStrategy(),
            "italic" to ItalicStrategy(),
            "item" to ItemStrategy(),
            "items" to ItemsStrategy(),
            //"label" to LabelStrategy(),
            "text" to OldTextStrategy(),
            "link" to LinkStrategy(),
            "map" to MapStrategy(),
            "multipleChoice" to MultipleChoiceStrategy(),
            "overlay" to OverlayStrategy(),
            "overlays" to OverlaysStrategy(),
            "phone" to PhoneStrategy(),
            "reel" to ReelStrategy(),
            "reelValue" to ReelValueStrategy(),
            "row" to RowStrategy(),
            "selection" to SelectionStrategy(),
            "singleChoice" to SingleChoiceStrategy(),
            "slider" to SliderStrategy(),
            "slotmachine" to SlotMachineStrategy(),
            "slotMachine" to SlotMachineStrategy(),
            "container" to ContainerStrategy(),
            "spinner" to SpinnerStrategy(),
            "submit" to SubmitStrategy(),
            "suggestion" to SuggestionStrategy(),
            "table" to TableStrategy(),
            //"text" to TextStrategy(),
            "textInput" to TextInputStrategy(),
            "textarea" to TextareaStrategy(),
            "trigger" to TriggerStrategy(),
            "upload" to UploadStrategy(),
            "video" to VideoStrategy()
    )

    fun parse(str: String): List<IRenderable> {
        try {
            var xml = str.replace("&", "&amp;")
            xml = "<markup><container>$xml</container></markup>"

            val document = getDocumentBuilder().parse(ByteArrayInputStream(xml.toByteArray(UTF_8)))
            return toRenderables(document.childNodes, mutableListOf())
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun toRenderables(nodeList: NodeList, renderables: MutableList<IRenderable>): List<IRenderable> {
        for (i in 0 until nodeList.length) {
            toRenderable(nodeList.item(i), renderables)
        }
        return renderables
    }

    fun toRenderables(node: Node): List<IRenderable> {
        return toRenderables(node.childNodes, mutableListOf())
    }

    fun toRenderable(node: Node, renderables: MutableList<IRenderable>) {

        val attributes = getAttributes(node.attributes)

        val wrap: (it: IRenderable) -> IRenderable = {
            when {
                attributes.containsKey("if") -> If(attributes["if"]!!.trim(), it, emptyList())
                attributes.containsKey("foreach") -> Foreach(attributes["foreach"], it)
                else -> it
            }
        }

        if (node.nodeName == "markup") {
            toRenderables(node.childNodes, renderables)
        } else if (node.nodeName == "#text") { // todo: why is it needed here?
            if (node.textContent.isNotBlank())
                renderables.add(wrap(parsers["text"]!!.parse(node, this::toRenderables)))
        } else if (parsers.containsKey(node.nodeName)) {
            renderables.add(wrap(parsers[node.nodeName]!!.parse(node, this::toRenderables)))
        } else {
            throw IllegalArgumentException("unknown markup element ${node.nodeName}")
        }
    }

    fun getAttributes(attributes: NamedNodeMap?): Map<String, String> {
        if (attributes == null) {
            return emptyMap()
        }
        val map = mutableMapOf<String, String>()

        if (attributes.getNamedItem("foreach") != null) {
            map["foreach"] = attributes.getNamedItem("foreach").textContent
        }
        if (attributes.getNamedItem("if") != null) {
            map["if"] = attributes.getNamedItem("if").textContent
        }

        for (i in 0 until attributes.length) {
            val node = attributes.item(i)
            map[node.nodeName] = node.nodeValue
        }

        return map
    }

    fun setParseStrategy(key: String, parseStrategy: AbstractParseStrategy<IRenderable>) {
        parsers[key] = parseStrategy
    }
}
