package canon.parser.dsl

import canon.api.IRenderable
import canon.model.*
import canon.model.Map

class CanonDSLParser {

    companion object {
        fun parse(config: CanonDSL.() -> Unit): List<IRenderable> {
            return CanonDSL().apply(config)
        }
    }

    class CanonDSL: ArrayList<IRenderable>() {

        fun basket(id: String?, `class`: String?, ariaLabel: String?, name: String?, required: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(Basket(id, `class`, ariaLabel, name, required, CanonDSL().apply(config ?: {})))
        }

        fun block(id: String?, `class`: String?, ariaLabel: String?, name: String?, config: (CanonDSL.() -> Unit)?){
            add(Block(id, `class`, ariaLabel, name, CanonDSL().apply(config ?: {})))
        }

        fun bold(id: String?, `class`: String?, ariaLabel: String?, text: String?) {
            add(Bold(id, `class`, ariaLabel, text))
        }

        fun `break`(id: String?, `class`: String?, ariaLabel: String?) {
            add(Break(id, `class`, ariaLabel))
        }

        fun button(id: String?, `class`: String?, ariaLabel: String?, text: String?, name: String?, value: String?, config: (CanonDSL.() -> Unit)?){
            add(Button(id, `class`, ariaLabel, text, name, value, CanonDSL().apply(config ?: {})))
        }

        fun camera(id: String?, `class`: String?, ariaLabel: String?, name: String?, required: Boolean?, maxCompressSize: Double?, config: (CanonDSL.() -> Unit)?) {
            add(Camera(id, `class`, ariaLabel, name, required, maxCompressSize, CanonDSL().apply(config ?: {})))
        }

        fun carousel(id: String?, `class`: String?, ariaLabel: String?, text: String?, name: String?, selected: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(Carousel(id, `class`, ariaLabel, text, name, selected, CanonDSL().apply(config ?: {})))
        }

        fun choice(id: String?, `class`: String?, ariaLabel: String?, name: String?, text: String?, selected: String?, config: (CanonDSL.() -> Unit)?) {
            add(Choice(id, `class`, ariaLabel, name, text, selected, CanonDSL().apply(config ?: {})))
        }

        fun codeReader(id: String?, `class`: String?, ariaLabel: String?, name: String?, format: String?) {
            add(CodeReader(id, `class`, ariaLabel, name, format))
        }

        fun col(id: String?, `class`: String?, ariaLabel: String?, config: (CanonDSL.() -> Unit)?) {
            add(Col(id, `class`, ariaLabel, CanonDSL().apply(config ?: {})))
        }

        fun container(id: String?, `class`: String?, ariaLabel: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Container(id, `class`, ariaLabel, name, CanonDSL().apply(config ?: {})))
        }

        fun email(id: String?, `class`: String?, ariaLabel: String?, placeholder: String?, required: Boolean?, name: String?, value: String?) {
            add(Email(id, `class`, ariaLabel, placeholder, required, name, value))
        }

        fun foreach(forEachStmt: String?, renderable: IRenderable?) {
            add(Foreach(forEachStmt, renderable))
        }

        fun form(id: String?, `class`: String?, ariaLabel: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Form(id, `class`, ariaLabel, name, CanonDSL().apply(config ?: {})))
        }

        fun headline(id: String?, `class`: String?, ariaLabel: String?, text: String?) {
            add(Headline(id, `class`, ariaLabel, text))
        }

        fun `if`(expression: String?, renderable: IRenderable?) {
            add(If(expression, renderable))
        }

        fun image(id: String?, `class`: String?, ariaLabel: String?, src: String?, width: String?, height: String?, alt: String?) {
            add(Image(id, `class`, ariaLabel, src, width, height, alt))
        }

        fun italic(id: String?, `class`: String?, ariaLabel: String?, text: String?) {
            add(Italic(id, `class`, ariaLabel, text))
        }

        fun item(id: String?, `class`: String?, ariaLabel: String?, config: (CanonDSL.() -> Unit)?) {
            add(Item(id, `class`, ariaLabel, CanonDSL().apply(config ?: {})))
        }

        fun items(id: String?, `class`: String?, ariaLabel: String?, ordered: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(Items(id, `class`, ariaLabel, ordered, CanonDSL().apply(config ?: {})))
        }

        fun label(id: String?, `class`: String?, ariaLabel: String?, text: String?) {
            add(Label(id, `class`, ariaLabel, text))
        }

        fun link(id: String?, `class`: String?, ariaLabel: String?, value: String?, text: String?) {
            add(Link(id, `class`, ariaLabel, value, text))
        }

        fun map(id: String?, `class`: String?, ariaLabel: String?, name: String?, src: String?, mapType: String?, centerLng: String?,
                centerLat: String?, markerIcon: String?, selectedMarkerIcon: String?, routeStartIcon: String?,
                routeEndIcon: String?, routePoints: String?, centerBrowserLocation: Boolean?, required: Boolean?,
                zoom: String?, maxSelections: Int?, zoomByRadius:  String?) {
            add(Map(id, `class`, ariaLabel, name, src, mapType, centerLng, centerLat, markerIcon, selectedMarkerIcon,
                    routeStartIcon, routeEndIcon, routePoints, centerBrowserLocation, required, zoom, maxSelections,
                    zoomByRadius))
        }

        fun multipleChoice(id: String?, `class`: String?, ariaLabel: String?, name: String?, sieve: Boolean?, required: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(MultipleChoice(id, `class`, ariaLabel, name, sieve, required, CanonDSL().apply(config ?: {})))
        }

        fun overlay(id: String?, `class`: String?, ariaLabel: String?, trigger: String?, config: (CanonDSL.() -> Unit)?) {
            add(Overlay(id, `class`, ariaLabel, trigger, CanonDSL().apply(config ?: {})))
        }

        fun overlays(id: String?, `class`: String?, ariaLabel: String?, trigger: String?, config: (CanonDSL.() -> Unit)?) {
            add(Overlays(id, `class`, ariaLabel, trigger, CanonDSL().apply(config ?: {})))
        }

        fun phone(id: String?, `class`: String?, ariaLabel: String?, placeholder: String?, required: Boolean?, name: String?, value: String?) {
            add(Phone(id, `class`, ariaLabel, placeholder, required, name, value))
        }

        fun reel(id: String?, `class`: String?, ariaLabel: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Reel(id, `class`, ariaLabel, name, CanonDSL().apply(config ?: {})))
        }

        fun reelValue(id: String?, `class`: String?, ariaLabel: String?, value: String?, valueType: String?) {
            add(ReelValue(id, `class`, ariaLabel, value, valueType))
        }

        fun row(id: String?, `class`: String?, ariaLabel: String?, config: (CanonDSL.() -> Unit)?) {
            add(Row(id, `class`, ariaLabel, CanonDSL().apply(config ?: {})))
        }

        fun selectable(id: String?, `class`: String?, ariaLabel: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Selectable(id, `class`, ariaLabel, name, CanonDSL().apply(config ?: {})))
        }

        fun selection(id: String?, `class`: String?, ariaLabel: String?, name: String?, countdownInSec: Int?, config: (CanonDSL.() -> Unit)?) {
            add(Selection(id, `class`, ariaLabel, name, countdownInSec, CanonDSL().apply(config ?: {})))
        }

        fun selectionItem(id: String?, `class`: String?, ariaLabel: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(SelectionItem(id, `class`, ariaLabel, name, CanonDSL().apply(config ?: {})))
        }

        fun singleChoice(id: String?, `class`: String?, ariaLabel: String?, name: String?, sieve: Boolean?, required: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(SingleChoice(id, `class`, ariaLabel, name, sieve, required, CanonDSL().apply(config ?: {})))
        }

        fun slider(id: String?, `class`: String?, ariaLabel: String?, min: Double?, max: Double?, step: Double?, value: Double?, name: String?, values: String?) {
            add(Slider(id, `class`, ariaLabel, min, max, step, value, name, values))
        }

        fun slotMachine(id: String?, `class`: String?, ariaLabel: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(SlotMachine(id, `class`, ariaLabel, name, CanonDSL().apply(config ?: {})))
        }

        fun spinner(id: String?, `class`: String?, ariaLabel: String?, min: Double?, max: Double?, step: Double?, value: Double?, name: String?) {
            add(Spinner(id, `class`, ariaLabel, min, max, step, value, name))
        }

        fun submit(id: String?, `class`: String?, ariaLabel: String?, text: String?, name: String?) {
            add(Submit(id, `class`, ariaLabel, text, name))
        }

        fun suggestion(id: String?, `class`: String?, ariaLabel: String?, text: String?, name: String?, value: String?) {
            add(Suggestion(id, `class`, ariaLabel, text, name, value))
        }

        fun table(id: String?, `class`: String?, ariaLabel: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Table(id, `class`, ariaLabel, name, CanonDSL().apply(config ?: {})))
        }

        fun text(id: String?, `class`: String?, ariaLabel: String?, regex: String?, placeholder: String?, required: Boolean?, name: String?, value: String?) {
            add(Text(id, `class`, ariaLabel, regex, placeholder, required, name, value))
        }

        fun textarea(id: String?, `class`: String?, ariaLabel: String?, placeholder: String?, name: String?, value: String?, required: Boolean?, rows: Int?, cols: Int?) {
            add(Textarea(id, `class`, ariaLabel, placeholder, name, value, required, rows, cols))
        }

        fun transition(id: String?, `class`: String?, ariaLabel: String?, name: String?, direction: String?, wrapped: String?, config: (CanonDSL.() -> Unit)?) {
            add(Transition(id, `class`, ariaLabel, name, direction, wrapped, CanonDSL().apply(config ?: {})))
        }

        fun trigger(id: String?, `class`: String?, ariaLabel: String?, name: String?, text: String?) {
            add(Trigger(id, `class`, ariaLabel, name, text))
        }

        fun upload(id: String?, `class`: String?, ariaLabel: String?, accept: String?, name: String?, text: String?, maxSize: Double?, maxCompressSize: Double?, required: Boolean?) {
            add(Upload(id, `class`, ariaLabel, accept, name, text, maxSize, maxCompressSize, required))
        }

        fun video(id: String?, `class`: String?, ariaLabel: String?, src: String?) {
            add(Video(id, `class`, ariaLabel, src))
        }
    }
}