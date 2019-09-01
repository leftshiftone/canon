package canon.model

class Suggestion(id: String,
                 `class`: String,
                 val text: String,
                 val name: String,
                 val value: String) : AbstractRenderable(id, `class`)
