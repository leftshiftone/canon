package canon.api

interface IVisitable {

    fun accept(visitor: IVisitor)

    fun evaluate(visitor: IVisitor) : Boolean { throw NotImplementedError("Override of evaluate function necessary") }
}