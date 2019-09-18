package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ForeachTest {

    @Test
    fun testForeachMapping() {
        val mapped = Foreach("testId", "testClass", ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(0, mapped.size)
    }
}