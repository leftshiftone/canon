package canon.model

import canon.api.IRenderable
import canon.api.IStackeable

class Slider(id: String,
             `class`: String,
             val min: Double,
             val max: Double,
             val step: Double,
             val value: Double,
             val name:String,
             val values:String) : AbstractRenderable(id, `class`), IRenderable
