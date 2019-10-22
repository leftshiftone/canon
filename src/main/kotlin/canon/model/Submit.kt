package canon.model

class Submit(id: String?,
             `class`: String?,
             val text: String?,
             val name: String?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Submit) return false
        if (!super.equals(other)) return false

        if (text != other.text) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Submit(text=$text, name=$name) ${super.toString()}"
    }


}
