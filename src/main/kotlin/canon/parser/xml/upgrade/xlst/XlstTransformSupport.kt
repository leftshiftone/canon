package canon.parser.xml.upgrade.xlst

import org.slf4j.LoggerFactory
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class XlstTransformSupport {

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)

        fun getDefaultTransformers(): List<String> {
            val pathToTransformers = "/xml/xlst/transformers/"
            return getResourceFiles(pathToTransformers).map { pathToTransformers.plus(it) }
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