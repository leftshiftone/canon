package canon.parser.xml.strategy

import canon.extension.toNode
import canon.parser.xml.CanonXmlParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MapStrategyTest {

    @Test
    fun testParse() {
        val xml = "<map id='testId' class='testClass' name='testName' src='testSrc' " +
                "mapType='type' centerLng='12.0' centerLat='10.0' markerIcon='static/icons/icon1.svg' " +
                "selectedMarkerIcon='static/icons/icon2.svg' routePoints='45.3,46.8;45.8,44.7'></map>"
        val parsed = MapStrategy().parse(xml.toNode(), CanonXmlParser()::toRenderables)

        assertThat(parsed).isNotNull()
        assertThat(parsed.id).isEqualTo("testId")
        assertThat(parsed.`class`).isEqualTo("testClass")
        assertThat(parsed.name).isEqualTo("testName")
        assertThat(parsed.src).isEqualTo("testSrc")
        assertThat(parsed.mapType).isEqualTo("type")
        assertThat(parsed.centerLng).isEqualTo("12.0")
        assertThat(parsed.centerLat).isEqualTo("10.0")
        assertThat(parsed.markerIcon).isEqualTo("static/icons/icon1.svg")
        assertThat(parsed.selectedMarkerIcon).isEqualTo("static/icons/icon2.svg")
        assertThat(parsed.routePoints).isEqualTo("45.3,46.8;45.8,44.7")
        assertThat(parsed.centerBrowserLocation).isFalse()
        assertThat(parsed.required).isFalse()

    }

}
