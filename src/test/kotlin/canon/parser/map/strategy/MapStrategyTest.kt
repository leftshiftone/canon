package canon.parser.map.strategy

import canon.model.Map
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class MapStrategyTest {

    @Test
    fun testParse() = assertTrue(
            testStrategy(
                Map("id", "class", "name", "src", "mapType", "10.0",
                        "10.0", "markerIcon", "selectedMarkerIcon",
                        "routeStartIcon", "routeEndIcon", "routePoints",
                        true, true, "8", 10, "35"),
                MapStrategy()))
}