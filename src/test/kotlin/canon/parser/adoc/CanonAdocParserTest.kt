package canon.parser.adoc

import canon.parser.adoc.ast.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

@Disabled(value="Code generation isnt working")
class CanonAdocParserTest {

    @Test
    fun parseHeadline() {
        val tree = CanonAdocParser.parse("= text")
        assertEquals((tree[0] as HeadlineAST).text, "text")
    }

    @Test
    fun parseImage() {
        val tree = CanonAdocParser.parse("image::http://example.com/image.jpg")
        assertEquals((tree[0] as ImageAST).src, "http://example.com/image.jpg")
    }

    @Test
    fun parseBold() {
        val tree = CanonAdocParser.parse("*text*")
        assertEquals((tree[0] as BoldAST).text, "text")
    }

    @Test
    fun parseList() {
        val tree = CanonAdocParser.parse("""
. itemA
. itemB
. itemC
. itemD
        """.trimIndent())

        val items = (tree[0] as ItemsAST).items
        assertEquals(items.size, 4)
        assertEquals((items[0] as ItemAST).text, "itemA")
        assertEquals((items[1] as ItemAST).text, "itemB")
        assertEquals((items[2] as ItemAST).text, "itemC")
        assertEquals((items[3] as ItemAST).text, "itemD")
    }

    @Test
    fun parseCode() {
        val tree = CanonAdocParser.parse("""
----
code
----
        """.trimIndent())

        assertEquals((tree[0] as CodeAST).lines.size, 1)
    }

    @Test
    fun parseText() {
        val tree = CanonAdocParser.parse("""
Dieser Switch ist dazu da um Behaviours inaktiv zu schalten. Man erkennt einen inaktiven Prozess ausßerhalb des
Bpmn-Designers daran das er in der Behaviour Übersicht einen grauen Label mit der Beschriftung "inaktiv" hat.
        """.trimIndent())

        println((tree[0] as LabelAST).text)
    }

    @Test
    fun parseTag() {
        val tree = CanonAdocParser.parse("""
// tag::on-off-switch[]
Text
// end::on-off-switch[]
        """.trimIndent())

        require(tree.size ==1)
        require(tree[0] is TagAST)
    }

}
