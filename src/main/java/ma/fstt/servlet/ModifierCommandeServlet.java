
package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.CommandeDAO;
import ma.fstt.entities.Commande;

@WebServlet("/modifierCommande")
public class ModifierCommandeServlet extends HttpServlet {

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

            Commande commande = commandeDAO.trouverParId(id);

            if (commande == null) {
                response.sendError(404, "Commande introuvable.");
                return;
            }

            request.setAttribute("commande", commande);

            request.getRequestDispatcher("/modifierCommande.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(400, "Identifiant de commande invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la recherche de la commande", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            int idClient = Integer.parseInt(request.getParameter("idClient"));
            String statut = request.getParameter("statut");

            Commande commande = commandeDAO.trouverParId(id);

            if (commande == null) {
                response.sendError(404, "Commande introuvable.");
                return;
            }

            commande.setStatut(statut);
            commande.setIdClient(idClient);

            commandeDAO.modifier(commande);

            response.sendRedirect(
                request.getContextPath() + "/commandes"
            );

        } catch (NumberFormatException e) {
            response.sendError(400, "Identifiant invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la modification de la commande", e);
        }
    }
}
