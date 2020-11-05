package canon.model

import canon.support.TestEvaluator
import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat


class UploadTest {

    @Test
    fun testUploadMapping() {
        val mapped = Upload("testId", "testClass", "jpg, png","testName",
                "testText", 5.0, 1.0, true).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(8)
        assertThat(mapped.get("accept")).isEqualTo("jpg, png")
        assertThat(mapped.get("name")).isEqualTo("testName")
        assertThat(mapped.get("text")).isEqualTo("testText")
        assertThat(mapped.get("maxSize")).isEqualTo(5.0)
        assertThat(mapped.get("maxCompressSize")).isEqualTo(1.0)
        assertThat(mapped.get("required")).isEqualTo(true)
    }
}
