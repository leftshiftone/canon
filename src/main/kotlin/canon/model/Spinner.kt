package canon.model

class Spinner(id: String?,
              `class`: String?,
              val min: Double?,
              val max: Double?,
              val step: Double?,
              val value: Double?,
              val name: String?) : AbstractRenderable(id, `class`)
