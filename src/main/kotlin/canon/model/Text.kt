package canon.model

class Text(id: String,
           `class`: String,
           val regex: String,
           val placeholder: String,
           val required: Boolean,
           val name: String,
           val value: String) : AbstractRenderable(id, `class`)
