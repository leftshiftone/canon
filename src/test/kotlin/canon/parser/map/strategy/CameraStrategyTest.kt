package canon.parser.map.strategy

import canon.model.Camera
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class CameraStrategyTest {

    @Test
    fun testParse() = assertTrue(testStrategy(Camera("id", "class", "ariaLabel", "name", true, 10.0, emptyList()), CameraStrategy()))
}