package canon.parser.xml.upgrade

/**
 * This interface defines a XML Upgrade handler
 */
interface UpgradeHandler {

    /**
     * It checks if a version must be upgraded
     * @param version Version to check if is behind the current version
     */
    fun isUpgradeRequired(version: String = "1.9.0"): Boolean

    /**
     * It perfoms the upgrade of the given xml to the newest available version.
     */
    fun upgrade(rawXml : String, rawXmlVersion: String = "1.9.0"): String

}
