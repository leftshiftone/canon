package canon.parser.adoc

import canon.parser.adoc.ast.*
import jdk.nashorn.internal.ir.annotations.Ignore
import org.junit.jupiter.api.Test


class CanonAdocTest {

    /**
    @Test
    fun test() {
        val stream = CanonAdocTest::class.java.getResourceAsStream("/test.adoc")
        val tree = CanonAdocParser.parse(stream)

        require(tree.size == 1)
        require(tree[0] is TagAST)
        require((tree[0] as TagAST).list[0] is CommentAST)
        require((tree[0] as TagAST).list[1] is CommentAST)
        require((tree[0] as TagAST).list[2] is HeadlineAST)
        require((tree[0] as TagAST).list[3] is ImageAST)
        require((tree[0] as TagAST).list[4] is LabelAST)
        require((tree[0] as TagAST).list[5] is ItemsAST)
        require((tree[0] as TagAST).list[6] is BoldAST)
        require((tree[0] as TagAST).list[7] is LabelAST)
        require((tree[0] as TagAST).list[8] is CodeAST)
    }
**/
}
