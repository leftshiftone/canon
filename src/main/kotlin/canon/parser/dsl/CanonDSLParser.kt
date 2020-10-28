package canon.parser.dsl

import canon.api.IRenderable
import canon.model.Basket

class CanonDSLParser {

    companion object {
        fun parse(config: CanonDSL.() -> Unit): List<IRenderable> {
            val dsl = CanonDSL().apply(config)
            return dsl.renderables
        }
    }

    class CanonDSL {
        val renderables = ArrayList<IRenderable>()

        fun basket(id: String?, clazz: String?, name: String?, required: Boolean?, config: (CanonDSL.() -> Unit)?): IRenderable {
            return Basket(id, clazz, name, required, CanonDSL().apply(config ?: {}).renderables)
        }
    }

}