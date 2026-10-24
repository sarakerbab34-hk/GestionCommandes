
package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Client;

@WebServlet("/modifierClient")
public class ModifierClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClientDAO clientDAO;

    @Override
    public void init() {
        clientDAO = new ClientDAO();
    }

    // Afficher le formulaire de modification
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Client client = clientDAO.trouverParId(id);

            if (client == null) {
                response.sendError(
                    HttpServletResponse.SC_NOT_FOUND,
                    "Client introuvable.");
                return;
            }

            request.setAttribute("client", client);
            request.getRequestDispatcher("/modifierClient.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Identifiant invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la recherche du client", e);
        }
    }

    // Enregistrer les modifications
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String email = request.getParameter("email");
            String telephone = request.getParameter("telephone");

            Client client = new Client(id, nom, email, telephone);
            clientDAO.modifier(client);

            response.sendRedirect(
                request.getContextPath() + "/clients");

        } catch (NumberFormatException e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Identifiant invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la modification du client", e);
        }
    }
}