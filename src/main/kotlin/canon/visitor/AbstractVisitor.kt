@file:Suppress("UNUSED_PARAMETER")

package canon.visitor

import canon.api.IRenderable
import canon.model.*
import canon.model.Map

abstract class AbstractVisitor {

    fun visitRenderable(renderable: IRenderable) {
        when (renderable) {
            is Carousel -> visitCarousel(renderable)
            is Block -> visitBlock(renderable)
            is Item -> visitItem(renderable)
            is Items -> visitItems(renderable)
            is Text -> visitText(renderable)
            is Button -> visitButton(renderable)
            is Image -> visitImage(renderable)
            is Headline -> visitHeadline(renderable)
            is Link -> visitLink(renderable)
            is Bold -> visitBold(renderable)
            is Break -> visitBreak(renderable)
            is Calendar -> visitCalendar(renderable)
            is SmallDevice -> visitSmallDevice(renderable)
            is Table -> visitTable(renderable)
            is Row -> visitRow(renderable)
            is Col -> visitCol(renderable)
            is Map -> visitMap(renderable)
            is Submit -> visitSubmit(renderable)
            is Slider -> visitSlider(renderable)
            is Spinner -> visitSpinner(renderable)
            is Label -> visitLabel(renderable)
            is Phone -> visitPhone(renderable)
            is Upload -> visitUpload(renderable)
            is Email -> visitEmail(renderable)
            is Reel -> visitReel(renderable)
            is Form -> visitForm(renderable)
            is SlotMachine -> visitSlotMachine(renderable)
            is Suggestion -> visitSuggestion(renderable)
            is Selection -> visitSelection(renderable)
            is CodeReader -> visitCodeReader(renderable)
            is Textarea -> visitTextarea(renderable)
            is ReelValue -> visitReelValue(renderable)
            is Camera -> visitCamera(renderable)
            is MultipleChoice -> visitMultipleChoice(renderable)
            is SingleChoice -> visitSingleChoice(renderable)
            is Choice -> visitChoice(renderable)
        }
    }

    fun visitTextarea(renderable: Textarea) {}
    fun visitCodeReader(renderable: CodeReader) {}
    fun visitSelection(renderable: Selection) {}
    fun visitUpload(renderable: Upload) {}
    fun visitLabel(renderable: Label) {}
    fun visitPhone(renderable: Phone) {}
    fun visitEmail(renderable: Email) {}
    fun visitForm(renderable: Form) {}
    fun visitReel(renderable: Reel) {}
    fun visitReelValue(renderable: ReelValue) {}
    fun visitSlotMachine(renderable: SlotMachine) {}
    fun visitSlider(renderable: Slider) {}
    fun visitSpinner(renderable: Spinner) {}
    fun visitTable(renderable: Table) {}
    fun visitRow(renderable: Row) {}
    fun visitCol(renderable: Col) {}
    fun visitBreak(renderable: Break) {}
    fun visitCalendar(calendar: Calendar) {}
    fun visitBold(renderable: Bold) {}
    fun visitLink(renderable: Link) {}
    fun visitCarousel(renderable: Carousel) {}
    fun visitBlock(renderable: Block) {}
    fun visitItem(renderable: Item) {}
    fun visitText(renderable: Text) {}
    fun visitItems(renderable: Items) {}
    fun visitButton(renderable: Button) {}
    fun visitImage(renderable: Image) {}
    fun visitHeadline(renderable: Headline) {}
    fun visitSmallDevice(renderable: SmallDevice) {}
    fun visitMap(map: Map) {}
    fun visitSubmit(submit: Submit) {}
    fun visitSuggestion(suggestion: Suggestion) {}
    fun visitCamera(camera: Camera) {}
    fun visitSingleChoice(singleChoice: SingleChoice) {}
    fun visitMultipleChoice(multipleChoice: MultipleChoice) {}
    fun visitChoice(choice: Choice) {}

}
