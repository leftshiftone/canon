package canon.model

class Video(id: String?,
            `class`: String?,
            val src: String?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Video) return false
        if (!super.equals(other)) return false

        if (src != other.src) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (src?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Video(src=$src) ${super.toString()}"
    }


}