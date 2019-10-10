package canon.parser.adoc.ast

import canon.api.IRenderable
import canon.model.Items

class ItemsAST(val items:List<AbstractAST>) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf(Items("", "", false, items.flatMap { it.toRenderable() }))
    }

}
