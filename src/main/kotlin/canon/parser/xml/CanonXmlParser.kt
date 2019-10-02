package canon.parser.xml

import canon.api.IRenderable
import canon.parser.xml.strategy.*
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
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
        parsers["block"] = BlockStrategy()
        parsers["bold"] = BoldStrategy()
        parsers["break"] = BreakStrategy()
        parsers["button"] = ButtonStrategy()
        parsers["calendar"] = CalendarStrategy()
        parsers["camera"] = CameraStrategy()
        parsers["carousel"] = CarouselStrategy()
        parsers["choice"] = ChoiceStrategy()
        parsers["codeReader"] = CodeReaderStrategy()
        parsers["col"] = ColStrategy()
        parsers["email"] = EmailStrategy()
        // TODO foreach and if
        parsers["form"] = FormStrategy()
        parsers["headline"] = HeadlineStrategy()
        parsers["image"] = ImageStrategy()
        parsers["italic"] = ItalicStrategy()
        parsers["item"] = ItemStrategy()
        parsers["items"] = ItemsStrategy()
        parsers["label"] = LabelStrategy()
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
        parsers["text"] = TextStrategy()
        parsers["textarea"] = TextareaStrategy()
        parsers["trigger"] = TriggerStrategy()
        parsers["upload"] = UploadStrategy()
        parsers["video"] = VideoStrategy()
    }

    fun parse(str: String): List<IRenderable> {
        try {
            val xml = "<markup><smallDevice>$str</smallDevice></markup>"

            val builder = DOCUMENT_BUILDER.get()
            val document = builder.parse(ByteArrayInputStream(xml.toByteArray(UTF_8)))

            return toRenderables(document)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    fun toRenderables(node:Node):List<IRenderable> {
        return toRenderables(node.childNodes, ArrayList())
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
            renderables.add(parsers.get("text")!!.parse(node, this::toRenderables))
        else if (parsers.containsKey(node.nodeName))
            renderables.add(parsers.get(node.nodeName)!!.parse(node, this::toRenderables))
        else
            throw java.lang.IllegalArgumentException("unknown markup elemenent ${node.nodeName}")
    }

}
