package canon.model

import canon.api.*
import canon.support.Base64
import canon.support.MapBuilder
import com.fasterxml.jackson.annotation.JsonIgnore

data class Button(@JsonIgnore override val id: String?,
                  @JsonIgnore override val `class`: String?,
                  val name: String?,
                  var value: String?,
                  @JsonIgnore val renderables: List<IRenderable>?) : AbstractStackable(renderables), IRenderable, IClassAware, IClickable, IValueAware {

    override fun label(): String? {
        TODO("Not yet implemented")
    }

    override fun name(): String? {
        return name
    }

    override fun value(): String? {
        return value
    }

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val builder = MapBuilder()
        builder.put("value", value) {Base64.encodeUTF8String(evaluator.evaluate(Base64.decode(it as String?)!!, context))!!}
        builder.put("name", name, "result")

        return builder.toMap().plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Button(name=$name, value=$value) { ${renderables?.map { it.toString() }}"
}
