
package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.CommandeDAO;
import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Commande;
import ma.fstt.entities.Client;

@WebServlet("/commandes")
public class CommandeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private CommandeDAO commandeDAO;
    private ClientDAO clientDAO;

    @Override
    public void init() {
        commandeDAO = new CommandeDAO();
        clientDAO = new ClientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Commande> commandes = commandeDAO.lister();
            List<Client> clients = clientDAO.lister();

            request.setAttribute("commandes", commandes);
            request.setAttribute("clients", clients);

            request.getRequestDispatcher("/commandes.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors du chargement des commandes et des clients", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            int idClient = Integer.parseInt(
                request.getParameter("idClient")
            );

            String statut = request.getParameter("statut");

            // Vérifier que le client existe réellement
            Client client = clientDAO.trouverParId(idClient);

            if (client == null) {
                response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Le client sélectionné n'existe pas."
                );
                return;
            }

            Commande commande = new Commande(
                0,
                null,
                statut,
                idClient
            );

            commandeDAO.ajouter(commande);

            response.sendRedirect(
                request.getContextPath() + "/commandes"
            );

        } catch (NumberFormatException e) {
            response.sendError(
                HttpServletResponse.SC_BAD_REQUEST,
                "Veuillez sélectionner un client valide."
            );

        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de l'ajout de la commande", e);
        }
    }
}
