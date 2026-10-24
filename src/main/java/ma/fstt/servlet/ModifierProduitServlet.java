
package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Produit;

@WebServlet("/modifierProduit")
public class ModifierProduitServlet extends HttpServlet {

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

            Produit produit = produitDAO.trouverParId(id);

            if (produit == null) {
                response.sendError(404, "Produit introuvable.");
                return;
            }

            request.setAttribute("produit", produit);
            request.getRequestDispatcher("/modifierProduit.jsp")
                   .forward(request, response);

        } catch (NumberFormatException e) {
            response.sendError(400, "Identifiant invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la recherche du produit", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            double prix = Double.parseDouble(request.getParameter("prix"));
            int stock = Integer.parseInt(request.getParameter("stock"));

            Produit produit = new Produit(
                id, nom, description, prix, stock
            );

            produitDAO.modifier(produit);

            response.sendRedirect(
                request.getContextPath() + "/produits"
            );

        } catch (NumberFormatException e) {
            response.sendError(400, "Le prix, le stock ou l'identifiant est invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de la modification du produit", e);
        }
    }
}
