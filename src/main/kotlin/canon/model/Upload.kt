package canon.model

class Upload(id: String?,
             `class`: String?,
             val accept: String?,
             val name: String?,
             val text: String?,
             val maxSize: Double?,
             val maxCompressionSize: Double?) : AbstractRenderable(id, `class`) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Upload) return false
        if (!super.equals(other)) return false

        if (accept != other.accept) return false
        if (name != other.name) return false
        if (text != other.text) return false
        if (maxSize != other.maxSize) return false
        if (maxCompressionSize != other.maxCompressionSize) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (accept?.hashCode() ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (maxSize?.hashCode() ?: 0)
        result = 31 * result + (maxCompressionSize?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Upload(accept=$accept, name=$name, text=$text, maxSize=$maxSize, maxCompressionSize=$maxCompressionSize) ${super.toString()}"
    }


}
