package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import com.fasterxml.jackson.annotation.JsonIgnore

data class Choice(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val name: String?,
                  val text: String?,
                  val selected: String?,
                  @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IClassAware {

    override fun toMap(context: Map<String, Any>, evaluator: IEvaluator): Map<String, Any> {
        val map = HashMap<String, Any>()
        if (name != null && name.isNotBlank())
            map["name"] = evaluator.evaluate(name, context)!!
        if (text != null && text.isNotBlank())
            map["text"] = evaluator.evaluate(text, context)!!
        if (selected != null && selected.isNotBlank())
            map["selected"] = evaluator.evaluate(selected, context)!!.toBoolean()

        return toIdAndClassMap(context, evaluator) + map
    }

    override fun toString() = "Choice(name=$name, text=$text, selected=$selected)"

}
