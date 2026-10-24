```jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ma.fstt.entities.Client" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier un client</title>
</head>
<body>

    <h1>Modifier un client</h1>

    <%
        Client client = (Client) request.getAttribute("client");
    %>

    <% if (client != null) { %>

        <form action="${pageContext.request.contextPath}/modifierClient"
              method="post">

            <input type="hidden" name="id"
                   value="<%= client.getIdClient() %>">

            <label>Nom :</label>
            <input type="text" name="nom"
                   value="<%= client.getNom() %>" required>
            <br><br>

            <label>Email :</label>
            <input type="email" name="email"
                   value="<%= client.getEmail() %>" required>
            <br><br>

            <label>Téléphone :</label>
            <input type="text" name="telephone"
                   value="<%= client.getTelephone() %>" required>
            <br><br>

            <button type="submit">Enregistrer les modifications</button>
        </form>

    <% } else { %>

        <p>Client introuvable.</p>

    <% } %>

    <br>

    <a href="${pageContext.request.contextPath}/clients">
        Retour à la liste des clients
    </a>

</body>
</html>
```