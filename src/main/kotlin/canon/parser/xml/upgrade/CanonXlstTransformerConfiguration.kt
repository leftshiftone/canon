package canon.parser.xml.upgrade

import org.slf4j.LoggerFactory
import java.util.*

class CanonXlstTransformerConfiguration : Comparable<CanonXlstTransformerConfiguration> {

    val transformerLocation : String
    val version: SemanticVersion

    companion object {

        val log = LoggerFactory.getLogger(this::class.java)
        @JvmStatic
        fun isValidTransformerPath(transformerPath : String) : Boolean {
            log.debug("validating version $transformerPath")
            val matchResult = findVersionMatches(transformerPath)

            if(matchResult!=null
                    && matchResult.groups.isNotEmpty()
                    && matchResult.groups.size > 4
                    && matchResult.groups[2]!=null
                    && matchResult.groups[3]!=null
                    && matchResult.groups[4]!=null){
                val transformerLocationMatch = extractRelativePath(transformerPath)
                if(transformerLocationMatch!=null){
                    log.debug("Version $transformerPath is valid")
                    return true
                }

            }
            log.debug("Version $transformerPath is not valid")
            return false
        }

        fun findVersionMatches(transformerPath: String) : MatchResult?{
            val regex = "^(.*)transform_(\\d+).?(\\d+).?(\\*|\\d+)-?(.*).xlst\$".toRegex()
            return regex.find(transformerPath)
        }


        fun extractRelativePath(transformerPath: String) : String{
            return transformerPath.substringAfterLast(".jar!")
        }

    }

    constructor(_transformerLocation: String, _version: SemanticVersion){
        this.transformerLocation=_transformerLocation
        this.version=_version

    }

    constructor(transformerLocation : String) {
        this.transformerLocation= extractRelativePath(transformerLocation)
        val parseVersion = parseTransformerLocation(this.transformerLocation)
        this.version= parseVersion
    }

    private fun parseTransformerLocation(transformerLocation: String) : SemanticVersion {
        val matchResult = findVersionMatches(transformerLocation)
        return SemanticVersion(matchResult!!.groups[2]!!.value, matchResult.groups[3]!!.value, matchResult.groups[4]!!.value)
    }

    override fun compareTo(other: CanonXlstTransformerConfiguration): Int {
        return this.version.compareTo(other.version);
    }

    override fun toString(): String {
        return "Transformer [$version] located in $transformerLocation"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        return version == (other as CanonXlstTransformerConfiguration).version
    }

    override fun hashCode(): Int {
        return Objects.hash(version)
    }
}