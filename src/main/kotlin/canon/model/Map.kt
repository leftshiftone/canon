package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.IVisitor

class Map(id: String?,
          `class`: String?,
          val name: String?,
          val src: String?,
          val mapType: String?,
          val centerLng: String?,
          val centerLat: String?,
          val exact: Boolean?,
          val centerBrowserLocation: Boolean?,
          val required: Boolean?,
          val maxSelections: Int?) : AbstractRenderable(id, `class`), IRenderable {

    override fun accept(visitor: IVisitor, evaluator: IEvaluator) {
        // do nothing
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Map) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (src != other.src) return false
        if (mapType != other.mapType) return false
        if (centerLng != other.centerLng) return false
        if (centerLat != other.centerLat) return false
        if (exact != other.exact) return false
        if (centerBrowserLocation != other.centerBrowserLocation) return false
        if (required != other.required) return false
        if (maxSelections != other.maxSelections) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (src?.hashCode() ?: 0)
        result = 31 * result + (mapType?.hashCode() ?: 0)
        result = 31 * result + (centerLng?.hashCode() ?: 0)
        result = 31 * result + (centerLat?.hashCode() ?: 0)
        result = 31 * result + (exact?.hashCode() ?: 0)
        result = 31 * result + (centerBrowserLocation?.hashCode() ?: 0)
        result = 31 * result + (required?.hashCode() ?: 0)
        result = 31 * result + (maxSelections?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Map(name=$name, src=$src, mapType=$mapType, centerLng=$centerLng, centerLat=$centerLat, exact=$exact, centerBrowserLocation=$centerBrowserLocation, required=$required, maxSelections=$maxSelections) ${super.toString()}"
    }

}
