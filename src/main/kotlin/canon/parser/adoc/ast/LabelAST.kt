package canon.parser.adoc.ast

import canon.api.IRenderable
import canon.model.Label

class LabelAST(val text:String) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf(Label("", "", text))
    }

}
