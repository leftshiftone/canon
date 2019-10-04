package canon.model

import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

class SieveAwareChoiceContainer(id: String,
                                 `class`: String,
                                 open val name: String,
                                 open val sieve: Boolean,
                                 open val required: Boolean,
                                 @JsonIgnore override val renderables: List<IRenderable>) : AbstractStackeable(id, `class`, renderables) {

}