package canon.parser.xml

import canon.api.IRenderable
import canon.exception.CanonException
import canon.model.Foreach
import canon.model.If
import canon.parser.xml.strategy.*
import canon.parser.xml.validation.XmlValidation
import canon.parser.xml.validation.XmlValidator
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.text.Charsets.UTF_8

open class CanonXmlParser(val customStrategies: (String) -> Optional<AbstractParseStrategy<IRenderable>> = {Optional.empty()}) {

    private val validator = XmlValidator(CanonXmlParser::class.java.getResourceAsStream("/xml/canon.xsd"))

    companion object {
        // DocumentBuilderFactory and DocumentBuilder are not thread safe but resource intensive instances
        private val DOCUMENT_BUILDER = ThreadLocal.withInitial {
            val builder = DocumentBuilderFactory.newInstance()
            builder.newDocumentBuilder()
        }!!

        @JvmStatic
        fun getDocumentBuilder() = DOCUMENT_BUILDER.get()!!
    }

    private fun resolveStrategy(key: String): AbstractParseStrategy<IRenderable> {
        val option = customStrategies(key)
        if (option.isPresent) return option.get()
        return when (key) {
            "basket" -> BasketStrategy()
            "block" -> BlockStrategy()
            "bold" -> BoldStrategy()
            "break" -> BreakStrategy()
            "button" -> ButtonStrategy()
            "calendar" -> CalendarStrategy()
            "camera" -> CameraStrategy()
            "carousel" -> CarouselStrategy()
            "checkbox" -> CheckboxStrategy()
            "choice" -> ChoiceStrategy()
            "codeReader" -> CodeReaderStrategy()
            "col" -> ColStrategy()
            "email" -> EmailStrategy()
            "form" -> FormStrategy()
            "headline" -> HeadlineStrategy()
            "image" -> ImageStrategy()
            "italic" -> ItalicStrategy()
            "item" -> ItemStrategy()
            "items" -> ItemsStrategy()
            "link" -> LinkStrategy()
            "map" -> MapStrategy()
            "multipleChoice" -> MultipleChoiceStrategy()
            "overlay" -> OverlayStrategy()
            "overlays" -> OverlaysStrategy()
            "phone" -> PhoneStrategy()
            "reel" -> ReelStrategy()
            "reelValue" -> ReelValueStrategy()
            "row" -> RowStrategy()
            "selection" -> SelectionStrategy()
            "singleChoice" -> SingleChoiceStrategy()
            "slider" -> SliderStrategy()
            "slotmachine" -> SlotMachineStrategy()
            "slotMachine" -> SlotMachineStrategy()
            "container" -> ContainerStrategy()
            "spinner" -> SpinnerStrategy()
            "submit" -> SubmitStrategy()
            "suggestion" -> SuggestionStrategy()
            "table" -> TableStrategy()
            "text" -> TextStrategy()
            "#text" -> TextStrategy()
            "textInput" -> TextInputStrategy()
            "textarea" -> TextareaStrategy()
            "trigger" -> TriggerStrategy()
            "upload" -> UploadStrategy()
            "video" -> VideoStrategy()
            else -> throw IllegalArgumentException("unknown markup element $key")
        }
    }

    fun parse(stream: InputStream, validate: Boolean = true): List<IRenderable> {
        return parse(stream.reader(StandardCharsets.UTF_8).readText(), validate)
    }

    fun parse(str: String, validate: Boolean = true): List<IRenderable> {
        if (validate) {
            val validation = validator.validate(str)
            if (validation is XmlValidation.Failure) throw CanonException(validation.getMessage())
        }
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

        if (node.nodeName == "#comment")
            return
        if (node.nodeName == "markup") {
            toRenderables(node.childNodes, renderables)
            return
        }

        val strategy = resolveStrategy(node.nodeName)
        renderables.add(wrap(strategy.parse(node, this::toRenderables)))
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

}
