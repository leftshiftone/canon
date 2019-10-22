package canon.model

class Spinner(id: String?,
              `class`: String?,
              val min: Double?,
              val max: Double?,
              val step: Double?,
              val value: Double?,
              val name: String?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Spinner) return false
        if (!super.equals(other)) return false

        if (min != other.min) return false
        if (max != other.max) return false
        if (step != other.step) return false
        if (value != other.value) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (min?.hashCode() ?: 0)
        result = 31 * result + (max?.hashCode() ?: 0)
        result = 31 * result + (step?.hashCode() ?: 0)
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Spinner(min=$min, max=$max, step=$step, value=$value, name=$name) ${super.toString()}"
    }


}
