package canon.parser.xml.upgrade.xslt

import canon.parser.xml.upgrade.TransformerIterator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

internal class TransformerIteratorTest{

    val TRANSFORMER_2_0_0 = XSLTTransformer(XSLTTransformerConfiguration(("/xml/xslt/transformers/transform_2.0.0.xslt")))


    @Test
    fun `given an iterator with non visited transformers, hasNext() returns true`(){
        val stack = Stack<XSLTTransformer>()
        stack.push(TRANSFORMER_2_0_0)
        val classUnderTest = TransformerIterator(stack)
        assertThat(classUnderTest.hasNext()).isTrue()
    }

    @Test
    fun `given an iterator with non visited transformers, next returns a transformer`(){
        val stack = Stack<XSLTTransformer>()
        stack.push(TRANSFORMER_2_0_0)
        val classUnderTest = TransformerIterator(stack)
        assertThat(classUnderTest.next()).isEqualTo(TRANSFORMER_2_0_0)
    }


    @Test
    fun `given an iterator which as been visited, hasNext() throws an EmptyStackException`(){
        val stack = Stack<XSLTTransformer>()
        stack.push(TRANSFORMER_2_0_0)
        val classUnderTest = TransformerIterator(stack)
        classUnderTest.next()
        Assertions.assertThrows(EmptyStackException::class.java) {
            classUnderTest.next()
        }
    }

    @Test
    fun `given an iterator which as been visited, hasNext() returns false`(){
        val stack = Stack<XSLTTransformer>()
        stack.push(TRANSFORMER_2_0_0)
        val classUnderTest = TransformerIterator(stack)
        classUnderTest.next()
        assertThat(classUnderTest.hasNext()).isFalse()

    }

}