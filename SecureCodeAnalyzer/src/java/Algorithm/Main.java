package Algorithm;

import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;


public class Main {
public static void main(String[] args) throws IOException {
	// Get our lexer
	Java8Lexer lexer;
	lexer = new Java8Lexer(new ANTLRFileStream("input.txt"));
	 // Get a list of matched tokens
    CommonTokenStream tokens = new CommonTokenStream(lexer);
 
    // Pass the tokens to the parser
    Java8Parser parser = new Java8Parser(tokens);
 
    // Specify our entry point
    Java8Parser.CompilationUnitContext  compilationUnit= parser.compilationUnit();
    // Walk it and attach our listener
    ParseTreeWalker walker = new ParseTreeWalker();
    ListenerJava listener = new ListenerJava(parser);
    walker.walk(listener, compilationUnit);
    List<Response> list = listener.responses;
    for( final Response response : list )
    {
    	System.out.println(response.toString());
    }
}
}
