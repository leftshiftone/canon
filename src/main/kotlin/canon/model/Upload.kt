package canon.model

class Upload(id: String,
             `class`: String,
             val accept: String,
             val name: String,
             val text: String,
             val maxSize: Double,
             val maxCompressionSize: Double) : AbstractRenderable(id, `class`)
