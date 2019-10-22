package canon.model

class ReelValue(id: String,
                `class`: String?,
                val value: String?,
                val valueType: String?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ReelValue) return false
        if (!super.equals(other)) return false

        if (value != other.value) return false
        if (valueType != other.valueType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (valueType?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "ReelValue(value=$value, valueType=$valueType) ${super.toString()}"
    }

}
