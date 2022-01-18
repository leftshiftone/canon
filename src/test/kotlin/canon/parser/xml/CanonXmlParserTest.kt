package canon.parser.xml

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger

class CanonXmlParserTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    private fun parseXml(cannonParser: CanonXmlParser, path: String, expectSuccess: Boolean = true) {
        parseXml(cannonParser, path, "2.0.0", expectSuccess)
    }

    private fun parseXml(cannonParser: CanonXmlParser, path: String, version: String, expectSuccess: Boolean = true) {
        try {
            val result = cannonParser.parse(CanonXmlParserTest::class.java.getResourceAsStream(path), version)
            Assertions.assertNotNull(result)
            Assertions.assertTrue(result.isNotEmpty())
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$path' should not throw a validation exception", e)
            }
        }
    }

    @Test
    fun testComplex1() = parseXml(CanonXmlParser(), "/xml/complex1.xml")

    @Test
    fun testComplex2() = parseXml(CanonXmlParser(), "/xml/complex2.xml")

    @Test
    fun testComplexVersion190() = parseXml(CanonXmlParser(), "/xml/complex_version_1.9.0.xml", "1.9.0")

    @Test
    fun span() = parseXml(CanonXmlParser(), "/xml/span.xml", false)

    @Test
    fun items() = parseXml(CanonXmlParser(), "/xml/items.xml")

    @Test
    fun text1() = parseXml(CanonXmlParser(), "/xml/label1.xml")

    @Test
    fun text2() = parseXml(CanonXmlParser(), "/xml/label2.xml")

    @Test
    fun table1() = parseXml(CanonXmlParser(), "/xml/table1.xml", true)

    @Test
    fun table2() = parseXml(CanonXmlParser(), "/xml/table2.xml", false)

    @Test
    fun button1() = parseXml(CanonXmlParser(), "/xml/button1.xml", true)

    @Test
    fun button2() = parseXml(CanonXmlParser(), "/xml/button2.xml", true)

    @Test
    fun button3() = parseXml(CanonXmlParser(), "/xml/button3.xml", false)

    @Test
    fun button4() = parseXml(CanonXmlParser(), "/xml/button4.xml", false)

    @Test
    fun headline2() = parseXml(CanonXmlParser(), "/xml/headline2.xml", false)

    @Test
    fun headline3() = parseXml(CanonXmlParser(), "/xml/headline3.xml", true)

    @Test
    fun headline4() = parseXml(CanonXmlParser(), "/xml/headline4.xml", false)

    @Test
    fun carousel1() = parseXml(CanonXmlParser(), "/xml/carousel1.xml", false)

    @Test
    fun carousel2() = parseXml(CanonXmlParser(), "/xml/carousel2.xml", false)

    @Test
    fun carousel3() = parseXml(CanonXmlParser(), "/xml/carousel3.xml", false)

    @Test
    fun carousel4() = parseXml(CanonXmlParser(), "/xml/carousel4.xml")

    @Test
    fun carousel5() = parseXml(CanonXmlParser(), "/xml/carousel5.xml")

    @Test
    fun block() = parseXml(CanonXmlParser(), "/xml/block.xml", false)

    @Test
    fun form1() = parseXml(CanonXmlParser(), "/xml/form1.xml", false)

    @Test
    fun form2() = parseXml(CanonXmlParser(), "/xml/form2.xml", false)

    @Test
    fun form3() = parseXml(CanonXmlParser(), "/xml/form3.xml")

    @Test
    fun form4() = parseXml(CanonXmlParser(), "/xml/form4.xml")

    @Test
    fun form5() = parseXml(CanonXmlParser(), "/xml/form5.xml", false)

    @Test
    fun transition1() = parseXml(CanonXmlParser(), "/xml/transition1.xml", false)

    @Test
    fun transition2() = parseXml(CanonXmlParser(), "/xml/transition2.xml", false)

    @Test
    fun overlay1() = parseXml(CanonXmlParser(), "/xml/overlay1.xml", false)

    @Test
    fun basket1() = parseXml(CanonXmlParser(), "/xml/basket1.xml")

    @Test
    fun multipleChoice1() = parseXml(CanonXmlParser(), "/xml/multipleChoice1.xml", false)

    @Test
    fun multipleChoice2() = parseXml(CanonXmlParser(), "/xml/multipleChoice2.xml")

    @Test
    fun singleChoice1() = parseXml(CanonXmlParser(), "/xml/singleChoice1.xml", false)

    @Test
    fun singleChoice2() = parseXml(CanonXmlParser(), "/xml/singleChoice2.xml")

    @Test
    fun singleChoice3() = parseXml(CanonXmlParser(), "/xml/singleChoice3.xml")

    @Test
    fun singleChoice4() = parseXml(CanonXmlParser(), "/xml/singleChoice4.xml", false)

    @Test
    fun singleChoice5() = parseXml(CanonXmlParser(), "/xml/singleChoice5.xml", true)

    @Test
    fun choiceWithSieve1() = parseXml(CanonXmlParser(), "/xml/choiceWithSieve1.xml")

    @Test
    fun choiceWithSieve2() = parseXml(CanonXmlParser(), "/xml/choiceWithSieve2.xml", false)

    @Test
    fun choiceWithSieve3() = parseXml(CanonXmlParser(), "/xml/choiceWithSieve3.xml", false)

    @Test
    fun choiceWithSieve4() = parseXml(CanonXmlParser(), "/xml/choiceWithSieve4.xml", false)

    @Test
    fun choiceWithSieve5() = parseXml(CanonXmlParser(), "/xml/choiceWithSieve5.xml")

    @Test
    fun nameArg1() = parseXml(CanonXmlParser(), "/xml/nameArg1.xml")

    @Test
    fun nameArg2() = parseXml(CanonXmlParser(), "/xml/nameArg2.xml", false)

    @Test
    fun nameArg3() = parseXml(CanonXmlParser(), "/xml/nameArg3.xml", false)


    @Test
    fun parallelTest() {
        val classUnderTest = CanonXmlParser()
        val count = 50
        val successCount = AtomicInteger(0)
        val barrier = CountDownLatch(1)
        val endBarrier = CountDownLatch(count)
        (1..count).forEach {
            Thread {
                try {
                    barrier.await(10, TimeUnit.SECONDS)
                    println("Thread-${it} starting execution")
                    try {
                        classUnderTest.parse(
                            CanonXmlParserTest::class.java.getResourceAsStream("/xml/complex1.xml"),
                            "2.0.0"
                        )
                        successCount.incrementAndGet()
                    } catch (e: Exception) {
                        log.error("Error: ", e)
                        throw e
                    }
                } finally {
                    endBarrier.countDown()
                }
            }.start()
        }
        barrier.countDown()
        endBarrier.await(60, TimeUnit.SECONDS)
        org.assertj.core.api.Assertions.assertThat(successCount)
            .hasValue(count)
    }

    @Test
    fun unescapingNullReturnsEmptyString() {
        val result = CanonXmlParser().unescapeAtreusExpression(null)
        Assertions.assertEquals(result, "")
    }

    @Test
    fun unescapeQuot() {
        val result = CanonXmlParser().unescapeAtreusExpression("{{ &quot;X&quot; }}")
        Assertions.assertEquals("""{{ "X" }}""", result)
    }

    @Test
    fun unescapeEscapedQuot() {
        val result = CanonXmlParser().unescapeAtreusExpression("{{ \\&quot;X\\&quot; }}")
        Assertions.assertEquals("""{{ &quot;X&quot; }}""", result)
    }

    @Test
    fun unescapeUppercaseQuot() {
        val result = CanonXmlParser().unescapeAtreusExpression("{{ &QUOT;X&QUOT; }}")
        Assertions.assertEquals("""{{ "X" }}""", result)
    }

    @Test
    fun unescapeUppercaseEscapedQuot() {
        val result = CanonXmlParser().unescapeAtreusExpression("{{ \\&QUOT;X\\&QUOT; }}")
        Assertions.assertEquals("""{{ &quot;X&quot; }}""", result)
    }

}
