package Algorithm;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class ListenerJava extends Java8BaseListener{
	
	Java8Parser parser;
	List<Response> responses;
	Set<String> arraysName;
	Set<Pair> collectionsName;
        Set<String> FilesName;
	SymbolTable symbolTable;
	boolean inAssert;
	boolean badAssert;
	int inForEach;
	String idForEach[];
	int countBooleanForAssert;
	String methodInCollections[] = new String[]{"remove", "get", "contains"};
	
	public ListenerJava(Java8Parser parser)
	{
		this.parser = parser;
		responses = new ArrayList<Response>();
		arraysName = new TreeSet<String>();
		collectionsName = new HashSet<Pair>();
                FilesName = new HashSet<String>();
		inAssert = false;
		badAssert = false;
		inForEach = 0;
		idForEach = new String[20];
		idForEach[inForEach] = "nothing";
		countBooleanForAssert = 0;
		symbolTable = new SymbolTable();
	}
	public List<Response> getResponses() {
		return responses;
	}
	public void setResponses(List<Response> responses) {
		this.responses = responses;
	}
	@Override 
	public void enterAssertStatement(Java8Parser.AssertStatementContext ctx) 
	{ 
		System.out.println("enterAssertStatement");
		inAssert = true;
		
	}
	@Override
	public void exitAssertStatement(Java8Parser.AssertStatementContext ctx) {
		// TODO Auto-generated method stub
		inAssert = false;
		if ( badAssert )
		{
			System.out.println(ctx.getText());
			Response auxResult = new Response("assert "+ctx.getText().substring(6), UtilsF.getGoodAssert(ctx.getText(), "assertBool"+countBooleanForAssert++), "", "EXP06-J", ctx.start.getLine());
			responses.add(auxResult);
			badAssert = false;
		}
		System.out.println("exitAssertStatement");
	}
	@Override
	public void enterVariableDeclarator(Java8Parser.VariableDeclaratorContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("enterVariableDeclarator");
		System.out.println(ctx.getText());
		String cad = ctx.getText();
		if ( UtilsF.isArray(ctx.getText()))
		{
			arraysName.add(UtilsF.nameOfArray(ctx.getText()));
		}
		if ( UtilsF.isCollection(cad))
		{
			collectionsName.add(new Pair(UtilsF.getNameCollection(cad), UtilsF.getTypeCollection(cad)));
		}
                if ( UtilsF.isFile(cad))
                {
                    
                    System.out.println("addFilestart--------------");        
                    FilesName.add(UtilsF.nameOfArray(ctx.getText()));
                    System.out.println("addFile--------------");
                            
                }
		super.enterVariableDeclarator(ctx);
	}
	@Override
	public void enterMethodInvocation(Java8Parser.MethodInvocationContext ctx) 
	{
		System.out.println("enterMethodInvocation");
		System.out.println(ctx.getText());
		String aux;
		String cad  = ctx.getText();
		for ( final String cad1 : arraysName )
		{
			for( final String cad2 : arraysName )
			{
				if ( !cad1.equals(cad2))
				{
					aux = cad1+".equals("+cad2+")";
					if (ctx.getText().contains(aux))
					{
						Response auxResponse = new Response(aux, "java.util.Arrays.equals("+cad1+","+cad2+")", "", "EXP02-J", ctx.start.getLine());
						responses.add(auxResponse);
					}
				}
			}
		}
  
		for( final Pair var : collectionsName )
		{
			String name = var.first;
			for( final String methodName : methodInCollections)
			{
				String auxS = name+"."+methodName;
				if ( cad.startsWith(auxS))
				{
					String inter = cad.substring(auxS.length()+1);
					inter = inter.substring(0, inter.length()-1);
					String interType = symbolTable.search(inter).type;
					if ( !UtilsF.isEqualType(interType, var.second))
					{
						inter = "(" + var.second + ")("+ inter +")";
						String recomendation = auxS+"("+inter+")";
						Response auxResponse = new Response(cad, recomendation, "", "EXP04-J", ctx.start.getLine());
						responses.add(auxResponse);
					}
				}
			}
		}
		super.enterMethodInvocation(ctx);
	}
	@Override
	public void enterMethodInvocation_lf_primary(Java8Parser.MethodInvocation_lf_primaryContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("enterMethodInvocation_lf_primary");
		System.out.println(ctx.getText());
		super.enterMethodInvocation_lf_primary(ctx);
	}
	@Override
	public void enterMethodInvocation_lfno_primary(Java8Parser.MethodInvocation_lfno_primaryContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("enterMethodInvocation_lfno_primary");
		System.out.println(ctx.getText());
                String cad = ctx.getText();
		if ( inAssert )
		{
			badAssert = true;
		}
                              for( final String var : FilesName )
                {
                    System.out.println("OOOOOOO   var:"+var);
                            
                    String auxF = var+".delete()";
                    if ( cad.contains(auxF))
                    {
                        Response auxResponse = new Response(auxF, "if (!file.delete()) {\n" +
"  System.out.println(\"Deletion failed\");\n" +
"}", "", "FIO02-J", ctx.start.getLine());
						responses.add(auxResponse);
                    }
                }
		super.enterMethodInvocation_lfno_primary(ctx);
	}
	@Override
	public void enterEnhancedForStatement(Java8Parser.EnhancedForStatementContext ctx) {
		// TODO Auto-generated method stub
		inForEach++;
		idForEach[inForEach] = "nothing";
		
		super.enterEnhancedForStatement(ctx);
	}
	@Override
	public void exitEnhancedForStatement(Java8Parser.EnhancedForStatementContext ctx) {
		// TODO Auto-generated method stub
		String cad = ctx.getText();
		String resp[];
		if ( !UtilsF.doItHaveFinal(cad))
		{
			resp = UtilsF.getGoodAndBad(cad);
			String wrongCode = resp[1];
			String goodCode = resp[0];
			wrongCode = wrongCode.replace(idForEach[inForEach]+":", " "+idForEach[inForEach]+" : ");
			goodCode = goodCode.replace(idForEach[inForEach]+":", " "+idForEach[inForEach]+" : ");
			Response auxResponse = new Response( wrongCode, goodCode, "", "DCL02-J", ctx.start.getLine());
			responses.add(auxResponse);
		}
		idForEach[inForEach] = "nothing";
		inForEach--;
		
		super.exitEnhancedForStatement(ctx);
	}
	@Override
	public void enterVariableDeclaratorId(Java8Parser.VariableDeclaratorIdContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("enterVariableDeclaratorId");
		if ( idForEach[inForEach].equals("nothing") && inForEach > 0)
		{
			System.out.println("-----------------------------------------start");
			System.out.println(ctx.getStart().getText());
			System.out.println("-----------------------------------------finish");
			idForEach[inForEach] = ctx.getStart().getText();
		}
		super.enterVariableDeclaratorId(ctx);
	}
	@Override
	public void enterClassInstanceCreationExpression(Java8Parser.ClassInstanceCreationExpressionContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("enterClassInstanceCreationExpression");
		super.enterClassInstanceCreationExpression(ctx);
	}
	@Override
	public void enterClassInstanceCreationExpression_lf_primary(Java8Parser.ClassInstanceCreationExpression_lf_primaryContext ctx) {
		// TODO Auto-generated method stub
		System.out.println("enterClassInstanceCreationExpression_lf_primary");
		super.enterClassInstanceCreationExpression_lf_primary(ctx);
	}
	@Override
	public void enterClassInstanceCreationExpression_lfno_primary(
			Java8Parser.ClassInstanceCreationExpression_lfno_primaryContext ctx) {
		System.out.println("enterClassInstanceCreationExpression_lfno_primary");
		String cad = ctx.getText();
		if ( UtilsF.isBigDecimal(cad))
		{
			if ( !UtilsF.isAGoodNewBigDecimal(cad))
			{
				Response auxResponse = new Response("new "+cad.substring(3), UtilsF.getGoodBigDecimal(cad), "", "NUM10-J", ctx.start.getLine());
				responses.add(auxResponse);
			}
		}
		
		// TODO Auto-generated method stub
		super.enterClassInstanceCreationExpression_lfno_primary(ctx);
	}
	@Override
	public void enterBasicForStatement(Java8Parser.BasicForStatementContext ctx) {
		
		// TODO Auto-generated method stub
		System.out.println("enterBasicForStatement");
		String cad = ctx.getText();
		if ( UtilsF.isForWithFloat(cad))
		{
			//System.out.println(cad);
			Response auxResponse = new Response(UtilsF.headerFor(cad), "You have a for with float", "","NUM09-J", ctx.start.getLine());
			responses.add(auxResponse);
		}
		else if ( UtilsF.isFowWithDouble(cad))
		{
			Response auxResponse = new Response(UtilsF.headerFor(cad), "You have a for with double", "", "NUM09-J", ctx.start.getLine());
			responses.add(auxResponse);
		}
		super.enterBasicForStatement(ctx);
	}
	@Override
	public void enterLocalVariableDeclaration(Java8Parser.LocalVariableDeclarationContext ctx) {
		// TODO Auto-generated method stub
		
		System.out.println("enterLocalVariableDeclaration");
		String cad = ctx.getText();
		String type = ctx.start.getText();
		String name = cad.split("=")[0].substring(type.length());
		if ( name.startsWith("<"))
		{
			boolean flag = true;
			while(flag)
			{
				name = name.substring(1);
				if ( name.startsWith(">"))
				{
					name = name.substring(1);
					flag = false;
				}
			}
		}
		symbolTable.add( new Variable(name, type));
		super.enterLocalVariableDeclaration(ctx);
	}
}
