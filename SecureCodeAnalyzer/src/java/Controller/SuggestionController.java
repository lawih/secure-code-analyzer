package Controller;

import Algorithm.Java8Lexer;
import Algorithm.Java8Parser;
import Algorithm.ListenerJava;
import Algorithm.Response;
import java.util.HashMap;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class SuggestionController {

    List<Response> suggestions;
    HashMap<String, Type> types;
    String source;
    int currentIndex;

    public SuggestionController(String source) {
        this.source = source;
        types = new HashMap<>();
        init();
        fillTypes();
    }

    private void init() {
        // Get our lexer
        Java8Lexer lexer;
        lexer = new Java8Lexer(new ANTLRInputStream(source));

        // Get a list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass the tokens to the parser
        Java8Parser parser = new Java8Parser(tokens);

        // Specify our entry point
        Java8Parser.CompilationUnitContext compilationUnit = parser.compilationUnit();

        // Walk it and attach our listener
        ParseTreeWalker walker = new ParseTreeWalker();
        ListenerJava listener = new ListenerJava(parser);
        walker.walk(listener, compilationUnit);

        // List of suggestions
        suggestions = listener.getResponses();
        currentIndex = suggestions.size() > 0 ? currentIndex = 0 : -1;
    }

    public Response getNextSuggestion() {
        if (currentIndex == -1) {
            return new Response("", "", "", "", -1);
        }
        return suggestions.get(currentIndex++);
    }
    
    public List<Response> getSuggestions() {
        return suggestions;
    }

    private void fillTypes() {
        getTypes().put("DCL02-J", new Type("DCL02-J",
                "Do not modify the collection's elements during an enhanced for statement.",
                "https://www.securecoding.cert.org/confluence/display/java/DCL02-J.+Do+not+modify+the+collection%27s+elements+during+an+enhanced+for+statement"));

        getTypes().put("EXP02-J", new Type("EXP02-J",
                "Do not use the Object.equals() method to compare two arrays.",
                "https://www.securecoding.cert.org/confluence/display/java/EXP02-J.+Do+not+use+the+Object.equals%28%29+method+to+compare+two+arrays"));

        getTypes().put("EXP04-J", new Type("EXP04-J",
                "Do not pass arguments to certain Java Collections Framework methods that are a different type than the collection parameter type.",
                "https://www.securecoding.cert.org/confluence/display/java/EXP04-J.+Do+not+pass+arguments+to+certain+Java+Collections+Framework+methods+that+are+a+different+type+than+the+collection+parameter+type"));

        getTypes().put("EXP06-J", new Type("EXP06-J",
                "Expressions used in assertions must not produce side effects.",
                "https://www.securecoding.cert.org/confluence/display/java/EXP06-J.+Expressions+used+in+assertions+must+not+produce+side+effects"));

        getTypes().put("NUM09-J", new Type("NUM09-J",
                "Do not use floating-point variables as loop counters.",
                "https://www.securecoding.cert.org/confluence/display/java/NUM09-J.+Do+not+use+floating-point+variables+as+loop+counters"));

        getTypes().put("NUM10-J", new Type("NUM10-J",
                "Do not construct BigDecimal objects from floating-point literals.",
                "https://www.securecoding.cert.org/confluence/display/java/NUM10-J.+Do+not+construct+BigDecimal+objects+from+floating-point+literals"));
    }

    public HashMap<String, Type> getTypes() {
        return types;
    }
}
