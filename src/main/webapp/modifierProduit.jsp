
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="ma.fstt.entities.Produit" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Modifier un produit</title>
</head>
<body>

    <h1>Modifier un produit</h1>

    <%
        Produit produit = (Produit) request.getAttribute("produit");
    %>

    <% if (produit != null) { %>

    <form action="${pageContext.request.contextPath}/modifierProduit"
          method="post">

        <input type="hidden" name="id"
               value="<%= produit.getIdProduit() %>">

        <label>Nom :</label>
        <input type="text" name="nom"
               value="<%= produit.getNom() %>" required>
        <br><br>

        <label>Description :</label>
        <textarea name="description" required><%= produit.getDescription() %></textarea>
        <br><br>

        <label>Prix :</label>
        <input type="number" name="prix" step="0.01" min="0"
               value="<%= produit.getPrix() %>" required>
        <br><br>

        <label>Stock :</label>
        <input type="number" name="stock" min="0"
               value="<%= produit.getStock() %>" required>
        <br><br>

        <button type="submit">Enregistrer les modifications</button>
    </form>

    <% } else { %>
        <p>Produit introuvable.</p>
    <% } %>

    <br>
    <a href="${pageContext.request.contextPath}/produits">
        Retour à la liste des produits
    </a>

</body>
</html>
