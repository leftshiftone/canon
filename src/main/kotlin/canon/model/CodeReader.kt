package canon.model

class CodeReader(id: String?,
                 `class`: String?,
                 val name: String?,
                 val format: String?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CodeReader) return false
        if (!super.equals(other)) return false

        if (name != other.name) return false
        if (format != other.format) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (format?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "CodeReader(name=$name, format=$format) ${super.toString()}"
    }

}
