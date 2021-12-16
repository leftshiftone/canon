package canon.parser.adoc.ast

import canon.api.IRenderable
import canon.model.Item
import canon.model.Label

class ItemAST(val text:String) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf(Item("", "", "", listOf(Label("", "", "", text))))
    }

}
