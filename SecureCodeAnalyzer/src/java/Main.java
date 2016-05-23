
import Algorithm.*;
import Controller.SuggestionController;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String source = "import java.util.*;\n"
                + "\n"
                + "public class Main \n"
                + "{\n"
                + "	//private ArrayList<String> names;\n"
                + "	void process(int index) \n"
                + "	{\n"
                + "		for (double x = 0.1f; x <= 1.0f; x += 0.1f) \n"
                + "		{\n"
                + "  			System.out.println(x);\n"
                + "		}\n"
                + "	}\n"
                + "}";

        SuggestionController controller = new SuggestionController(source);
        Response nextSuggestion = controller.getNextSuggestion();
        String suggestionType = nextSuggestion.getType();
        
        System.out.println(controller.getTypes().get(suggestionType));
        
        System.out.println(nextSuggestion.getRecomendation());
    }
}
