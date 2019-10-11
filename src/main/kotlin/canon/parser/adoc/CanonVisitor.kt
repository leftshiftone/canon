package canon.parser.adoc

//import canon.antlr.CanonBaseVisitor
//import canon.antlr.CanonParser
//import canon.parser.adoc.ast.*

class CanonVisitor //: CanonBaseVisitor<List<AbstractAST>>()
{

   /* override fun visitHeadline(ctx: CanonParser.HeadlineContext): List<AbstractAST> {
        return listOf(HeadlineAST(ctx.text.substring(2)))
    }

    override fun visitImage(ctx: CanonParser.ImageContext): List<AbstractAST> {
        return listOf(ImageAST(ctx.text.substring(7)))
    }

    override fun visitBold(ctx: CanonParser.BoldContext): List<AbstractAST> {
        return listOf(BoldAST(ctx.text.substring(1, ctx.text.length - 1)))
    }

    override fun visitItems(ctx: CanonParser.ItemsContext): List<AbstractAST> {
        return listOf(ItemsAST(super.visitItems(ctx)))
    }

    override fun visitItem(ctx: CanonParser.ItemContext): List<AbstractAST> {
        return listOf(ItemAST(ctx.text.substring(2)))
    }

    override fun visitCode(ctx: CanonParser.CodeContext): List<AbstractAST> {
        return listOf(CodeAST(super.visitCode(ctx)))
    }

    override fun visitText(ctx: CanonParser.TextContext): List<AbstractAST> {
        return listOf(LabelAST(ctx.text))
    }

    override fun visitTag(ctx: CanonParser.TagContext): List<AbstractAST> {
        return listOf(TagAST(super.visitTag(ctx)))
    }

    override fun visitIntent(ctx: CanonParser.IntentContext): List<AbstractAST> {
        return listOf(CommentAST("intent", ctx.text.substring(11)))
    }

    override fun aggregateResult(aggregate: List<AbstractAST>?, nextResult: List<AbstractAST>?): List<AbstractAST> {
        val list = ArrayList<AbstractAST>()
        if (aggregate != null) list.addAll(aggregate)
        if (nextResult != null) list.addAll(nextResult)
        return list
    }*/

}
