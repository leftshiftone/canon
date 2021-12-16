package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class UploadTest {

    @Test
    fun testUploadMapping() {
        val mapped = Upload("testId", "testClass", "testAriaLabel", "jpg, png","testName",
                "testText", 5.0, 1.0, true).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(9)
        assertThat(mapped["accept"]).isEqualTo("jpg, png")
        assertThat(mapped["name"]).isEqualTo("testName")
        assertThat(mapped["text"]).isEqualTo("testText")
        assertThat(mapped["maxSize"]).isEqualTo(5.0)
        assertThat(mapped["maxCompressSize"]).isEqualTo(1.0)
        assertThat(mapped["required"]).isEqualTo(true)
    }
}
