
package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ClientDAO;

@WebServlet("/supprimerClient")
public class SupprimerClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClientDAO clientDAO;

    @Override
    public void init() {
        clientDAO = new ClientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");

        try {
            int id = Integer.parseInt(idParam);

            clientDAO.supprimer(id);

            response.sendRedirect(
                request.getContextPath() + "/clients");

        } catch (NumberFormatException e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Identifiant du client invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la suppression du client", e);
        }
    }
}
