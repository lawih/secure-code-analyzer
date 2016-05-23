
import Algorithm.Response;
import Controller.*;
import java.io.IOException;
import java.io.PrintWriter;
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

        SuggestionController controller = new SuggestionController(source);
        Response nextSuggestion = controller.getNextSuggestion();

        // There are not suggestions
        if (nextSuggestion.getLine() == -1) {

            Type emptySuggestion = new Type("", "There are not suggestions for your code.", "https://www.securecoding.cert.org/confluence/display/java/2+Rules");
            request.setAttribute("suggestionType", emptySuggestion.getDescription());
            request.setAttribute("suggestionTypeURL", "You can learn about secure code <a target=\"_blank\" href=\""
                    + emptySuggestion.getUrl() + "\">here</a>.");

        } else {

            Type suggestionType = controller.getTypes().get(nextSuggestion.getType());

            request.setAttribute("suggestionType", suggestionType.getDescription());
            request.setAttribute("suggestionTypeURL", "You can learn more <a target=\"_blank\" href=\""
                    + suggestionType.getUrl() + "\">here</a>.");
            request.setAttribute("suggestionLine", "The vulnerable line code is "
                    + nextSuggestion.getLine() + ".");

            request.setAttribute("wrongcode", nextSuggestion.getWrongCode());
            request.setAttribute("recomendation", nextSuggestion.getRecomendation());
        }

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
