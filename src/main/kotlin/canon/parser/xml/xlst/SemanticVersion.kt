package canon.parser.xml.xlst

import org.slf4j.LoggerFactory
import java.util.*

class SemanticVersion : Comparable<SemanticVersion> {

    val major : String


    val minor : String
    val patch : String
    companion object {

        val log = LoggerFactory.getLogger(this::class.java)
        @JvmStatic
        fun isValidVersion(versionToValidate : String) : Boolean {
            log.debug("validating version $versionToValidate")
            val matchResult = findVersionMatches(versionToValidate)
            if(matchResult!=null
                    && matchResult.groups.isNotEmpty()
                    && matchResult.groups.size > 3
                    && matchResult.groups[1]!=null
                    && matchResult.groups[2]!=null
                    && matchResult.groups[3]!=null){
                log.debug("Version $versionToValidate is valid")
                return true
            }
            log.debug("Version $versionToValidate is not valid")
            return false
        }

        fun findVersionMatches(version: String) : MatchResult?{
            val regex = "^(\\d+).?(\\d+).?(\\*|\\d+)\$".toRegex()
            return regex.find(version)
        }
    }

    constructor(_major: String, _minor: String, _patch: String){
        major=_major
        minor=_minor
        patch=_patch
    }

    constructor(version : String) {
        val parseVersion = parseVersion(version)
        major= parseVersion.major
        minor= parseVersion.minor
        patch= parseVersion.patch
    }

    private fun parseVersion(version: String) : SemanticVersion{
        val matchResult = findVersionMatches(version)
        return SemanticVersion(matchResult!!.groups[1]!!.value, matchResult.groups[2]!!.value, matchResult.groups[3]!!.value)
    }

    override fun compareTo(other: SemanticVersion): Int {
        if(this.major.compareTo(other.major)==0){
            if(this.minor.compareTo(other.minor)==0){
                return this.patch.compareTo(other.patch)
            } else return this.minor.compareTo(other.minor)
        }else return this.major.compareTo(other.major)
    }

    override fun toString(): String {
        return "$major.$minor.$patch"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        return major == (other as SemanticVersion).major && minor == other.minor && patch== other.patch
    }

    override fun hashCode(): Int {
        return Objects.hash(major, minor,patch)
    }
}