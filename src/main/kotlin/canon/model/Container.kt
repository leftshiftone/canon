package canon.model

import canon.api.IClassAware
import canon.api.IEnrichable
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Container(@JsonIgnore override val id: String?,
                     @JsonIgnore override val `class`: String?,
                     val name: String?,
                     @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware, IEnrichable {

    private val enrichedData = mutableMapOf<String, Any>()

    override fun enrich(key: String, value: Any) {
        enrichedData[key] = value
    }

    override fun getEnriched(): Map<String, Any> {
        return enrichedData
    }

    override fun toString() = "Container(name=$name)"
}
