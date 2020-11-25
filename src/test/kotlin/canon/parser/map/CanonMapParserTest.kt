package canon.parser.map

import canon.api.IRenderable
import canon.model.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.reflect.KClass

class CanonMapParserTest {

    @Test fun complexTest() {
        val renderableList = listOf<Map<String, Any>>().toMutableList()
        renderableList.add(mapOf(
                        "type" to "label",
                        "text" to "Hier steht ein Text"))
        renderableList.add(mapOf(
                "type" to "block",
                "elements" to listOf<Map<String, Any>>( mapOf(
                        "type" to "button",
                        "text" to "Button A",
                        "name" to "btnA"
                ), mapOf(
                        "type" to "button",
                        "text" to "Button B",
                        "name" to "btnB"
                ))))
        val parsedRenderables = renderableList.map { map -> CanonMapParser.parse(map) }

        assertEquals(2, parsedRenderables.size)
        assertTrue(parsedRenderables[0] is Label)
        assertTrue(parsedRenderables[1] is Block)
        assertTrue((parsedRenderables[1] as Block).renderables?.get(0) is Button)
        assertTrue((parsedRenderables[1] as Block).renderables?.get(1) is Button)
    }

    @Test fun testBasket() = testParser(Basket::class)
    @Test fun testBlock() = testParser(Block::class)
    @Test fun testBold() = testParser(Bold::class)
    @Test fun testBreak() = testParser(Break::class)
    @Test fun testButton() = testParser(Button::class)
    @Test fun testCamera() = testParser(Camera::class)
    @Test fun testCarouse() = testParser(Carousel::class)
    @Test fun testChoice() = testParser(Choice::class)
    @Test fun testCodeReader() = testParser(CodeReader::class)
    @Test fun testCol() = testParser(Col::class)
    @Test fun testContainer() = testParser(Container::class)
    @Test fun testEmail() = testParser(Email::class)
    @Test fun testForeach() = testParser(Foreach::class)
    @Test fun testForm() = testParser(Form::class)
    @Test fun testHeadline() = testParser(Headline::class)
    @Test fun testIf() = testParser(If::class)
    @Test fun testImage() = testParser(Image::class)
    @Test fun testItalic() = testParser(Italic::class)
    @Test fun testItem() = testParser(Item::class)
    @Test fun testItems() = testParser(Items::class)
    @Test fun testLabel() = testParser(Label::class)
    @Test fun testLink() = testParser(Link::class)
    @Test fun testMap() = testParser(canon.model.Map::class)
    @Test fun testMultipleChoice() = testParser(MultipleChoice::class)
    @Test fun testOverlay() = testParser(Overlay::class)
    @Test fun testOverlays() = testParser(Overlays::class)
    @Test fun testPhone() = testParser(Phone::class)
    @Test fun testReel() = testParser(Reel::class)
    @Test fun testReelValue() = testParser(ReelValue::class)
    @Test fun testRow() = testParser(Row::class)
    @Test fun testSelectable() = testParser(Selectable::class)
    @Test fun testSelection() = testParser(Selection::class)
    @Test fun testSelectionItem() = testParser(SelectionItem::class)
    @Test fun testSingleChoice() = testParser(SingleChoice::class)
    @Test fun testSlider() = testParser(Slider::class)
    @Test fun testSlotMachine() = testParser(SlotMachine::class)
    @Test fun testSpinner() = testParser(Spinner::class)
    @Test fun testSubmit() = testParser(Submit::class)
    @Test fun testSuggestion() = testParser(Suggestion::class)
    @Test fun testTable() = testParser(Table::class)
    @Test fun testText() = testParser(Text::class)
    @Test fun testTextarea() = testParser(Textarea::class)
    @Test fun testTransition() = testParser(Transition::class)
    @Test fun testTrigger() = testParser(Trigger::class)
    @Test fun testUpload() = testParser(Upload::class)
    @Test fun testVideo() = testParser(Video::class)

    @Test fun testConversionFailure() {
        val errorMap = mapOf("type" to "abc")
        val ex = assertThrows<RuntimeException> { CanonMapParser.parse(errorMap) }
        assertEquals("cannot convert $errorMap", ex.message)
    }

    private fun testParser(exp: KClass<out IRenderable>) {
        val map = mapOf("type" to exp.java.simpleName.decapitalize())
        assertEquals(exp, CanonMapParser.parse(map)::class)
    }

}