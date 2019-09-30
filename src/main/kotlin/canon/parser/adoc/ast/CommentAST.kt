package canon.parser.adoc.ast

import canon.api.IRenderable

class CommentAST(val type: String, val text:String) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf()
    }
}
