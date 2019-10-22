package canon.model

class Text(id: String?, `class`: String?, val text: String?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Text) return false
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
        return "Text(text=$text) ${super.toString()}"
    }


}
