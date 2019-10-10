@file:Suppress("UNUSED_PARAMETER")

package canon.visitor

import canon.api.IRenderable
import canon.api.IVisitor
import canon.model.*
import canon.model.Map
import kotlin.collections.Map as KotlinMap

abstract class AbstractVisitor(context:KotlinMap<String, Any>) : IVisitor {

    override fun visitRenderable(renderable: IRenderable?) {
        when (renderable) {

            is Block -> visitBlock(renderable)
            is Bold -> visitBold(renderable)
            is Break -> visitBreak(renderable)
            is Button -> visitButton(renderable)
            is Calendar -> visitCalendar(renderable)
            is Camera -> visitCamera(renderable)
            is Carousel -> visitCarousel(renderable)
            is Checkbox -> visitCheckbox(renderable)
            is Choice -> visitChoice(renderable)
            is CodeReader -> visitCodeReader(renderable)
            is Col -> visitCol(renderable)
            is Email -> visitEmail(renderable)
            is Foreach -> visitForeach(renderable)
            is Form -> visitForm(renderable)
            is Headline -> visitHeadline(renderable)
            is If -> visitIf(renderable)
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

    open fun visitBlock(renderable: Block) {}
    open fun visitBold(renderable: Bold) {}
    open fun visitBreak(renderable: Break) {}
    open fun visitButton(renderable: Button) {}
    open fun visitCalendar(calendar: Calendar) {}
    open fun visitCamera(camera: Camera) {}
    open fun visitCarousel(renderable: Carousel) {}
    open fun visitCheckbox(renderable: Checkbox) {}
    open fun visitChoice(choice: Choice) {}
    open fun visitCodeReader(renderable: CodeReader) {}
    open fun visitCol(renderable: Col) {}
    open fun visitEmail(renderable: Email) {}
    open fun visitForeach(renderable: Foreach) {}
    open fun visitForm(renderable: Form) {}
    open fun visitHeadline(renderable: Headline) {}
    open fun visitIf(renderable: If) {}
    open fun visitImage(renderable: Image) {}
    open fun visitItalic(renderable: Italic) {}
    open fun visitItems(renderable: Items) {}
    open fun visitItem(renderable: Item) {}
    open fun visitLabel(renderable: Label) {}
    open fun visitLink(renderable: Link) {}
    open fun visitMap(map: Map) {}
    open fun visitMultipleChoice(multipleChoice: MultipleChoice) {}
    open fun visitOverlay(overlay: Overlay) {}
    open fun visitOverlays(overlays: Overlays) {}
    open fun visitPhone(renderable: Phone) {}
    open fun visitReel(renderable: Reel) {}
    open fun visitReelValue(renderable: ReelValue) {}
    open fun visitRow(renderable: Row) {}
    open fun visitSelection(renderable: Selection) {}
    open fun visitSingleChoice(singleChoice: SingleChoice) {}
    open fun visitSlider(renderable: Slider) {}
    open fun visitSlotMachine(renderable: SlotMachine) {}
    open fun visitSmallDevice(renderable: SmallDevice) {}
    open fun visitSpinner(renderable: Spinner) {}
    open fun visitSubmit(submit: Submit) {}
    open fun visitSuggestion(suggestion: Suggestion) {}
    open fun visitTable(renderable: Table) {}
    open fun visitText(renderable: Text) {}
    open fun visitTextarea(renderable: Textarea) {}
    open fun visitTransition(renderable: Transition) {}
    open fun visitTrigger(renderable: Trigger) {}
    open fun visitUpload(renderable: Upload) {}
    open fun visitVideo(renderable: Video) {}
}
