
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="ma.fstt.entities.Produit" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des produits</title>
</head>
<body>

    <h1>Gestion des produits</h1>

    <h2>Ajouter un produit</h2>

    <form action="${pageContext.request.contextPath}/produits"
          method="post">

        <label>Nom :</label>
        <input type="text" name="nom" required>
        <br><br>

        <label>Description :</label>
        <textarea name="description" required></textarea>
        <br><br>

        <label>Prix :</label>
        <input type="number" name="prix"
               step="0.01" min="0" required>
        <br><br>

        <label>Stock :</label>
        <input type="number" name="stock"
               min="0" required>
        <br><br>

        <button type="submit">Ajouter le produit</button>
    </form>

    <hr>

    <h2>Liste des produits</h2>

    <table border="1" cellpadding="8">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Prix</th>
                <th>Stock</th>
                <th>Actions</th>
            </tr>
        </thead>

        <tbody>
        <%
            List<Produit> produits =
                (List<Produit>) request.getAttribute("produits");

            if (produits != null && !produits.isEmpty()) {
                for (Produit produit : produits) {
        %>
            <tr>
                <td><%= produit.getIdProduit() %></td>
                <td><%= produit.getNom() %></td>
                <td><%= produit.getDescription() %></td>
                <td><%= produit.getPrix() %></td>
                <td><%= produit.getStock() %></td>

                <td>
                    <a href="${pageContext.request.contextPath}/modifierProduit?id=<%= produit.getIdProduit() %>">
                        Modifier
                    </a>

                    |

                    <a href="${pageContext.request.contextPath}/supprimerProduit?id=<%= produit.getIdProduit() %>"
                       onclick="return confirm('Voulez-vous vraiment supprimer ce produit ?');">
                        Supprimer
                    </a>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="6">Aucun produit disponible.</td>
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
