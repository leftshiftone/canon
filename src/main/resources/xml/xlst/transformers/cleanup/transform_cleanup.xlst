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
   <xsl:apply-templates/>
</xsl:template>
<xsl:template match="markup">
  <xsl:apply-templates/>
</xsl:template>
</xsl:stylesheet>