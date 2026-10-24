
package ma.fstt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/lignes")
public class LigneCommandeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='fr'>");
        out.println("<head><meta charset='UTF-8'>");
        out.println("<title>Lignes de commande</title></head>");
        out.println("<body>");
        out.println("<h1>Gestion des lignes de commande</h1>");
        out.println("<p>La page des lignes de commande fonctionne !</p>");
        out.println("<a href='" + request.getContextPath()
                + "/'>Retour à l'accueil</a>");
        out.println("</body></html>");
    }
}
