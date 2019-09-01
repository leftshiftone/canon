package canon.model

class Phone(id: String,
            `class`: String,
            val placeholder: String,
            val required: Boolean,
            val name:String,
            val value: String) : AbstractRenderable(id, `class`)
