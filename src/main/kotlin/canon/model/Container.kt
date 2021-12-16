package canon.model

import canon.api.IClassAware
import canon.api.IEnrichable
import canon.api.IRenderable
import canon.api.KMap
import com.fasterxml.jackson.annotation.JsonIgnore

data class Container(
    @JsonIgnore override val id: String?,
    @JsonIgnore override val `class`: String?,
    @JsonIgnore override val ariaLabel: String?,
    val name: String?,
    @JsonIgnore val renderables: List<IRenderable>?
) : AbstractStackable(renderables), IClassAware, IEnrichable {

    private val enrichedData = mutableMapOf<String, Any>()

    override fun enrich(key: String, value: Any) {
        enrichedData[key] = value
    }

    override fun getEnriched(): KMap<String, Any> {
        return enrichedData
    }

    override fun toString() = "Container(name=$name)"
}
