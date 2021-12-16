package canon.parser.adoc.ast

import canon.api.IRenderable
import canon.model.Image

class ImageAST(val src:String) : AbstractAST() {

    override fun toRenderable(): List<IRenderable> {
        return listOf(Image("", "", "", src, "auto", "auto", "image"))
    }

}
