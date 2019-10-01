@file:Suppress("UNUSED_PARAMETER")

package canon.visitor

import canon.api.IRenderable
import canon.model.*
import canon.model.Map

abstract class AbstractVisitor {

    fun visitRenderable(renderable: IRenderable) {
        when (renderable) {

            is Block -> visitBlock(renderable)
            is Bold -> visitBold(renderable)
            is Break -> visitBreak(renderable)
            is Button -> visitButton(renderable)
            is Calendar -> visitCalendar(renderable)
            is Camera -> visitCamera(renderable)
            is Carousel -> visitCarousel(renderable)
            is Choice -> visitChoice(renderable)
            is CodeReader -> visitCodeReader(renderable)
            is Col -> visitCol(renderable)
            is Email -> visitEmail(renderable)
            is Form -> visitForm(renderable)
            is Headline -> visitHeadline(renderable)
            is Image -> visitImage(renderable)
            is Italic -> visitItalic(renderable)
            is Items -> visitItems(renderable)
            is Item -> visitItem(renderable)
            is Label -> visitLabel(renderable)
            is Link -> visitLink(renderable)
            is Map -> visitMap(renderable)
            is MultipleChoice -> visitMultipleChoice(renderable)
            is Overlay -> visitOverlay(renderable)
            is Overlays -> visitOverlays(renderable)
            is Phone -> visitPhone(renderable)
            is Reel -> visitReel(renderable)
            is ReelValue -> visitReelValue(renderable)
            is Row -> visitRow(renderable)
            is Selection -> visitSelection(renderable)
            is SingleChoice -> visitSingleChoice(renderable)
            is Slider -> visitSlider(renderable)
            is SlotMachine -> visitSlotMachine(renderable)
            is SmallDevice -> visitSmallDevice(renderable)
            is Spinner -> visitSpinner(renderable)
            is Submit -> visitSubmit(renderable)
            is Suggestion -> visitSuggestion(renderable)
            is Table -> visitTable(renderable)
            is Text -> visitText(renderable)
            is Textarea -> visitTextarea(renderable)
            is Transition -> visitTransition(renderable)
            is Trigger -> visitTrigger(renderable)
            is Upload -> visitUpload(renderable)
            is Video -> visitVideo(renderable)
        }
    }

    fun visitBlock(renderable: Block) {}
    fun visitBold(renderable: Bold) {}
    fun visitBreak(renderable: Break) {}
    fun visitButton(renderable: Button) {}
    fun visitCalendar(calendar: Calendar) {}
    fun visitCamera(camera: Camera) {}
    fun visitCarousel(renderable: Carousel) {}
    fun visitChoice(choice: Choice) {}
    fun visitCodeReader(renderable: CodeReader) {}
    fun visitCol(renderable: Col) {}
    fun visitEmail(renderable: Email) {}
    fun visitForm(renderable: Form) {}
    fun visitHeadline(renderable: Headline) {}
    fun visitImage(renderable: Image) {}
    fun visitItalic(renderable: Italic) {}
    fun visitItems(renderable: Items) {}
    fun visitItem(renderable: Item) {}
    fun visitLabel(renderable: Label) {}
    fun visitLink(renderable: Link) {}
    fun visitMap(map: Map) {}
    fun visitMultipleChoice(multipleChoice: MultipleChoice) {}
    fun visitOverlay(overlay: Overlay) {}
    fun visitOverlays(overlays: Overlays) {}
    fun visitPhone(renderable: Phone) {}
    fun visitReel(renderable: Reel) {}
    fun visitReelValue(renderable: ReelValue) {}
    fun visitRow(renderable: Row) {}
    fun visitSelection(renderable: Selection) {}
    fun visitSingleChoice(singleChoice: SingleChoice) {}
    fun visitSlider(renderable: Slider) {}
    fun visitSlotMachine(renderable: SlotMachine) {}
    fun visitSmallDevice(renderable: SmallDevice) {}
    fun visitSpinner(renderable: Spinner) {}
    fun visitSubmit(submit: Submit) {}
    fun visitSuggestion(suggestion: Suggestion) {}
    fun visitTable(renderable: Table) {}
    fun visitText(renderable: Text) {}
    fun visitTextarea(renderable: Textarea) {}
    fun visitTransition(renderable: Transition) {}
    fun visitTrigger(renderable: Trigger) {}
    fun visitUpload(renderable: Upload) {}
    fun visitVideo(renderable: Video) {}
}
