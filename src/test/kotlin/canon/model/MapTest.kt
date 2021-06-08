package canon.model

import canon.support.TestEvaluator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class MapTest {

    @Test
    fun testMapMapping() {
        val mapped = Map(id = "testId", `class` = "testClass", name = "testName",
                src = "testSrc", mapType = "type", centerLng = "123.0", centerLat = "-170.0",
                markerIcon = "static/icons/icon01.svg", selectedMarkerIcon = "static/icons/icon02.svg",
                routeStartIcon = "static/icons/start.png", routeEndIcon = "static/icons/end.png",
                routePoints = "45.3,46.8;45.8,44.7", centerBrowserLocation = false, required = true,
                zoom = "12", maxSelections = 1, zoomByRadius = 35).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(17)
        assertThat(mapped["name"]).isEqualTo("testName")
        assertThat(mapped["src"]).isEqualTo("testSrc")
        assertThat(mapped["mapType"]).isEqualTo("type")
        assertThat(mapped["centerLng"]).isEqualTo(123.0)
        assertThat(mapped["centerLat"]).isEqualTo(-170.0)
        assertThat(mapped["markerIcon"]).isEqualTo("static/icons/icon01.svg")
        assertThat(mapped["selectedMarkerIcon"]).isEqualTo("static/icons/icon02.svg")
        assertThat(mapped["routeStartIcon"]).isEqualTo("static/icons/start.png")
        assertThat(mapped["routeEndIcon"]).isEqualTo("static/icons/end.png")
        assertThat(mapped["routePoints"]).isEqualTo("45.3,46.8;45.8,44.7")
        assertThat(mapped["centerBrowserLocation"]).isEqualTo(false)
        assertThat(mapped["required"]).isEqualTo(true)
        assertThat(mapped["zoom"]).isEqualTo(12)
        assertThat(mapped["maxSelections"]).isEqualTo(1)
        assertThat(mapped["zoomByRadius"]).isEqualTo(35)
    }

    @Test
    fun `testMapMapping check default setting`() {
        val mapped = Map(id = "testId", `class` = "testClass", name = "testName",
                src = "testSrc", mapType = "type", centerLng = "not numeric", centerLat = "not numeric",
                markerIcon = "static/icons/icon01.svg", selectedMarkerIcon = "static/icons/icon02.svg",
                routeStartIcon = "static/icons/start.png", routeEndIcon = "static/icons/end.png",
                routePoints = "45.3,46.8;45.8,44.7", centerBrowserLocation = false, required = true,
                zoom = "test", maxSelections = 1, zoomByRadius = 35).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(17)
        assertThat(mapped["centerLng"]).isEqualTo(0.0)
        assertThat(mapped["centerLat"]).isEqualTo(0.0)
        assertThat(mapped["zoom"]).isEqualTo(8)
    }
}
