
import Algorithm.Response;
import Controller.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/SourceCodeServlet"})
@MultipartConfig
public class SourceCodeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SourceCodeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SourceCodeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Code from the Text Area
        String source = request.getParameter("sourcecode");

        String lines[] = source.split("\n");
        System.out.println("A call: \n");
        /*
        System.out.println("-----------------------");
        for (String s : lines) {
            System.out.println(s);
        }
        System.out.println("-----------------------");
         */

        SuggestionController controller = new SuggestionController(source);
        Response nextSuggestion = controller.getNextSuggestion();
        List<Response> suggestions = controller.getSuggestions();
        Type suggestionType;

        int currentSuggestion;

        if (request.getParameter("currentSuggestion").equals("")) {
            currentSuggestion = 0;
        } else {
            currentSuggestion = Integer.parseInt(request.getParameter("currentSuggestion"));
        }

        if (request.getParameter("analize") != null) {

            // There are not suggestions
            if (suggestions.isEmpty()) {
                Type emptySuggestion = new Type("", "There are not suggestions for your code.", "https://www.securecoding.cert.org/confluence/display/java/2+Rules");
                request.setAttribute("suggestionType", emptySuggestion.getDescription());
                request.setAttribute("suggestionTypeURL", "You can learn about secure code <a target=\"_blank\" href=\""
                        + emptySuggestion.getUrl() + "\">here</a>.");
                request.setAttribute("currentSuggestion", currentSuggestion + 1);
            } else {

                suggestionType = controller.getTypes().get(nextSuggestion.getType());
                request.setAttribute("currentSuggestion", 0);

                request.setAttribute("suggestionType", suggestionType.getDescription());
                request.setAttribute("suggestionTypeURL", "You can learn more <a target=\"_blank\" href=\""
                        + suggestionType.getUrl() + "\">here</a>.");
                request.setAttribute("suggestionLine", "The vulnerable line code is "
                        + nextSuggestion.getLine() + ".");
            }
        } // There are no more suggestions
        else if (currentSuggestion + 1 >= suggestions.size()) {
            Type emptySuggestion = new Type("", "There are no more suggestions for your code.", "https://www.securecoding.cert.org/confluence/display/java/2+Rules");
            request.setAttribute("suggestionType", emptySuggestion.getDescription());
            request.setAttribute("suggestionTypeURL", "You can learn more about secure code <a target=\"_blank\" href=\""
                    + emptySuggestion.getUrl() + "\">here</a>.");
            request.setAttribute("currentSuggestion", currentSuggestion + 1);
        } else {

            // If user ask for next suggestion
            if (request.getParameter("next") != null) {

                nextSuggestion = suggestions.get(currentSuggestion + 1);
                suggestionType = controller.getTypes().get(nextSuggestion.getType());

                request.setAttribute("currentSuggestion", currentSuggestion + 1);
                System.out.println("-----------------------");
                System.out.println("Next: " + nextSuggestion.getWrongCode());
                System.out.println("Current sug: " + (Integer) request.getAttribute("currentSuggestion"));
                System.out.println("-----------------------");

            } else {
                /*
            String wrongCode = nextSuggestion.getWrongCode();
            String recomendedCode = nextSuggestion.getRecomendation();
            String resultingLine;
            String resultingCode = "";
            String preffix, suffix;
            String line = lines[nextSuggestion.getLine()-1];
            
            line = line.replaceAll("\\s+","");            
            int index = line.indexOf(wrongCode);
            
            preffix = line.substring(0, index);
            suffix = line.substring( index + wrongCode.length() );
            resultingLine = preffix + recomendedCode + suffix;
            
            lines[nextSuggestion.getLine()-1] = resultingLine;
            for( String s : lines ){
                resultingCode += s + "\n";
            }
                
            System.out.println("-----------------------");
            System.out.println(preffix + recomendedCode + suffix);
            System.out.println("-----------------------");
            
                 */

                suggestionType = controller.getTypes().get(nextSuggestion.getType());

                request.setAttribute("currentSuggestion", 0);
            }

            request.setAttribute("suggestionType", suggestionType.getDescription());
            request.setAttribute("suggestionTypeURL", "You can learn more <a target=\"_blank\" href=\""
                    + suggestionType.getUrl() + "\">here</a>.");
            request.setAttribute("suggestionLine", "The vulnerable line code is "
                    + nextSuggestion.getLine() + ".");

            request.setAttribute("wrongcode", nextSuggestion.getWrongCode());
            request.setAttribute("recomendation", nextSuggestion.getRecomendation());

        }

        /*
            if( suggestionType.getId().equals("DCL02-J") ){
                int index = line.indexOf("for");
                String out = line.substring(index);
                request.setAttribute("wrongcode", out);
            }

            System.out.println("-----------------------");
            System.out.println(lines[nextSuggestion.getLine()-1]);
            System.out.println("-----------------------");
            
         */
        request.setAttribute("lastInput", source);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
