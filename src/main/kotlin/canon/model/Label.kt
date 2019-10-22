package canon.model

class Label(id: String?, `class`: String?, val text: String?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Label) return false
        if (!super.equals(other)) return false

        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (text?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Label(text=$text) ${super.toString()}"
    }

}
