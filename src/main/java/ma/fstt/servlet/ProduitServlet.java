
package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ProduitDAO;
import ma.fstt.entities.Produit;

@WebServlet("/produits")
public class ProduitServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProduitDAO produitDAO;

    @Override
    public void init() {
        produitDAO = new ProduitDAO();
    }

    // Afficher la liste des produits
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Produit> produits = produitDAO.lister();
            request.setAttribute("produits", produits);

            request.getRequestDispatcher("/produits.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors du chargement des produits", e);
        }
    }

    // Ajouter un produit
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            String nom = request.getParameter("nom");
            String description = request.getParameter("description");
            double prix = Double.parseDouble(request.getParameter("prix"));
            int stock = Integer.parseInt(request.getParameter("stock"));

            Produit produit = new Produit(0, nom, description, prix, stock);
            produitDAO.ajouter(produit);

            response.sendRedirect(
                request.getContextPath() + "/produits");

        } catch (NumberFormatException e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Le prix ou le stock est invalide.");
        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de l'ajout du produit", e);
        }
    }
}
