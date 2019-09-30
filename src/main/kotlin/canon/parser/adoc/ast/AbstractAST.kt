package canon.parser.adoc.ast

import canon.api.IRenderable

abstract class AbstractAST {

    abstract fun toRenderable():List<IRenderable>

}
