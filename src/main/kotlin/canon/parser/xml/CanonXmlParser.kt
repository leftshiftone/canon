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

    private val parsers: HashMap<String, AbstractParseStrategy<IRenderable>> = HashMap()

    init {
        parsers["block"] = BlockStrategy()
        parsers["bold"] = BoldStrategy()
        parsers["break"] = BreakStrategy()
        parsers["button"] = ButtonStrategy()
        parsers["calendar"] = CalendarStrategy()
        parsers["camera"] = CameraStrategy()
        parsers["carousel"] = CarouselStrategy()
        parsers["checkbox"] = CheckboxStrategy()
        parsers["choice"] = ChoiceStrategy()
        parsers["codeReader"] = CodeReaderStrategy()
        parsers["col"] = ColStrategy()
        parsers["email"] = EmailStrategy()
        parsers["form"] = FormStrategy()
        parsers["headline"] = HeadlineStrategy()
        parsers["image"] = ImageStrategy()
        parsers["italic"] = ItalicStrategy()
        parsers["item"] = ItemStrategy()
        parsers["items"] = ItemsStrategy()
        //parsers["label"] = LabelStrategy()
        parsers["text"] = OldTextStrategy()
        parsers["link"] = LinkStrategy()
        parsers["map"] = MapStrategy()
        parsers["multipleChoice"] = MultipleChoiceStrategy()
        parsers["overlay"] = OverlayStrategy()
        parsers["overlays"] = OverlaysStrategy()
        parsers["phone"] = PhoneStrategy()
        parsers["reel"] = ReelStrategy()
        parsers["reelValue"] = ReelValueStrategy()
        parsers["row"] = RowStrategy()
        parsers["selection"] = SelectionStrategy()
        parsers["singleChoice"] = SingleChoiceStrategy()
        parsers["slider"] = SliderStrategy()
        parsers["slotMachine"] = SlotMachineStrategy()
        parsers["smallDevice"] = SmallDeviceStrategy()
        parsers["spinner"] = SpinnerStrategy()
        parsers["submit"] = SubmitStrategy()
        parsers["suggestion"] = SuggestionStrategy()
        parsers["table"] = TableStrategy()
        //parsers["text"] = TextStrategy()
        parsers["textInput"] = TextInputStrategy()
        parsers["textarea"] = TextareaStrategy()
        parsers["trigger"] = TriggerStrategy()
        parsers["upload"] = UploadStrategy()
        parsers["video"] = VideoStrategy()
    }

    fun parse(str: String, context: Map<String, Any?>): List<IRenderable> {
        try {
            val xml = "<markup><smallDevice>$str</smallDevice></markup>"

            // TODO: refactor!
            if (DOCUMENT_BUILDER_FACTORY.get() == null)
                DOCUMENT_BUILDER_FACTORY.set(DocumentBuilderFactory.newInstance());
            if (DOCUMENT_BUILDER.get() == null)
                DOCUMENT_BUILDER.set(DOCUMENT_BUILDER_FACTORY.get().newDocumentBuilder());

            val document = DOCUMENT_BUILDER.get().parse(ByteArrayInputStream(xml.toByteArray(UTF_8)))

            return toRenderables(document.childNodes, ArrayList<IRenderable>(), context)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun toRenderables(nodeList: NodeList, renderables: ArrayList<IRenderable>, context: Map<String, Any?>): List<IRenderable> {
        for (i in 0 until nodeList.length) {
            toRenderable(nodeList.item(i), renderables, context)
        }
        return renderables
    }

    fun toRenderables(node: Node, context: Map<String, Any?>): List<IRenderable> {
        return toRenderables(node.childNodes, ArrayList(), context)
    }

    // TODO: refactor!
    fun toRenderable(node: Node, renderables: ArrayList<IRenderable>, context: Map<String, Any?>) {

        val attributes = getAttributes(node.attributes)

        val wrap: (it: IRenderable) -> IRenderable = {
            if (attributes.containsKey("if")) {
                If(attributes["if"]!!.trim(), it, java.util.ArrayList())
            } else if (attributes.containsKey("foreach")) {
                Foreach(attributes["foreach"], it)
            }
            it
        }

        if (node.nodeName.equals("markup"))
            toRenderables(node.childNodes, renderables, context)
        else if (node.nodeName.equals("#text") && node.textContent.isNotEmpty())
            renderables.add(wrap(parsers.get("text")!!.parse(node, context, this::toRenderables)))
        else if (parsers.containsKey(node.nodeName))
            renderables.add(wrap(parsers.get(node.nodeName)!!.parse(node, context, this::toRenderables)))
        else
            throw java.lang.IllegalArgumentException("unknown markup elemenent ${node.nodeName}")
    }

    fun getAttributes(attributes: NamedNodeMap?): Map<String, String> {
        if (attributes == null) {
            return java.util.HashMap()
        }
        val map = java.util.HashMap<String, String>()

        if (attributes.getNamedItem("foreach") != null) {
            map["foreach"] = attributes.getNamedItem("foreach").textContent
        }
        if (attributes.getNamedItem("if") != null) {
            map["if"] = attributes.getNamedItem("if").textContent
        }
        if (attributes.getNamedItem("value") != null) {
            map["value"] = attributes.getNamedItem("value").textContent
        }
        if (attributes.getNamedItem("name") != null) {
            map["name"] = attributes.getNamedItem("name").textContent
        }

        for (i in 0 until attributes.length) {
            val node = attributes.item(i)
            map[node.nodeName] = node.nodeValue
        }

        return map
    }

}
