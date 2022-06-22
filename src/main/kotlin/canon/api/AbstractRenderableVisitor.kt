/*
 * Copyright (c) 2016-2022, Leftshift One
 * __________________
 * [2022] Leftshift One
 * All Rights Reserved.
 * NOTICE:  All information contained herein is, and remains
 * the property of Leftshift One and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Leftshift One
 * and its suppliers and may be covered by Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Leftshift One.
 */

package canon.api

import canon.model.*
import canon.model.Map

/**
 * Has implementations for every IRenderable type.
 */
abstract class AbstractRenderableVisitor<R> : IVisitor<R> {
    override fun visitRenderable(renderable: IRenderable?): R {
        return when (renderable) {
            is Basket -> visitBasket(renderable)
            is Block -> visitBlock(renderable)
            is Bold -> visitBold(renderable)
            is Break -> visitBreak(renderable)
            is Button -> visitButton(renderable)
            is Camera -> visitCamera(renderable)
            is Carousel -> visitCarousel(renderable)
            is Choice -> visitChoice(renderable)
            is CodeReader -> visitCodeReader(renderable)
            is Col -> visitCol(renderable)
            is Container -> visitContainer(renderable)
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
            is Rating -> visitRating(renderable)
            is Reel -> visitReel(renderable)
            is ReelValue -> visitReelValue(renderable)
            is Row -> visitRow(renderable)
            is Selectable -> visitSelectable(renderable)
            is Selection -> visitSelection(renderable)
            is SelectionItem -> visitSelectionItem(renderable)
            is SingleChoice -> visitSingleChoice(renderable)
            is Slider -> visitSlider(renderable)
            is SlotMachine -> visitSlotMachine(renderable)
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
            else -> throw IllegalArgumentException("renderable (${renderable?.getType()}) $renderable not implemented")
        }
    }

    abstract fun visitBasket(renderable: Basket): R
    abstract fun visitBlock(renderable: Block): R
    abstract fun visitBold(renderable: Bold): R
    abstract fun visitBreak(renderable: Break): R
    abstract fun visitButton(renderable: Button): R
    abstract fun visitCamera(renderable: Camera): R
    abstract fun visitCarousel(renderable: Carousel): R
    abstract fun visitChoice(renderable: Choice): R
    abstract fun visitCodeReader(renderable: CodeReader): R
    abstract fun visitCol(renderable: Col): R
    abstract fun visitContainer(renderable: Container): R
    abstract fun visitEmail(renderable: Email): R
    abstract fun visitForeach(renderable: Foreach): R
    abstract fun visitForm(renderable: Form): R
    abstract fun visitHeadline(renderable: Headline): R
    abstract fun visitIf(renderable: If): R
    abstract fun visitImage(renderable: Image): R
    abstract fun visitItalic(renderable: Italic): R
    abstract fun visitItems(renderable: Items): R
    abstract fun visitItem(renderable: Item): R
    abstract fun visitLabel(renderable: Label): R
    abstract fun visitLink(renderable: Link): R
    abstract fun visitMap(renderable: Map): R
    abstract fun visitMultipleChoice(renderable: MultipleChoice): R
    abstract fun visitOverlay(renderable: Overlay): R
    abstract fun visitOverlays(renderable: Overlays): R
    abstract fun visitPhone(renderable: Phone): R
    abstract fun visitRating(renderable: Rating): R
    abstract fun visitReel(renderable: Reel): R
    abstract fun visitReelValue(renderable: ReelValue): R
    abstract fun visitRow(renderable: Row): R
    abstract fun visitSelectable(renderable: Selectable): R
    abstract fun visitSelection(renderable: Selection): R
    abstract fun visitSelectionItem(renderable: SelectionItem): R
    abstract fun visitSingleChoice(renderable: SingleChoice): R
    abstract fun visitSlider(renderable: Slider): R
    abstract fun visitSlotMachine(renderable: SlotMachine): R
    abstract fun visitSpinner(renderable: Spinner): R
    abstract fun visitSubmit(renderable: Submit): R
    abstract fun visitSuggestion(renderable: Suggestion): R
    abstract fun visitTable(renderable: Table): R
    abstract fun visitText(renderable: Text): R
    abstract fun visitTextarea(renderable: Textarea): R
    abstract fun visitTransition(renderable: Transition): R
    abstract fun visitTrigger(renderable: Trigger): R
    abstract fun visitUpload(renderable: Upload): R
    abstract fun visitVideo(renderable: Video): R
}