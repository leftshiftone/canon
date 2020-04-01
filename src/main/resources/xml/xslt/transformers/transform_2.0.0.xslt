<?xml version="1.0" encoding="iso-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" omit-xml-declaration="yes" indent="yes"/>
    <xsl:strip-space elements="*"/>
    <xsl:template match="node()|@*">
        <xsl:copy>
            <xsl:apply-templates select="node()|@*"/>
        </xsl:copy>

    </xsl:template>


    <xsl:template match="container">
        <xsl:copy>
            <xsl:apply-templates select="@*|node()"/>
        </xsl:copy>
    </xsl:template>

    <xsl:template match="text">
        <!-- text was replaced for label by the XSLT Transformer -->
        <xsl:comment> text element was replaced for label by the XSLT Transformer</xsl:comment>
        <xsl:if test="./@name"> <xsl:comment>attribute name of element text was removed when converting it to label by the XSLT Transformer</xsl:comment></xsl:if>
        <label><xsl:apply-templates select="@*|node()"/></label>
        <automaticUpgraded/>
    </xsl:template>

    <!-- attribute name of element text was removed when converting it to label by the XSLT Transformer -->
    <xsl:template match="text/@name"/>

    <!-- textInput element was replaced for text by the XSLT Transformer -->
    <xsl:template match="textInput">
        <xsl:comment> textInput element was replaced for text by the XSLT Transformer</xsl:comment>
        <text><xsl:apply-templates select="@*|node()"/></text>
        <automaticUpgraded/>
    </xsl:template>


    <xsl:template match="textInput/node()"/>
    <!-- datePicker element was removed by the XSLT Transformer -->
    <xsl:template match="datePicker">
        <xsl:comment>datePicker element was removed by the XSLT Transformer</xsl:comment>
        <automaticUpgraded/>
    </xsl:template>

    <!-- dateTimePicker element was removed by the XSLT Transformer -->
    <xsl:template match="dateTimePicker">
        <xsl:comment> dateTimePicker element was removed by the XSLT Transformer</xsl:comment>
        <automaticUpgraded/>
    </xsl:template>

    <!-- Calendar element was removed by the XSLT Transformer -->
    <xsl:template match="calendar">
        <xsl:comment> Calendar element was removed by the XSLT Transformer</xsl:comment>
        <automaticUpgraded/>
    </xsl:template>

    <!-- Checkbox element was commented out by the XSLT Transformer -->
    <xsl:template match="checkbox">
        <xsl:comment>Checkbox element was commented out by the XSLT Transformer</xsl:comment>
        <xsl:text disable-output-escaping="yes">
    &lt;!-- before migration: </xsl:text>
        <xsl:copy>
            <xsl:apply-templates select="@*|node()" />
        </xsl:copy>
        <xsl:text disable-output-escaping="yes"> --&gt;
    </xsl:text>
    </xsl:template>

</xsl:stylesheet>