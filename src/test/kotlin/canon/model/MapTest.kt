package canon.model

import canon.support.TestEvaluator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class MapTest {

    @Test
    fun testMapMapping() {
        val mapped = Map(id = "testId", `class` = "testClass", name = "testName",
                src = "testSrc", mapType = "type", centerLng = 123.0, centerLat = -170.0,
                markerIcon = "static/icons/icon01.svg", selectedMarkerIcon = "static/icons/icon02.svg",
                routePoints = "45.3,46.8;45.8,44.7", centerBrowserLocation = false, required = true,
                zoom = 12, maxSelections = 1).toMap(HashMap(), TestEvaluator())

        assertThat(mapped.size).isEqualTo(14)
        assertThat(mapped["name"]).isEqualTo("testName")
        assertThat(mapped["src"]).isEqualTo("testSrc")
        assertThat(mapped["mapType"]).isEqualTo("type")
        assertThat(mapped["centerLng"]).isEqualTo(123.0)
        assertThat(mapped["centerLat"]).isEqualTo(-170.0)
        assertThat(mapped["markerIcon"]).isEqualTo("static/icons/icon01.svg")
        assertThat(mapped["selectedMarkerIcon"]).isEqualTo("static/icons/icon02.svg")
        assertThat(mapped["routePoints"]).isEqualTo("45.3,46.8;45.8,44.7")
        assertThat(mapped["centerBrowserLocation"]).isEqualTo(false)
        assertThat(mapped["required"]).isEqualTo(true)
        assertThat(mapped["zoom"]).isEqualTo(12)
        assertThat(mapped["maxSelections"]).isEqualTo(1)
    }
}
