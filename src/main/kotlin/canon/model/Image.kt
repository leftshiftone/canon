package canon.model

class Image(id: String?, `class`: String?,
            val src: String?,
            val width: String?,
            val height: String?,
            val alt: String?) : AbstractRenderable(id, `class`)
