// Generated from /Users/cynyard/Documents/大学学习/大四上/编译原理-助教版/ANTLR-Labs/src/main/java/L3/CmmParser.g4 by ANTLR 4.9.2
package L4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CmmParser}.
 */
public interface CmmParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CmmParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CmmParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#extDef}.
	 * @param ctx the parse tree
	 */
	void enterExtDef(CmmParser.ExtDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#extDef}.
	 * @param ctx the parse tree
	 */
	void exitExtDef(CmmParser.ExtDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#extDecList}.
	 * @param ctx the parse tree
	 */
	void enterExtDecList(CmmParser.ExtDecListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#extDecList}.
	 * @param ctx the parse tree
	 */
	void exitExtDecList(CmmParser.ExtDecListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#specifier}.
	 * @param ctx the parse tree
	 */
	void enterSpecifier(CmmParser.SpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#specifier}.
	 * @param ctx the parse tree
	 */
	void exitSpecifier(CmmParser.SpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#structSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterStructSpecifier(CmmParser.StructSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#structSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitStructSpecifier(CmmParser.StructSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#optTag}.
	 * @param ctx the parse tree
	 */
	void enterOptTag(CmmParser.OptTagContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#optTag}.
	 * @param ctx the parse tree
	 */
	void exitOptTag(CmmParser.OptTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#tag}.
	 * @param ctx the parse tree
	 */
	void enterTag(CmmParser.TagContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#tag}.
	 * @param ctx the parse tree
	 */
	void exitTag(CmmParser.TagContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#varDec}.
	 * @param ctx the parse tree
	 */
	void enterVarDec(CmmParser.VarDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#varDec}.
	 * @param ctx the parse tree
	 */
	void exitVarDec(CmmParser.VarDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#funDec}.
	 * @param ctx the parse tree
	 */
	void enterFunDec(CmmParser.FunDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#funDec}.
	 * @param ctx the parse tree
	 */
	void exitFunDec(CmmParser.FunDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#varList}.
	 * @param ctx the parse tree
	 */
	void enterVarList(CmmParser.VarListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#varList}.
	 * @param ctx the parse tree
	 */
	void exitVarList(CmmParser.VarListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#paramDec}.
	 * @param ctx the parse tree
	 */
	void enterParamDec(CmmParser.ParamDecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#paramDec}.
	 * @param ctx the parse tree
	 */
	void exitParamDec(CmmParser.ParamDecContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#compSt}.
	 * @param ctx the parse tree
	 */
	void enterCompSt(CmmParser.CompStContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#compSt}.
	 * @param ctx the parse tree
	 */
	void exitCompSt(CmmParser.CompStContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#stmtList}.
	 * @param ctx the parse tree
	 */
	void enterStmtList(CmmParser.StmtListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#stmtList}.
	 * @param ctx the parse tree
	 */
	void exitStmtList(CmmParser.StmtListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtExp}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmtExp(CmmParser.StmtExpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtExp}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmtExp(CmmParser.StmtExpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtCompSt}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmtCompSt(CmmParser.StmtCompStContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtCompSt}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmtCompSt(CmmParser.StmtCompStContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtReturn}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmtReturn(CmmParser.StmtReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtReturn}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmtReturn(CmmParser.StmtReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtIf}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmtIf(CmmParser.StmtIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtIf}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmtIf(CmmParser.StmtIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtIfElse}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmtIfElse(CmmParser.StmtIfElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtIfElse}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmtIfElse(CmmParser.StmtIfElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stmtWhile}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterStmtWhile(CmmParser.StmtWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stmtWhile}
	 * labeled alternative in {@link CmmParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitStmtWhile(CmmParser.StmtWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#defList}.
	 * @param ctx the parse tree
	 */
	void enterDefList(CmmParser.DefListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#defList}.
	 * @param ctx the parse tree
	 */
	void exitDefList(CmmParser.DefListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#def}.
	 * @param ctx the parse tree
	 */
	void enterDef(CmmParser.DefContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#def}.
	 * @param ctx the parse tree
	 */
	void exitDef(CmmParser.DefContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#decList}.
	 * @param ctx the parse tree
	 */
	void enterDecList(CmmParser.DecListContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#decList}.
	 * @param ctx the parse tree
	 */
	void exitDecList(CmmParser.DecListContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#dec}.
	 * @param ctx the parse tree
	 */
	void enterDec(CmmParser.DecContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#dec}.
	 * @param ctx the parse tree
	 */
	void exitDec(CmmParser.DecContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expDot}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpDot(CmmParser.ExpDotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expDot}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpDot(CmmParser.ExpDotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expOr}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpOr(CmmParser.ExpOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expOr}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpOr(CmmParser.ExpOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expRelop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpRelop(CmmParser.ExpRelopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expRelop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpRelop(CmmParser.ExpRelopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expPlusMinus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpPlusMinus(CmmParser.ExpPlusMinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expPlusMinus}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpPlusMinus(CmmParser.ExpPlusMinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expAssignop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpAssignop(CmmParser.ExpAssignopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expAssignop}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpAssignop(CmmParser.ExpAssignopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expParenthesis}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpParenthesis(CmmParser.ExpParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expParenthesis}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpParenthesis(CmmParser.ExpParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expFloat}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpFloat(CmmParser.ExpFloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expFloat}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpFloat(CmmParser.ExpFloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expAnd}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpAnd(CmmParser.ExpAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expAnd}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpAnd(CmmParser.ExpAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expFuncArgs}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpFuncArgs(CmmParser.ExpFuncArgsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expFuncArgs}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpFuncArgs(CmmParser.ExpFuncArgsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expStarDiv}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpStarDiv(CmmParser.ExpStarDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expStarDiv}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpStarDiv(CmmParser.ExpStarDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expInt}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpInt(CmmParser.ExpIntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expInt}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpInt(CmmParser.ExpIntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expMinusNot}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpMinusNot(CmmParser.ExpMinusNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expMinusNot}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpMinusNot(CmmParser.ExpMinusNotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expId}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpId(CmmParser.ExpIdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expId}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpId(CmmParser.ExpIdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code expBrackets}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExpBrackets(CmmParser.ExpBracketsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code expBrackets}
	 * labeled alternative in {@link CmmParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExpBrackets(CmmParser.ExpBracketsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CmmParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(CmmParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CmmParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(CmmParser.ArgsContext ctx);
}