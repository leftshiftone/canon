package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class Overlays (id: String?, `class`: String?,
                 val trigger: String?,
                 @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables)
