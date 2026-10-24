
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Gestion des commandes</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5f9;
            text-align: center;
            padding: 50px;
        }

        h1 {
            color: #243b53;
        }

        .menu {
            margin: 30px auto;
            max-width: 500px;
        }

        .menu a {
            display: block;
            margin: 15px;
            padding: 18px;
            background: #176b87;
            color: white;
            text-decoration: none;
            border-radius: 8px;
            font-size: 18px;
        }

        .menu a:hover {
            background: #0d4f66;
        }
    </style>
</head>
<body>

    <h1>Gestion des commandes</h1>
    <p>Bienvenue dans votre application de gestion commerciale.</p>

    <div class="menu">
        <a href="<%= request.getContextPath() %>/clients">Gestion des clients</a>
        <a href="<%= request.getContextPath() %>/produits">Gestion des produits</a>
        <a href="<%= request.getContextPath() %>/commandes">Gestion des commandes</a>
        <a href="<%= request.getContextPath() %>/lignes">Gestion des lignes de commande</a>
    </div>

</body>
</html>
