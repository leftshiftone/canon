package canon.api

/**
 * @author Michael Mair
 */
interface IEnrichable {

    fun enrich(key: String?, value: Any?)

    fun enrich(enriched: Map<String?, Any?>) {
        enriched.forEach { (key: String?, value: Any?) -> this.enrich(key, value) }
    }

    fun getEnriched(): Map<String?, Any?>?
}