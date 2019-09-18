package canon.model

import canon.api.IRenderable
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class ColTest {

    @Test
    fun testColMapping() {
        val mapped = Col("testId", "testClass", ArrayList<IRenderable>()).toMap(HashMap<String, Any>())

        assertEquals(0, mapped.size)
    }
}