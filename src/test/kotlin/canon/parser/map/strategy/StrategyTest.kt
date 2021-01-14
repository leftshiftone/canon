package canon.parser.map.strategy

import canon.api.IRenderable
import canon.support.TestEvaluator

internal fun <T: IRenderable> testStrategy(renderable: T, strategy: AbstractParseStrategy<T>) : Boolean{
    val map = renderable.toMap(HashMap(), TestEvaluator())
    val mappedRenderable = strategy.parse(map) { emptyList()}

    return renderable == mappedRenderable
}