package canon.parser.xml.upgrade.xslt

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.*

class XSLTTransformSupport {

    companion object {
        fun getDefaultTransformers(): List<String> {
            val pathToTransformers = "/xml/xslt/transformers/"
            return listOf("transform_0.0.0.xslt","transform_2.0.0.xslt").map { pathToTransformers.plus(it) }
        }

        fun getResourceFiles(path: String): List<String> = getResourceAsStream(path).use{
            return if(it == null) emptyList()
            else BufferedReader(InputStreamReader(it)).readLines()
        }

        private fun getResourceAsStream(resource: String): InputStream? = XSLTTransformSupport::class.java.getResourceAsStream(resource)

        fun getCanonVersion(): String {
            val prop = Properties()
            prop.load( this::class.java.getResourceAsStream("/config.properties"))
            return prop.getOrDefault("version","1.9.0") as String
        }

    }

}