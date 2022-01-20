@file:Suppress("UNUSED_PARAMETER")

package canon.visitor

import canon.api.AbstractRenderableVisitor
import canon.model.*
import canon.model.Map

typealias KMap = kotlin.collections.Map<String, Any>

abstract class AbstractVisitor<R>(private val context: KMap) : AbstractRenderableVisitor<List<R>>() {

    override fun getContext() = context

    override fun visitBasket(renderable: Basket) = listOf<R>()
    override fun visitBlock(renderable: Block) = listOf<R>()
    override fun visitBold(renderable: Bold) = listOf<R>()
    override fun visitBreak(renderable: Break) = listOf<R>()
    override fun visitButton(renderable: Button) = listOf<R>()
    override fun visitCamera(renderable: Camera) = listOf<R>()
    override fun visitCarousel(renderable: Carousel) = listOf<R>()
    override fun visitChoice(renderable: Choice) = listOf<R>()
    override fun visitCodeReader(renderable: CodeReader) = listOf<R>()
    override fun visitCol(renderable: Col) = listOf<R>()
    override fun visitContainer(renderable: Container) = listOf<R>()
    override fun visitEmail(renderable: Email) = listOf<R>()
    override fun visitForeach(renderable: Foreach) = listOf<R>()
    override fun visitForm(renderable: Form) = listOf<R>()
    override fun visitHeadline(renderable: Headline) = listOf<R>()
    override fun visitIf(renderable: If) = listOf<R>()
    override fun visitImage(renderable: Image) = listOf<R>()
    override fun visitItalic(renderable: Italic) = listOf<R>()
    override fun visitItems(renderable: Items) = listOf<R>()
    override fun visitItem(renderable: Item) = listOf<R>()
    override fun visitLabel(renderable: Label) = listOf<R>()
    override fun visitLink(renderable: Link) = listOf<R>()
    override fun visitMap(renderable: Map) = listOf<R>()
    override fun visitMultipleChoice(renderable: MultipleChoice) = listOf<R>()
    override fun visitOverlay(renderable: Overlay) = listOf<R>()
    override fun visitOverlays(renderable: Overlays) = listOf<R>()
    override fun visitPhone(renderable: Phone) = listOf<R>()
    override fun visitRating(renderable: Rating) = listOf<R>()
    override fun visitReel(renderable: Reel) = listOf<R>()
    override fun visitReelValue(renderable: ReelValue) = listOf<R>()
    override fun visitRow(renderable: Row) = listOf<R>()
    override fun visitSelectable(renderable: Selectable) = listOf<R>()
    override fun visitSelection(renderable: Selection) = listOf<R>()
    override fun visitSelectionItem(renderable: SelectionItem) = listOf<R>()
    override fun visitSingleChoice(renderable: SingleChoice) = listOf<R>()
    override fun visitSlider(renderable: Slider) = listOf<R>()
    override fun visitSlotMachine(renderable: SlotMachine) = listOf<R>()
    override fun visitSpinner(renderable: Spinner) = listOf<R>()
    override fun visitSubmit(renderable: Submit) = listOf<R>()
    override fun visitSuggestion(renderable: Suggestion) = listOf<R>()
    override fun visitTable(renderable: Table) = listOf<R>()
    override fun visitText(renderable: Text) = listOf<R>()
    override fun visitTextarea(renderable: Textarea) = listOf<R>()
    override fun visitTransition(renderable: Transition) = listOf<R>()
    override fun visitTrigger(renderable: Trigger) = listOf<R>()
    override fun visitUpload(renderable: Upload) = listOf<R>()
    override fun visitVideo(renderable: Video) = listOf<R>()
}
