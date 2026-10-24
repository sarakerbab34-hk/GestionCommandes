
package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.CommandeDAO;

@WebServlet("/supprimerCommande")
public class SupprimerCommandeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CommandeDAO commandeDAO;

    @Override
    public void init() {
        commandeDAO = new CommandeDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            commandeDAO.supprimer(id);

            response.sendRedirect(
                request.getContextPath() + "/commandes"
            );

        } catch (NumberFormatException e) {
            response.sendError(400, "Identifiant de commande invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la suppression de la commande", e);
        }
    }
}
