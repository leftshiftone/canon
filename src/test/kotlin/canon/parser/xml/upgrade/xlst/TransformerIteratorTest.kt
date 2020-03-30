package canon.parser.xml.upgrade.xlst

import canon.parser.xml.upgrade.SemanticVersion
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

internal class TransformerIteratorTest{

    @Test
    fun `given an iterator with non visited transformers, hasNext() returns true`(){
        val stack = Stack<CanonXlstTransformer>()
        val transformer_2_0_0 = CanonXlstTransformer("/xml/xlst/", SemanticVersion("2.0.0"))
        stack.push(transformer_2_0_0)
        val classUnderTest = TransformerIterator(stack)
        assertThat(classUnderTest.hasNext()).isTrue()
    }

    @Test
    fun `given an iterator with non visited transformers, next returns a transformer`(){
        val stack = Stack<CanonXlstTransformer>()
        val transformer_2_0_0 = CanonXlstTransformer("/xml/xlst/", SemanticVersion("2.0.0"))
        stack.push(transformer_2_0_0)
        val classUnderTest = TransformerIterator(stack)
        assertThat(classUnderTest.next()).isEqualTo(transformer_2_0_0)
    }


    @Test
    fun `given an iterator which as been visited, hasNext() throws an EmptyStackException`(){
        val stack = Stack<CanonXlstTransformer>()
        val transformer_2_0_0 = CanonXlstTransformer("/xml/xlst/", SemanticVersion("2.0.0"))
        stack.push(transformer_2_0_0)
        val classUnderTest = TransformerIterator(stack)
        classUnderTest.next()
        Assertions.assertThrows(EmptyStackException::class.java) {
            classUnderTest.next()
        }
    }

    @Test
    fun `given an iterator which as been visited, hasNext() returns false`(){
        val stack = Stack<CanonXlstTransformer>()
        val transformer_2_0_0 = CanonXlstTransformer("/xml/xlst/", SemanticVersion("2.0.0"))
        stack.push(transformer_2_0_0)
        val classUnderTest = TransformerIterator(stack)
        classUnderTest.next()
        assertThat(classUnderTest.hasNext()).isFalse()

    }

}