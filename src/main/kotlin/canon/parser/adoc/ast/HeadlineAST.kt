package canon.parser.adoc.ast

import canon.api.IRenderable
import canon.model.Headline

class HeadlineAST(val text: String) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf(Headline("", "", "", text, ""))
    }

}
