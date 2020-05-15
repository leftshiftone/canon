<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="xml" version="1.0" encoding="UTF-8" omit-xml-declaration="yes"/>
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
        <label><xsl:apply-templates select="@*|node()"/></label>
    </xsl:template>
    <xsl:template match="text/@name"/>

    <xsl:template match="textInput">
        <text><xsl:apply-templates select="@*|node()"/></text>
    </xsl:template>


    <xsl:template match="textInput/node()"/>
    <xsl:template match="datePicker">
        <!-- ATTENTION: the 'manual action needed' comment is explicitly defined in XSLTUpgradeHandler and is used to determine
             whether markup needs manual a manual action or not -->
        <xsl:comment>manual action needed</xsl:comment>
        <xsl:comment>datePicker element was removed</xsl:comment>
    </xsl:template>

    <xsl:template match="dateTimePicker">
        <!-- ATTENTION: the 'manual action needed' comment is explicitly defined in XSLTUpgradeHandler and is used to determine
             whether markup needs manual a manual action or not -->
        <xsl:comment>manual action needed</xsl:comment>
        <xsl:comment>dateTimePicker element was removed</xsl:comment>
    </xsl:template>

    <xsl:template match="calendar">
        <!-- ATTENTION: the 'manual action needed' comment is explicitly defined in XSLTUpgradeHandler and is used to determine
             whether markup needs manual a manual action or not -->
        <xsl:comment>manual action needed</xsl:comment>
        <xsl:comment>calendar element was removed</xsl:comment>
    </xsl:template>

    <xsl:template match="checkbox">
        <!-- ATTENTION: the 'manual action needed' comment is explicitly defined in XSLTUpgradeHandler and is used to determine
             whether markup needs manual a manual action or not -->
        <xsl:comment>manual action needed</xsl:comment>
        <xsl:text disable-output-escaping="yes">&lt;!--use choice instead of checkbox: </xsl:text>
        <xsl:copy>
            <xsl:apply-templates select="@*|node()" />
        </xsl:copy>
        <xsl:text disable-output-escaping="yes">--&gt;</xsl:text>
    </xsl:template>

</xsl:stylesheet>