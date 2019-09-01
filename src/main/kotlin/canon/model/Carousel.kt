package canon.model

class Carousel(id: String,
               `class`: String,
               val text: String,
               val name: String,
               val selected: Boolean) : AbstractRenderable(id, `class`)
