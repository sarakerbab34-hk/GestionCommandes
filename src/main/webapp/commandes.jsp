
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="ma.fstt.entities.Commande" %>
<%@ page import="ma.fstt.entities.Client" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des commandes</title>
</head>
<body>

    <h1>Gestion des commandes</h1>

    <h2>Ajouter une commande</h2>

    <%
        List<Client> clients =
            (List<Client>) request.getAttribute("clients");
    %>

    <% if (clients != null && !clients.isEmpty()) { %>

    <form action="${pageContext.request.contextPath}/commandes"
          method="post">

        <label>Choisir un client :</label>

        <select name="idClient" required>
            <option value="">-- Sélectionner un client --</option>

            <%
                for (Client client : clients) {
            %>
                <option value="<%= client.getIdClient() %>">
                    <%= client.getNom() %>
                    (ID : <%= client.getIdClient() %>)
                </option>
            <%
                }
            %>
        </select>

        <br><br>

        <label>Statut :</label>

        <select name="statut" required>
            <option value="En attente">En attente</option>
            <option value="En cours">En cours</option>
            <option value="Livrée">Livrée</option>
            <option value="Annulée">Annulée</option>
        </select>

        <br><br>

        <button type="submit">Ajouter la commande</button>

    </form>

    <% } else { %>

        <p>
            Aucun client disponible.
            <a href="${pageContext.request.contextPath}/clients">
                Ajouter un client
            </a>
            avant de créer une commande.
        </p>

    <% } %>

    <hr>

    <h2>Liste des commandes</h2>

    <table border="1" cellpadding="8">
        <thead>
            <tr>
                <th>ID commande</th>
                <th>Date</th>
                <th>Statut</th>
                <th>ID client</th>
                <th>Actions</th>
            </tr>
        </thead>

        <tbody>
        <%
            List<Commande> commandes =
                (List<Commande>) request.getAttribute("commandes");

            if (commandes != null && !commandes.isEmpty()) {
                for (Commande commande : commandes) {
        %>
            <tr>
                <td><%= commande.getIdCommande() %></td>

                <td>
                    <%
                        if (commande.getDateCommande() != null) {
                            out.print(commande.getDateCommande().format(
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                            ));
                        } else {
                            out.print("Non définie");
                        }
                    %>
                </td>

                <td><%= commande.getStatut() %></td>

                <td><%= commande.getIdClient() %></td>

                <td>
                    <a href="${pageContext.request.contextPath}/modifierCommande?id=<%= commande.getIdCommande() %>">
                        Modifier
                    </a>

                    |

                    <a href="${pageContext.request.contextPath}/supprimerCommande?id=<%= commande.getIdCommande() %>"
                       onclick="return confirm('Voulez-vous vraiment supprimer cette commande ?');">
                        Supprimer
                    </a>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="5">Aucune commande disponible.</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <br>

    <a href="${pageContext.request.contextPath}/index.jsp">
        Retour à l'accueil
    </a>

</body>
</html>
