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


    <xsl:template match="selection/block/block/block">
        <selectable>
            <xsl:apply-templates select="@*|node()"/>
        </selectable>
    </xsl:template>

    <xsl:template match="selection/block/@name">
        <xsl:attribute name="id">
            <xsl:value-of select="."/>
        </xsl:attribute>
    </xsl:template>

    <xsl:template match="selection/block/block/@name">
        <xsl:attribute name="id">
            <xsl:value-of select="."/>
        </xsl:attribute>
    </xsl:template>

</xsl:stylesheet>