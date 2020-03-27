package canon.parser.xml.xlst

import java.util.*


class TransformerIterator(val transformers : Stack<CanonXlstTransformer>) : Iterator<CanonXlstTransformer> {

    override fun hasNext(): Boolean {
        return !transformers.isEmpty()
    }

    override fun next(): CanonXlstTransformer {
        return transformers.pop()
    }

}