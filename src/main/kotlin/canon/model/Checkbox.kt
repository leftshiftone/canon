package canon.model

import canon.api.IRenderable

@Deprecated("Use Choice instead")
class Checkbox(id: String,
               `class`: String,
               val value: String,
               val text: String,
               val name: String,
               val checked: String) : AbstractRenderable(id, `class`) {


}