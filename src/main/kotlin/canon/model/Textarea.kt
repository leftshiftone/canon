package canon.model

class Textarea(id: String,
               `class`: String,
               val placeholder: String,
               val name: String,
               val value: String,
               val required:Boolean,
               val rows: Int,
               val cols: Int) : AbstractRenderable(id, `class`)
