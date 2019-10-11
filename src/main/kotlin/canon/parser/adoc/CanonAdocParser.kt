package canon.parser.adoc


import canon.exception.CanonException
import canon.parser.adoc.ast.AbstractAST
import canon.parser.adoc.exception.CanonExceptionListener
import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.InputStream

class CanonAdocParser {

    companion object {

        @JvmStatic
        fun parse(string: String):List<AbstractAST> {
            return parse(CharStreams.fromString(string))
        }

        @JvmStatic
        fun parse(stream: InputStream):List<AbstractAST> {
            return parse(CharStreams.fromStream(stream))
        }

        private fun parse(stream: CharStream): List<AbstractAST> {
            /*val lexer = CanonLexer(stream)
            val commonTokenStream = CommonTokenStream(lexer)
            val parser = CanonParser(commonTokenStream)

            val errorListener = CanonExceptionListener()
            parser.addErrorListener(errorListener)

            val visitor = CanonVisitor()
            val astList = visitor.visit(parser.compilation()) ?: throw CanonException("invalid adoc script")

            if (errorListener.get() != null) {
                throw CanonException(errorListener.get())
            }*/
            return ArrayList<AbstractAST>()
        }
    }

}
