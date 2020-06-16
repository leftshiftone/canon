<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" omit-xml-declaration="yes"/>
    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="selection/block">
        <selectionItem>
            <xsl:apply-templates select="@*|node()"/>
        </selectionItem>
    </xsl:template>


    <xsl:template match="selection/block/block">
        <xsl:if test="not(block)">
            <xsl:choose>
                <xsl:when test="label | image">
                    <xsl:choose>
                        <xsl:when test="@name">
                            <selectable>
                                <xsl:apply-templates select="@*|node()"/>
                            </selectable>
                        </xsl:when>
                        <xsl:otherwise>
                            <selectable name="result">
                                <xsl:apply-templates select="@*|node()"/>
                            </selectable>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:copy>
                        <xsl:apply-templates select="@*|node()"/>
                    </xsl:copy>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:if>
        <xsl:if test="block">
            <xsl:copy>
                <xsl:apply-templates select="@*|node()"/>
            </xsl:copy>
        </xsl:if>
    </xsl:template>

    <xsl:template match="selection/block/block/block">
        <xsl:choose>
            <xsl:when test="@name">
                <selectable>
                    <xsl:apply-templates select="@*|node()"/>
                </selectable>
            </xsl:when>
            <xsl:otherwise>
                <selectable name="result">
                    <xsl:apply-templates select="@*|node()"/>
                </selectable>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>

</xsl:stylesheet>