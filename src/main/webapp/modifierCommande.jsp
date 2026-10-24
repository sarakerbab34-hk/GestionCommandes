
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="ma.fstt.entities.Commande" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier une commande</title>
</head>
<body>

    <h1>Modifier une commande</h1>

    <%
        Commande commande =
            (Commande) request.getAttribute("commande");
    %>

    <% if (commande != null) { %>

    <form action="${pageContext.request.contextPath}/modifierCommande"
          method="post">

        <input type="hidden" name="id"
               value="<%= commande.getIdCommande() %>">

        <p>
            <strong>Numéro de commande :</strong>
            <%= commande.getIdCommande() %>
        </p>

        <label>ID du client :</label>
        <input type="number" name="idClient" min="1"
               value="<%= commande.getIdClient() %>" required>
        <br><br>

        <label>Statut :</label>
        <select name="statut" required>
            <option value="En attente"
                <%= "En attente".equals(commande.getStatut()) ? "selected" : "" %>>
                En attente
            </option>

            <option value="En cours"
                <%= "En cours".equals(commande.getStatut()) ? "selected" : "" %>>
                En cours
            </option>

            <option value="Livrée"
                <%= "Livrée".equals(commande.getStatut()) ? "selected" : "" %>>
                Livrée
            </option>

            <option value="Annulée"
                <%= "Annulée".equals(commande.getStatut()) ? "selected" : "" %>>
                Annulée
            </option>
        </select>
        <br><br>

        <button type="submit">Enregistrer les modifications</button>

    </form>

    <% } else { %>

        <p>Commande introuvable.</p>

    <% } %>

    <br>

    <a href="${pageContext.request.contextPath}/commandes">
        Retour à la liste des commandes
    </a>

</body>
</html>
