package canon.parser.adoc.ast

import canon.api.IRenderable
import canon.model.Block

class CodeAST(val lines:List<AbstractAST>) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf(Block("", "", "", "", lines.flatMap { it.toRenderable() }))
    }

}
