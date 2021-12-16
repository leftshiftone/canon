package canon.parser.adoc.ast

import canon.api.IRenderable
import canon.model.Bold

class BoldAST(val text:String) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf(Bold("", "", "", text))
    }

}
