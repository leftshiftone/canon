package canon.parser.xml.upgrade.xslt

import canon.parser.xml.upgrade.SemanticVersion
import org.slf4j.LoggerFactory
import kotlin.concurrent.getOrSet

/**
 * Stores transformers by version for re-use and creates them if they have not been created for the current thread.
 *
 * XSLTTransformer cannot be used across threads.
 * Thread local transformer re-use increases performance,
 * especially for outgoing transformations which can happen for lots of entities.
 */
class TransformerCache(private val transformerConfig: List<XSLTTransformerConfiguration>) {

    companion object {
        private val log = LoggerFactory.getLogger(TransformerCache::class.java)
    }

    val transformers = ThreadLocal<HashMap<String, List<XSLTTransformer>>>()

    fun transformersForVersion(version: String): List<XSLTTransformer> {
        val cached = transformers().get(version)

        if (cached != null) {
            return cached
        }

        val newTransformers = buildTransformers(version)

        transformers().put(version, newTransformers)

        return newTransformers
    }

    fun buildTransformers(currentVersion: String): List<XSLTTransformer> {
        log.debug("Build transformation path for version $currentVersion")
        val transformations = retrieveAvailableTransformationsForAVersion(currentVersion)
        return transformations
                .map {
                    XSLTTransformer(it)
                }
                .toList()
    }

    fun retrieveAvailableTransformationsForAVersion(currentVersion: String): List<XSLTTransformerConfiguration> {
        log.debug("Retrieve available transformations for version: $currentVersion")
        val curVersion = SemanticVersion(currentVersion)
        val allAvailableTransformations = this.transformerConfig.map { config -> config.version }.toList()
        log.debug("AvailableTransformations : ${allAvailableTransformations.joinToString(",")}")
        val transformationForGivenVersion = this.transformerConfig
                .filter { it.version > curVersion }
        log.debug("AvailableTransformations for $currentVersion : ${allAvailableTransformations.joinToString(",")}")
        return transformationForGivenVersion

    }

    private fun transformers(): HashMap<String, List<XSLTTransformer>> {
        return transformers.getOrSet {
            log.debug("initializing new transformer cache for thread ${Thread.currentThread().name}")
            HashMap()
        }
    }
}
