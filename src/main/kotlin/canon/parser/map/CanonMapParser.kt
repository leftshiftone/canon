package canon.parser.map

import canon.api.IRenderable
import canon.parser.map.strategy.*

class CanonMapParser {

    companion object {
        fun parse(map: Map<String, Any>): IRenderable {
            val factory: (Map<String, Any>) -> List<IRenderable> = { m: Map<String, Any> ->
                @Suppress("UNCHECKED_CAST")
                if (m.containsKey("elements")) (m["elements"] as List<Map<String, Any>>).map(this::parse)
                else emptyList()
            }

            return when (map["type"]) {
                "basket"            -> BasketStrategy().parse(map, factory)
                "block"             -> BlockStrategy().parse(map, factory)
                "bold"              -> BoldStrategy().parse(map, factory)
                "break"             -> BreakStrategy().parse(map, factory)
                "button"            -> ButtonStrategy().parse(map, factory)
                "camera"            -> CameraStrategy().parse(map, factory)
                "carousel"          -> CarouselStrategy().parse(map, factory)
                "choice"            -> ChoiceStrategy().parse(map, factory)
                "codeReader"        -> CodeReaderStrategy().parse(map, factory)
                "col"               -> ColStrategy().parse(map, factory)
                "container"         -> ContainerStrategy().parse(map, factory)
                "email"             -> EmailStrategy().parse(map, factory)
                "foreach"           -> ForeachStrategy().parse(map, factory)
                "form"              -> FormStrategy().parse(map, factory)
                "headline"          -> HeadlineStrategy().parse(map, factory)
                "if"                -> IfStrategy().parse(map, factory)
                "image"             -> ImageStrategy().parse(map, factory)
                "italic"            -> ItalicStrategy().parse(map, factory)
                "item"              -> ItemStrategy().parse(map, factory)
                "items"             -> ItemsStrategy().parse(map, factory)
                "label"             -> LabelStrategy().parse(map, factory)
                "link"              -> LinkStrategy().parse(map, factory)
                "map"               -> MapStrategy().parse(map, factory)
                "multipleChoice"    -> MultipleChoiceStrategy().parse(map, factory)
                "overlay"           -> OverlayStrategy().parse(map, factory)
                "overlays"          -> OverlaysStrategy().parse(map, factory)
                "phone"             -> PhoneStrategy().parse(map, factory)
                "rating"            -> RatingStrategy().parse(map, factory)
                "reel"              -> ReelStrategy().parse(map, factory)
                "reelValue"         -> ReelValueStrategy().parse(map, factory)
                "row"               -> RowStrategy().parse(map, factory)
                "selectable"        -> SelectableStrategy().parse(map, factory)
                "selection"         -> SelectionStrategy().parse(map, factory)
                "selectionItem"     -> SelectionItemStrategy().parse(map, factory)
                "singleChoice"      -> SingleChoiceStrategy().parse(map, factory)
                "slider"            -> SliderStrategy().parse(map, factory)
                "slotMachine"       -> SlotMachineStrategy().parse(map, factory)
                "spinner"           -> SpinnerStrategy().parse(map, factory)
                "submit"            -> SubmitStrategy().parse(map, factory)
                "suggestion"        -> SuggestionStrategy().parse(map, factory)
                "table"             -> TableStrategy().parse(map, factory)
                "text"              -> TextStrategy().parse(map, factory)
                "textarea"          -> TextareaStrategy().parse(map, factory)
                "transition"        -> TransitionStrategy().parse(map, factory)
                "trigger"           -> TriggerStrategy().parse(map, factory)
                "upload"            -> UploadStrategy().parse(map, factory)
                "video"             -> VideoStrategy().parse(map, factory)
                else -> throw RuntimeException("cannot convert $map")
            }
        }
    }

}