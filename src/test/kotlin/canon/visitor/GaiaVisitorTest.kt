package canon.visitor

import canon.api.IEvaluator
import canon.api.IRenderable
import canon.parser.xml.CanonXmlParser
import canon.parser.xml.CanonXmlParserTest
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.Velocity
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.skyscreamer.jsonassert.JSONAssert
import java.io.StringWriter

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GaiaVisitorTest {

    companion object {
        val mapper = ObjectMapper().writerWithDefaultPrettyPrinter()
    }
    private fun parseXml(path: String, validate: Boolean = true): List<IRenderable> {
        return CanonXmlParser().parse(CanonXmlParserTest::class.java.getResourceAsStream(path),"2.0.0", validate)
    }

    private fun parseJson(path: String): String {
        return CanonXmlParser::class.java.getResourceAsStream(path).reader().readText()
    }

    private fun compare(name: String, ctx: Map<String, Any> = mapOf(), validate: Boolean = true) {
        val visitor = object : GaiaVisitor(ctx, TestEvaluator()) {
            override fun getTimestamp() = 0L
        }
        val renderables = parseXml("/xml/$name.xml", validate)
        val rendered = renderables.map { e -> visitor.visitRenderable(e) }

        val result = mapper.writeValueAsString(rendered)

        println(result)
        JSONAssert.assertEquals(parseJson("/json/$name.json"), result, true)
    }

    @Test
    fun basket1() = compare("basket1")

    @Test
    fun block1() = compare("block1")

    @Test
    fun block2() = compare("block2")

    @Test
    fun blockWithAriaLabel() = compare("blockWithAriaLabel")

    @Test
    fun label1() = compare("label1")

    @Test
    fun label2() = compare("label2")

    @Test
    fun button1() = compare("button1")

    @Test
    fun carousel1() = compare("carousel1", mapOf(
            "result" to listOf(mapOf(
                    "productName" to "Champagne Cuvee",
                    "year" to 2010,
                    "taste" to "Trocken",
                    "pictureUrl" to "http://example.com/picture.jpg",
                    "grapeType" to "Cuvee",
                    "price" to 20.95,
                    "country" to "France",
                    "region" to "Champagne",
                    "tasteNotice" to "text ".repeat(100),
                    "url" to "http://example.com/picture.jpg"
            ))
    ))


    @Test
    fun carousel2() = compare("carousel2", mapOf(
            "result" to listOf(mapOf(
                    "productName" to "Champagne Cuvee",
                    "year" to 2010,
                    "taste" to "Trocken",
                    "pictureUrl" to "http://example.com/picture.jpg",
                    "grapeType" to "Cuvee",
                    "price" to 20.95,
                    "country" to "France",
                    "region" to "Champagne",
                    "tasteNotice" to "text ".repeat(100),
                    "url" to "http://example.com/picture.jpg"
            ))
    ))

    @Test
    fun carousel4() = compare("carousel4", mapOf(
            "result" to listOf(mapOf(
                    "productName" to "Champagne Cuvee",
                    "year" to 2010,
                    "taste" to "Trocken",
                    "pictureUrl" to "http://example.com/picture.jpg",
                    "grapeType" to "Cuvee",
                    "price" to 20.95,
                    "country" to "France",
                    "region" to "Champagne",
                    "tasteNotice" to "text ".repeat(100),
                    "url" to "http://example.com/picture.jpg"
            ))
    ))

    @Test
    fun carousel5() = compare("carousel5")

    @Test
    fun choiceWithSieve1() = compare("choiceWithSieve1")

    @Test
    fun choiceWithSieve5() = compare("choiceWithSieve5")

    @Test
    fun singleChoice5() = compare("singleChoice5")

    @Test
    fun complex1() = compare("complex1", mapOf(
            "result" to listOf(mapOf(
                    "name" to "Max Mustermann",
                    "adress" to "some address",
                    "phone" to "123456789",
                    "email" to "max@mustermann@example.com",
                    "topics" to listOf("A", "B", "C"),
                    "languages" to listOf("Deutsch", "Englisch")
            ))
    ))

    @Test
    fun complex2() = compare("complex2", mapOf(
            "result" to listOf(mapOf(
                    "name" to "Max Mustermann",
                    "adress" to "some address",
                    "phone" to "123456789",
                    "email" to "max@mustermann@example.com",
                    "topics" to listOf("A", "B", "C"),
                    "languages" to listOf("Deutsch", "Englisch")
            ))
    ))

    @Test
    fun map1() = compare("map1", mapOf(), false)

    @Test
    fun item1() = compare("item1", mapOf("items" to listOf("item1", "item2", "item3", "item4")), false)

    @Test
    fun textInput1() = compare("text1", mapOf(), false)

    @Test
    fun upload1() = compare("upload1")

    private class TestEvaluator() : IEvaluator {

        override fun evaluate(expression: String, context: Map<String, Any>): String {
            val ctx = VelocityContext(context)
            val writer = StringWriter()
            Velocity.evaluate(ctx, writer, "", expression)
            return writer.toString()
        }
    }

}
