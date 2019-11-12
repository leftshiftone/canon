package canon.model

import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore
import kotlin.collections.Map

data class Textarea(@JsonIgnore val id: String?,
                    @JsonIgnore val `class`: String?,
                    val placeholder: String?,
                    val name: String?,
                    val value: String?,
                    val required: Boolean?,
                    val rows: Int?,
                    val cols: Int?) : IRenderable {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String?, Any?> {
        return mapOf("cols" to cols, "rows" to rows, "placeholder" to evaluator.evaluate(placeholder ?: "", context),
                "name" to name, "value" to evaluator.evaluate(value ?: "", context), "required" to required)
    }

    override fun toString() = "Textarea(placeholder=$placeholder, name=$name, value=$value, required=$required, rows=$rows, cols=$cols)"
}
