package ma.fstt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.fstt.dao.ClientDAO;
import ma.fstt.entities.Client;

@WebServlet("/clients")
public class ClientServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ClientDAO clientDAO;

    @Override
    public void init() {
        clientDAO = new ClientDAO();
    }

    // Afficher la liste des clients
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Client> clients = clientDAO.lister();
            request.setAttribute("clients", clients);

            request.getRequestDispatcher("/clients.jsp")
                   .forward(request, response);

        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors du chargement des clients", e);
        }
    }

    // Ajouter un client
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");

        Client client = new Client(0, nom, email, telephone);

        try {
            clientDAO.ajouter(client);

            // Revenir à la liste après l'ajout
            response.sendRedirect(
                request.getContextPath() + "/clients");

        } catch (SQLException e) {
            throw new ServletException(
                "Erreur lors de l'ajout du client", e);
        }
    }
}