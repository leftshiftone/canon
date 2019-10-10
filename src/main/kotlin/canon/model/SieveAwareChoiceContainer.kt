package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

class SieveAwareChoiceContainer(id: String?,
                                 `class`: String?,
                                 open val name: String?,
                                 open val sieve: Boolean?,
                                 open val required: Boolean?,
                                 @JsonIgnore override val renderables: List<IRenderable>?) : AbstractStackeable(id, `class`, renderables) {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("name" to evaluator.evaluate(name, context), "sieve" to sieve, "required" to required)
    }

}