package canon.parser.adoc.ast

import canon.api.IRenderable
import canon.model.Block

class TagAST(val list:List<AbstractAST>) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf(Block("", "", "", list.flatMap { it.toRenderable() }))
    }

}
