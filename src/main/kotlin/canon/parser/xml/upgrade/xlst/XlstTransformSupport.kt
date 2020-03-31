package canon.parser.xml.upgrade.xlst

import canon.parser.xml.upgrade.SemanticVersion
import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class XlstTransformSupport {

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
        val TRANSFORMER_PREFIX="transform_"
        val TRANSFORMER_EXTENSION=".xlst"

        fun extractTransformations(relativePath: String): List<SemanticVersion> {
            log.debug("Retrieving the transformer configuration in path $relativePath with prefix [$TRANSFORMER_PREFIX] and extension [$TRANSFORMER_EXTENSION]")
            return getResourceFiles(relativePath)
                    .map { fileName -> fileName.substringAfter(TRANSFORMER_PREFIX).substringBefore(TRANSFORMER_EXTENSION)  }
                    .filter { extractedVersionFromFile -> SemanticVersion.isValidVersion(extractedVersionFromFile) }
                    .map { version -> SemanticVersion(version) }
        }

        fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use{
            return if(it == null) emptyList()
            else BufferedReader(InputStreamReader(it)).readLines()
        }

        private fun getResourceAsStream(resource: String): InputStream? = XlstTransformSupport::class.java.getResourceAsStream(resource)

        fun getCanonVersion(): String {
            val prop = Properties()
            prop.load( this::class.java.getResourceAsStream("/config.properties"))
            return prop.getOrDefault("version","1.9.0") as String
        }

    }

}