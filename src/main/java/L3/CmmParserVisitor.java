// Generated from /Users/cynyard/Documents/大学学习/大四上/编译原理-助教版/ANTLR-Labs/src/main/java/L3/CmmParser.g4 by ANTLR 4.9.2
package L3;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CmmParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CmmParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CmmParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#extDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtDef(CmmParser.ExtDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#extDecList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExtDecList(CmmParser.ExtDecListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#specifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpecifier(CmmParser.SpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#structSpecifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructSpecifier(CmmParser.StructSpecifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#optTag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptTag(CmmParser.OptTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#tag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTag(CmmParser.TagContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#varDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDec(CmmParser.VarDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#funDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunDec(CmmParser.FunDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#varList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarList(CmmParser.VarListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#paramDec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamDec(CmmParser.ParamDecContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#compSt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompSt(CmmParser.CompStContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#stmtList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtList(CmmParser.StmtListContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtExp}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtExp(CmmParser.StmtExpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtCompSt}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtCompSt(CmmParser.StmtCompStContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtReturn}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtReturn(CmmParser.StmtReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtIf}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtIf(CmmParser.StmtIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtIfElse}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtIfElse(CmmParser.StmtIfElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stmtWhile}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmtWhile(CmmParser.StmtWhileContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#defList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefList(CmmParser.DefListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDef(CmmParser.DefContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#decList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecList(CmmParser.DecListContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDec(CmmParser.DecContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expDot}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpDot(CmmParser.ExpDotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expFunc}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpFunc(CmmParser.ExpFuncContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expOr}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpOr(CmmParser.ExpOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expRelop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpRelop(CmmParser.ExpRelopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expPlusMinus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpPlusMinus(CmmParser.ExpPlusMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expAssignop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpAssignop(CmmParser.ExpAssignopContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expParenthesis}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpParenthesis(CmmParser.ExpParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expFloat}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpFloat(CmmParser.ExpFloatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expAnd}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpAnd(CmmParser.ExpAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expFuncArgs}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpFuncArgs(CmmParser.ExpFuncArgsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expStarDiv}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpStarDiv(CmmParser.ExpStarDivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expInt}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpInt(CmmParser.ExpIntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expMinusNot}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpMinusNot(CmmParser.ExpMinusNotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expId}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpId(CmmParser.ExpIdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code expBrackets}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpBrackets(CmmParser.ExpBracketsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CmmParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(CmmParser.ArgsContext ctx);
}