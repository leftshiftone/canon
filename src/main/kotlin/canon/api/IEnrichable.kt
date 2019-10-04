package canon.api

@Deprecated("Refactoring ongoing")
interface IEnrichable : IRenderable {

    fun enrich(key: String, value:Any)

    fun enrich(enriched: Map<String, Any>) { enriched.forEach {it -> enrich(it.key, it.value)} }

    fun getEnriched() : Map<String, Any>;

}