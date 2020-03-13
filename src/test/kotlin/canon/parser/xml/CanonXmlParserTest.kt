package canon.parser.xml

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class CanonXmlParserTest {

    private fun parseXml(path: String, expectSuccess: Boolean = true) {
        try {
            val result = CanonXmlParser().parse(CanonXmlParserTest::class.java.getResourceAsStream(path))
            Assertions.assertNotNull(result)
            Assertions.assertTrue(result.isNotEmpty())
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$path' should not throw a validation exception", e)
            }
        }
    }


    private fun parseXml(cannonParser: CanonXmlParser, path: String, expectSuccess: Boolean = true) {
        try {
            val result = cannonParser.parse(CanonXmlParserTest::class.java.getResourceAsStream(path))
            Assertions.assertNotNull(result)
            Assertions.assertTrue(result.isNotEmpty())
        } catch (e: Exception) {
            if (expectSuccess) {
                Assertions.fail<String>("xml '$path' should not throw a validation exception", e)
            }
        }
    }

    @Test
    fun testComplex1() = parseXml("/xml/complex1.xml")

    @Test
    fun testComplex2() = parseXml("/xml/complex2.xml")

    @Test
    fun span() = parseXml("/xml/span.xml", false)

    @Test
    fun items() = parseXml("/xml/items.xml")

    @Test
    fun text1() = parseXml("/xml/text1.xml")

    @Test
    fun text2() = parseXml("/xml/text2.xml")

    @Test
    fun table1() = parseXml("/xml/table1.xml")

    @Test
    fun table2() = parseXml("/xml/table2.xml", false)

    @Test
    fun carousel1() = parseXml("/xml/carousel1.xml", false)

    @Test
    fun carousel2() = parseXml("/xml/carousel2.xml", false)

    @Test
    fun carousel3() = parseXml("/xml/carousel3.xml", false)

    @Test
    fun carousel4() = parseXml("/xml/carousel4.xml")

    @Test
    fun carousel5() = parseXml("/xml/carousel5.xml")

    @Test
    fun block() = parseXml("/xml/block.xml", false)

    @Test
    fun form1() = parseXml("/xml/form1.xml", false)

    @Test
    fun form2() = parseXml("/xml/form2.xml", false)

    @Test
    fun form3() = parseXml("/xml/form3.xml")

    @Test
    fun form4() = parseXml("/xml/form4.xml")

    @Test
    fun form5() = parseXml("/xml/form5.xml", false)

    @Test
    fun datepicker1() = parseXml("/xml/datepicker1.xml", false)

    @Test
    fun datepicker2() = parseXml("/xml/datepicker2.xml", false)

    @Test
    fun datepicker3() = parseXml("/xml/datepicker3.xml", false)

    @Test
    fun datepicker4() = parseXml("/xml/datepicker4.xml", false)

    @Test
    fun transition1() = parseXml("/xml/transition1.xml", false)

    @Test
    fun transition2() = parseXml("/xml/transition2.xml", false)

    @Test
    fun overlay1() = parseXml("/xml/overlay1.xml", false)

    @Test
    fun basket1() = parseXml("/xml/basket1.xml")

    @Test
    fun multipleChoice1() = parseXml("/xml/multipleChoice1.xml", false)

    @Test
    fun multipleChoice2() = parseXml("/xml/multipleChoice2.xml")

    @Test
    fun singleChoice1() = parseXml("/xml/singleChoice1.xml", false)

    @Test
    fun singleChoice2() = parseXml("/xml/singleChoice2.xml")

    @Test
    fun singleChoice3() = parseXml("/xml/singleChoice3.xml")

    @Test
    fun singleChoice4() = parseXml("/xml/singleChoice4.xml", false)

    @Test
    fun choiceWithSieve1() = parseXml("/xml/choiceWithSieve1.xml")

    @Test
    fun choiceWithSieve2() = parseXml("/xml/choiceWithSieve2.xml", false)

    @Test
    fun choiceWithSieve3() = parseXml("/xml/choiceWithSieve3.xml", false)

    @Test
    fun choiceWithSieve4() = parseXml("/xml/choiceWithSieve4.xml", false)

    @Test
    fun choiceWithSieve5() = parseXml("/xml/choiceWithSieve5.xml")

    @Test
    fun nameArg1() = parseXml("/xml/nameArg1.xml")

    @Test
    fun nameArg2() = parseXml("/xml/nameArg2.xml", false)

    @Test
    fun nameArg3() = parseXml("/xml/nameArg3.xml", false)


    @Test
    fun parallelTest() {
        val barrier = CountDownLatch(1)
        val endBarrier = CountDownLatch(25)
        val canonXmlParser = CanonXmlParser()
        (0..24).forEach {
            Thread {
                try{
                    barrier.await(10, TimeUnit.SECONDS)
                    println("Thread-${it} starting execution")
                    parseXml(canonXmlParser,"/xml/complex1.xml")
                } finally {
                    endBarrier.countDown()
                }
            }.start()
        }
        barrier.countDown()
        endBarrier.await(60, TimeUnit.SECONDS)
    }

}
