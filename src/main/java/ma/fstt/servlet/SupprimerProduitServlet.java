package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ProduitDAO;

@WebServlet("/supprimerProduit")
public class SupprimerProduitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProduitDAO produitDAO;

    @Override
    public void init() {
        produitDAO = new ProduitDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            produitDAO.supprimer(id);

            response.sendRedirect(
                request.getContextPath() + "/produits");

        } catch (NumberFormatException e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Identifiant du produit invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la suppression du produit", e);
        }
    }
}
