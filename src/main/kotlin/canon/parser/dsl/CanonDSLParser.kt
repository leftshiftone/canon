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

        fun basket(id: String?, `class`: String?, name: String?, required: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(Basket(id, `class`, name, required, CanonDSL().apply(config ?: {})))
        }

        fun block(id: String?, `class`: String?, name: String?, config: (CanonDSL.() -> Unit)?){
            add(Block(id, `class`, name, CanonDSL().apply(config ?: {})))
        }

        fun bold(id: String?, `class`: String?, text: String?) {
            add(Bold(id, `class`, text))
        }

        fun `break`(id: String?, `class`: String?) {
            add(Break(id, `class`))
        }

        fun button(id: String?, `class`: String?, text: String?, name: String?, value: String?, config: (CanonDSL.() -> Unit)?){
            add(Button(id, `class`, text, name, value, CanonDSL().apply(config ?: {})))
        }

        fun camera(id: String?, `class`: String?, name: String?, required: Boolean?, maxCompressSize: Double?, config: (CanonDSL.() -> Unit)?) {
            add(Camera(id, `class`, name, required, maxCompressSize, CanonDSL().apply(config ?: {})))
        }

        fun carousel(id: String?, `class`: String?, text: String?, name: String?, selected: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(Carousel(id, `class`, text, name, selected, CanonDSL().apply(config ?: {})))
        }

        fun choice(id: String?, `class`: String?, name: String?, text: String?, selected: String?, config: (CanonDSL.() -> Unit)?) {
            add(Choice(id, `class`, name, text, selected, CanonDSL().apply(config ?: {})))
        }

        fun codeReader(id: String?, `class`: String?, name: String?, format: String?) {
            add(CodeReader(id, `class`, name, format))
        }

        fun col(id: String?, `class`: String?, config: (CanonDSL.() -> Unit)?) {
            add(Col(id, `class`, CanonDSL().apply(config ?: {})))
        }

        fun container(id: String?, `class`: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Container(id, `class`, name, CanonDSL().apply(config ?: {})))
        }

        fun email(id: String?, `class`: String?, placeholder: String?, required: Boolean?, name: String?, value: String?) {
            add(Email(id, `class`, placeholder, required, name, value))
        }

        fun foreach(forEachStmt: String?, renderable: IRenderable?) {
            add(Foreach(forEachStmt, renderable))
        }

        fun form(id: String?, `class`: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Form(id, `class`, name, CanonDSL().apply(config ?: {})))
        }

        fun headline(id: String?, `class`: String?, text: String?) {
            add(Headline(id, `class`, text))
        }

        fun `if`(expression: String?, renderable: IRenderable?) {
            add(If(expression, renderable))
        }

        fun image(id: String?, `class`: String?, src: String?, width: String?, height: String?, alt: String?) {
            add(Image(id, `class`, src, width, height, alt))
        }

        fun italic(id: String?, `class`: String?, text: String?) {
            add(Italic(id, `class`, text))
        }

        fun item(id: String?, `class`: String?, config: (CanonDSL.() -> Unit)?) {
            add(Item(id, `class`, CanonDSL().apply(config ?: {})))
        }

        fun items(id: String?, `class`: String?, ordered: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(Items(id, `class`, ordered, CanonDSL().apply(config ?: {})))
        }

        fun label(id: String?, `class`: String?, text: String?) {
            add(Label(id, `class`, text))
        }

        fun link(id: String?, `class`: String?, value: String?, text: String?) {
            add(Link(id, `class`, value, text))
        }

        fun map(id: String?, `class`: String?, name: String?, src: String?, mapType: String?, centerLng: String?,
                centerLat: String?, markerIcon: String?, selectedMarkerIcon: String?, routeStartIcon: String?,
                routeEndIcon: String?, routePoints: String?, centerBrowserLocation: Boolean?, required: Boolean?,
                zoom: String?, maxSelections: Int?) {
            add(Map(id, `class`, name, src, mapType, centerLng, centerLat, markerIcon, selectedMarkerIcon,
                    routeStartIcon, routeEndIcon, routePoints, centerBrowserLocation, required, zoom, maxSelections))
        }

        fun multipleChoice(id: String?, `class`: String?, name: String?, sieve: Boolean?, required: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(MultipleChoice(id, `class`, name, sieve, required, CanonDSL().apply(config ?: {})))
        }

        fun overlay(id: String?, `class`: String?, trigger: String?, config: (CanonDSL.() -> Unit)?) {
            add(Overlay(id, `class`, trigger, CanonDSL().apply(config ?: {})))
        }

        fun overlays(id: String?, `class`: String?, trigger: String?, config: (CanonDSL.() -> Unit)?) {
            add(Overlays(id, `class`, trigger, CanonDSL().apply(config ?: {})))
        }

        fun phone(id: String?, `class`: String?, placeholder: String?, required: Boolean?, name: String?, value: String?) {
            add(Phone(id, `class`, placeholder, required, name, value))
        }

        fun reel(id: String?, `class`: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Reel(id, `class`, name, CanonDSL().apply(config ?: {})))
        }

        fun reelValue(id: String?, `class`: String?, value: String?, valueType: String?) {
            add(ReelValue(id, `class`, value, valueType))
        }

        fun row(id: String?, `class`: String?, config: (CanonDSL.() -> Unit)?) {
            add(Row(id, `class`, CanonDSL().apply(config ?: {})))
        }

        fun selectable(id: String?, `class`: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Selectable(id, `class`, name, CanonDSL().apply(config ?: {})))
        }

        fun selection(id: String?, `class`: String?, name: String?, countdownInSec: Int?, config: (CanonDSL.() -> Unit)?) {
            add(Selection(id, `class`, name, countdownInSec, CanonDSL().apply(config ?: {})))
        }

        fun selectionItem(id: String?, `class`: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(SelectionItem(id, `class`, name, CanonDSL().apply(config ?: {})))
        }

        fun singleChoice(id: String?, `class`: String?, name: String?, sieve: Boolean?, required: Boolean?, config: (CanonDSL.() -> Unit)?) {
            add(SingleChoice(id, `class`, name, sieve, required, CanonDSL().apply(config ?: {})))
        }

        fun slider(id: String?, `class`: String?, min: Double?, max: Double?, step: Double?, value: Double?, name: String?, values: String?) {
            add(Slider(id, `class`, min, max, step, value, name, values))
        }

        fun slotMachine(id: String?, `class`: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(SlotMachine(id, `class`, name, CanonDSL().apply(config ?: {})))
        }

        fun spinner(id: String?, `class`: String?, min: Double?, max: Double?, step: Double?, value: Double?, name: String?) {
            add(Spinner(id, `class`, min, max, step, value, name))
        }

        fun submit(id: String?, `class`: String?, text: String?, name: String?) {
            add(Submit(id, `class`, text, name))
        }

        fun suggestion(id: String?, `class`: String?, text: String?, name: String?, value: String?) {
            add(Suggestion(id, `class`, text, name, value))
        }

        fun table(id: String?, `class`: String?, name: String?, config: (CanonDSL.() -> Unit)?) {
            add(Table(id, `class`, name, CanonDSL().apply(config ?: {})))
        }

        fun text(id: String?, `class`: String?, regex: String?, placeholder: String?, required: Boolean?, name: String?, value: String?) {
            add(Text(id, `class`, regex, placeholder, required, name, value))
        }

        fun textarea(id: String?, `class`: String?, placeholder: String?, name: String?, value: String?, required: Boolean?, rows: Int?, cols: Int?) {
            add(Textarea(id, `class`, placeholder, name, value, required, rows, cols))
        }

        fun transition(id: String?, `class`: String?, name: String?, direction: String?, wrapped: String?, config: (CanonDSL.() -> Unit)?) {
            add(Transition(id, `class`, name, direction, wrapped, CanonDSL().apply(config ?: {})))
        }

        fun trigger(id: String?, `class`: String?, name: String?, text: String?) {
            add(Trigger(id, `class`, name, text))
        }

        fun upload(id: String?, `class`: String?, accept: String?, name: String?, text: String?, maxSize: Double?, maxCompressSize: Double?, required: Boolean?) {
            add(Upload(id, `class`, accept, name, text, maxSize, maxCompressSize, required))
        }

        fun video(id: String?, `class`: String?, src: String?) {
            add(Video(id, `class`, src))
        }
    }
}