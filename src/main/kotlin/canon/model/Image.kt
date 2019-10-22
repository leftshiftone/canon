package canon.model

class Image(id: String?, `class`: String?,
            val src: String?,
            val width: String?,
            val height: String?,
            val alt: String?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Image) return false
        if (!super.equals(other)) return false

        if (src != other.src) return false
        if (width != other.width) return false
        if (height != other.height) return false
        if (alt != other.alt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (src?.hashCode() ?: 0)
        result = 31 * result + (width?.hashCode() ?: 0)
        result = 31 * result + (height?.hashCode() ?: 0)
        result = 31 * result + (alt?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Image(src=$src, width=$width, height=$height, alt=$alt) ${super.toString()}"
    }

}
