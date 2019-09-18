package canon.api

interface IVisitable {

    fun accept(visitor: IVisitor)
}