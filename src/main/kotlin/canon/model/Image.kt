package canon.model

import canon.api.IClassAware
import canon.api.IEvaluator
import canon.api.IRenderable
import canon.api.KMap
import com.fasterxml.jackson.annotation.JsonIgnore

data class Image(@JsonIgnore override val id: String?, @JsonIgnore override val `class`: String?,
                 val src: String?,
                 val width: String?,
                 val height: String?,
                 val alt: String?) : IRenderable, IClassAware {

    override fun toMap(context: KMap<String, Any>, evaluator: IEvaluator): KMap<String, Any> {
        val map = HashMap<String, Any>()
        if (src != null && src.isNotBlank())
            map.put("src", evaluator.evaluate(src, context)!!)
        if (width != null && width.isNotBlank())
            map.put("width", width)
        if (height != null && height.isNotBlank())
            map.put("height", height)
        if (alt != null && alt.isNotBlank())
            map.put("alt", alt)

        return map.plus(toIdAndClassMap(context, evaluator))
    }

    override fun toString() = "Image(src=$src, width=$width, height=$height, alt=$alt)"
}
