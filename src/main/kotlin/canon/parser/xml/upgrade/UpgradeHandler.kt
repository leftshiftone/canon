package canon.parser.xml.upgrade

/**
 * This interface defines a XML Upgrade handler
 */
interface UpgradeHandler {

    /**
     * It checks if a version must be upgraded
     * @paramversion to check if is behind the current version
     * @return whether the version requires an upgrade or not
     */
    fun isUpgradeRequired(version: String = "1.9.0"): Boolean

    /**
     * It provides the current version of Canon
     * @return canon version
     */
    fun getLatestVersion(): String

    /**
     * It performs the upgrade of the given xml to the newest available version.
     * @param rawXml XML string that should be updated
     * @param rawXmlVersion version of the XML string
     * @return XML transformed according to the latest version. If the rawXmlVersion does not require an upgrade, the rawXML will be returned.
     */
    fun upgrade(rawXml : String, rawXmlVersion: String = "1.9.0"): String


    /**
     * It performs the upgrade of the given utterance to the newest available version.
     * @param utterance a Map containing the XML that should/can be upgraded
     *  Key -> language code. e.g. "de"
     *  Value -> List of XML strings in canon format.
     * @param version of the XML that should/can be upgraded
     * @return a Utterance containing the transformed/upgraded XML strings
     */
    fun upgrade(utterance : Map<String, List<String>> , rawXmlVersion: String = "1.9.0"): Map<String, List<String>>

}
