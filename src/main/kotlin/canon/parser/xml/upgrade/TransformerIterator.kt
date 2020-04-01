package canon.parser.xml.upgrade

import java.util.*


class TransformerIterator<E>(val transformers : Stack<E>) : Iterator<E> {

    override fun hasNext(): Boolean {
        return !transformers.isEmpty()
    }

    override fun next(): E {
        return transformers.pop()
    }

}