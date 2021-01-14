package canon.visitor

import canon.api.IEnrichable
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IStackable
import canon.model.*
import java.time.Instant
import java.util.*
import java.util.concurrent.atomic.AtomicInteger

open class GaiaVisitor(map: Map<String, Any>,
                       private val evaluator: IEvaluator) : AbstractVisitor<Map<String, Any>>(map) {
    private val counter = AtomicInteger()

    override fun wrap(context: Map<String, Any>) = GaiaVisitor(context, evaluator)

    override fun visitText(renderable: Text) = listOf(toMap(renderable))
    override fun visitBasket(renderable: Basket) = listOf(toMap(renderable))
    override fun visitBlock(renderable: Block) = listOf(toMap(renderable))
    override fun visitBold(renderable: Bold) = listOf(toMap(renderable))
    override fun visitBreak(renderable: Break) = listOf(toMap(renderable))
    override fun visitButton(renderable: Button) = listOf(toMap(renderable))
    override fun visitCamera(renderable: Camera) = listOf(toMap(renderable))
    override fun visitCarousel(renderable: Carousel) = listOf(toMap(renderable))
    override fun visitChoice(renderable: Choice) = listOf(toMap(renderable))
    override fun visitCodeReader(renderable: CodeReader) = listOf(toMap(renderable))
    override fun visitCol(renderable: Col) = listOf(toMap(renderable))
    override fun visitContainer(renderable: Container) = listOf(toMap(renderable))
    override fun visitEmail(renderable: Email) = listOf(toMap(renderable))
    override fun visitForm(renderable: Form) = listOf(toMap(renderable))
    override fun visitHeadline(renderable: Headline) = listOf(toMap(renderable))
    override fun visitImage(renderable: Image) = listOf(toMap(renderable))
    override fun visitItalic(renderable: Italic) = listOf(toMap(renderable))
    override fun visitItem(renderable: Item) = listOf(toMap(renderable))
    override fun visitItems(renderable: Items) = listOf(toMap(renderable))
    override fun visitLabel(renderable: Label) = listOf(toMap(renderable))
    override fun visitLink(renderable: Link) = listOf(toMap(renderable))
    override fun visitMap(renderable: canon.model.Map) = listOf(toMap(renderable))
    override fun visitMultipleChoice(renderable: MultipleChoice) = listOf(toMap(renderable))
    override fun visitOverlay(renderable: Overlay) = listOf(toMap(renderable))
    override fun visitOverlays(renderable: Overlays) = listOf(toMap(renderable))
    override fun visitPhone(renderable: Phone) = listOf(toMap(renderable))
    override fun visitRating(renderable: Rating) = listOf(toMap(renderable))
    override fun visitReel(renderable: Reel) = listOf(toMap(renderable))
    override fun visitReelValue(renderable: ReelValue) = listOf(toMap(renderable))
    override fun visitRow(renderable: Row) = listOf(toMap(renderable))
    override fun visitSelectable(renderable: Selectable) = listOf(toMap(renderable))
    override fun visitSelection(renderable: Selection) = listOf(toMap(renderable))
    override fun visitSelectionItem(renderable: SelectionItem) = listOf(toMap(renderable))
    override fun visitSingleChoice(renderable: SingleChoice) = listOf(toMap(renderable))
    override fun visitSlider(renderable: Slider) = listOf(toMap(renderable))
    override fun visitSlotMachine(renderable: SlotMachine) = listOf(toMap(renderable))
    override fun visitSpinner(renderable: Spinner) = listOf(toMap(renderable))
    override fun visitSubmit(renderable: Submit) = listOf(toMap(renderable))
    override fun visitSuggestion(renderable: Suggestion) = listOf(toMap(renderable))
    override fun visitTable(renderable: Table) = listOf(toMap(renderable))
    override fun visitTextarea(renderable: Textarea) = listOf(toMap(renderable))
    override fun visitTransition(renderable: Transition) = listOf(toMap(renderable))
    override fun visitTrigger(renderable: Trigger) = listOf(toMap(renderable))
    override fun visitUpload(renderable: Upload) = listOf(toMap(renderable))
    override fun visitVideo(renderable: Video) = listOf(toMap(renderable))

    protected open fun getTimestamp():Long = Instant.now().epochSecond

    private fun toMap(renderable: IRenderable, context: Map<String, Any> = getContext()): Map<String, Any> {
        val map = HashMap<String, Any>()
        map["type"] = renderable.getType()
        map.putAll(renderable.toMap(context, evaluator))
        map["timestamp"] = getTimestamp()
        map["index"] = counter.getAndIncrement()

        if (renderable is IStackable) {
            val visitor = EmbeddedVisitor(context, evaluator)
            map["elements"] = renderable.accept(visitor, evaluator)
        }
        if (renderable is IEnrichable) {
            map.putAll(renderable.getEnriched())
        }
        return map
    }

    override fun merge(r1: List<Map<String, Any>>, r2: List<Map<String, Any>>): List<Map<String, Any>> {
        val result = ArrayList<Map<String, Any>>()
        result.addAll(r1)
        result.addAll(r2)
        return result
    }

    override fun empty(): List<Map<String, Any>> = listOf()

    private class EmbeddedVisitor(map: Map<String, Any>, val evaluator: IEvaluator) : AbstractVisitor<Map<String, Any?>>(map) {

        override fun visitBasket(renderable: Basket) = listOf(toMap(renderable))
        override fun visitBlock(renderable: Block) = listOf(toMap(renderable))
        override fun visitBold(renderable: Bold) = listOf(toMap(renderable))
        override fun visitBreak(renderable: Break) = listOf(toMap(renderable))
        override fun visitButton(renderable: Button) = listOf(toMap(renderable))
        override fun visitCamera(renderable: Camera) = listOf(toMap(renderable))
        override fun visitCarousel(renderable: Carousel) = listOf(toMap(renderable))
        override fun visitChoice(renderable: Choice) = listOf(toMap(renderable))
        override fun visitCodeReader(renderable: CodeReader) = listOf(toMap(renderable))
        override fun visitCol(renderable: Col) = listOf(toMap(renderable))
        override fun visitContainer(renderable: Container) = listOf(toMap(renderable))
        override fun visitEmail(renderable: Email) = listOf(toMap(renderable))
        override fun visitForm(renderable: Form) = listOf(toMap(renderable))
        override fun visitHeadline(renderable: Headline) = listOf(toMap(renderable))
        override fun visitImage(renderable: Image) = listOf(toMap(renderable))
        override fun visitItalic(renderable: Italic) = listOf(toMap(renderable))
        override fun visitItem(renderable: Item) = listOf(toMap(renderable))
        override fun visitItems(renderable: Items) = listOf(toMap(renderable))
        override fun visitLabel(renderable: Label) = listOf(toMap(renderable))
        override fun visitLink(renderable: Link) = listOf(toMap(renderable))
        override fun visitMap(renderable: canon.model.Map) = listOf(toMap(renderable))
        override fun visitMultipleChoice(renderable: MultipleChoice) = listOf(toMap(renderable))
        override fun visitOverlay(renderable: Overlay) = listOf(toMap(renderable))
        override fun visitOverlays(renderable: Overlays) = listOf(toMap(renderable))
        override fun visitPhone(renderable: Phone) = listOf(toMap(renderable))
        override fun visitRating(renderable: Rating) = listOf(toMap(renderable))
        override fun visitReel(renderable: Reel) = listOf(toMap(renderable))
        override fun visitReelValue(renderable: ReelValue) = listOf(toMap(renderable))
        override fun visitRow(renderable: Row) = listOf(toMap(renderable))
        override fun visitSelectable(renderable: Selectable) = listOf(toMap(renderable))
        override fun visitSelection(renderable: Selection) = listOf(toMap(renderable))
        override fun visitSelectionItem(renderable: SelectionItem) = listOf(toMap(renderable))
        override fun visitSingleChoice(renderable: SingleChoice) = listOf(toMap(renderable))
        override fun visitSlider(renderable: Slider) = listOf(toMap(renderable))
        override fun visitSlotMachine(renderable: SlotMachine) = listOf(toMap(renderable))
        override fun visitSpinner(renderable: Spinner) = listOf(toMap(renderable))
        override fun visitSubmit(renderable: Submit) = listOf(toMap(renderable))
        override fun visitSuggestion(renderable: Suggestion) = listOf(toMap(renderable))
        override fun visitTable(renderable: Table) = listOf(toMap(renderable))
        override fun visitText(renderable: Text) = listOf(toMap(renderable))
        override fun visitTextarea(renderable: Textarea) = listOf(toMap(renderable))
        override fun visitTransition(renderable: Transition) = listOf(toMap(renderable))
        override fun visitTrigger(renderable: Trigger) = listOf(toMap(renderable))
        override fun visitUpload(renderable: Upload) = listOf(toMap(renderable))
        override fun visitVideo(renderable: Video) = listOf(toMap(renderable))

        override fun visitForeach(renderable: Foreach): List<Map<String, Any?>> {
            val visitor = EmbeddedVisitor(getContext(), evaluator)
            return renderable.accept(visitor, evaluator)
        }

        override fun visitIf(renderable: If): List<Map<String, Any?>> {
            val visitor = EmbeddedVisitor(getContext(), evaluator)
            return renderable.accept(visitor, evaluator)
        }

        private fun toMap(renderable: IRenderable, context: Map<String, Any> = getContext()): Map<String, Any> {
            val map = HashMap<String, Any>()
            map["type"] = renderable.getType()
            map.putAll(renderable.toMap(context, evaluator))

            if (renderable is IStackable) {
                val visitor = EmbeddedVisitor(context, evaluator)
                map["elements"] = renderable.accept(visitor, evaluator)
            }
            if (renderable is IEnrichable) {
                map.putAll(renderable.getEnriched())
            }
            return map
        }

        override fun wrap(context: Map<String, Any>) = EmbeddedVisitor(context, evaluator)

        override fun merge(r1: List<Map<String, Any?>>, r2: List<Map<String, Any?>>): List<Map<String, Any?>> {
            val result = ArrayList<Map<String, Any?>>()
            result.addAll(r1)
            result.addAll(r2)
            return result
        }

        override fun empty(): List<Map<String, Any?>> = listOf()

    }

}
