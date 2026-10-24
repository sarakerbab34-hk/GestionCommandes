```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des clients</title>
</head>
<body>

    <h1>Gestion des clients</h1>

    <!-- Formulaire d'ajout -->
    <h2>Ajouter un client</h2>

    <form action="${pageContext.request.contextPath}/clients" method="post">
        <label>Nom :</label>
        <input type="text" name="nom" required>
        <br><br>

        <label>Email :</label>
        <input type="email" name="email" required>
        <br><br>

        <label>Téléphone :</label>
        <input type="text" name="telephone" required>
        <br><br>

        <button type="submit">Ajouter</button>
    </form>

    <hr>

    <!-- Liste des clients -->
    <h2>Liste des clients</h2>

    <table border="1" cellpadding="8">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Email</th>
            <th>Téléphone</th>
            <th>Actions</th>
        </tr>

        <%
            java.util.List<ma.fstt.entities.Client> clients =
                (java.util.List<ma.fstt.entities.Client>)
                request.getAttribute("clients");

            if (clients != null) {
                for (ma.fstt.entities.Client client : clients) {
        %>
        <tr>
            <td><%= client.getIdClient() %></td>
            <td><%= client.getNom() %></td>
            <td><%= client.getEmail() %></td>
            <td><%= client.getTelephone() %></td>

            <td>
                <a href="${pageContext.request.contextPath}/modifierClient?id=<%= client.getIdClient() %>">
                    Modifier
                </a>

                |

                <a href="${pageContext.request.contextPath}/supprimerClient?id=<%= client.getIdClient() %>"
                   onclick="return confirm('Voulez-vous vraiment supprimer ce client ?');">
                    Supprimer
                </a>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>

    <br>

    <a href="${pageContext.request.contextPath}/index.jsp">
        Retour à l'accueil
    </a>

</body>
</html>
```