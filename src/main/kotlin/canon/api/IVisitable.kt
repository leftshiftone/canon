package canon.api

interface IVisitable {
    fun <R>accept(visitor: IVisitor<R>, evaluator: IEvaluator):R
}
