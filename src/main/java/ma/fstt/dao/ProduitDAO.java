
package ma.fstt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.entities.Produit;
import ma.fstt.service.ProduitService;

public class ProduitDAO implements ProduitService {

   
    public void ajouter(Produit produit) throws SQLException {
        String sql = "INSERT INTO produit (nom, description, prix, stock) VALUES (?, ?, ?, ?)";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, produit.getNom());
            ps.setString(2, produit.getDescription());
            ps.setDouble(3, produit.getPrix());
            ps.setInt(4, produit.getStock());
            ps.executeUpdate();
        }
    }

    
    public List<Produit> lister() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produit produit = new Produit(
                    rs.getInt("id_produit"),
                    rs.getString("nom"),
                    rs.getString("description"),
                    rs.getDouble("prix"),
                    rs.getInt("stock")
                );
                produits.add(produit);
            }
        }

        return produits;
    }

  
    public Produit trouverParId(int id) throws SQLException {
        String sql = "SELECT * FROM produit WHERE id_produit = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Produit(
                        rs.getInt("id_produit"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getDouble("prix"),
                        rs.getInt("stock")
                    );
                }
            }
        }

        return null;
    }

  
    public void modifier(Produit produit) throws SQLException {
        String sql = "UPDATE produit SET nom = ?, description = ?, prix = ?, stock = ? WHERE id_produit = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, produit.getNom());
            ps.setString(2, produit.getDescription());
            ps.setDouble(3, produit.getPrix());
            ps.setInt(4, produit.getStock());
            ps.setInt(5, produit.getIdProduit());
            ps.executeUpdate();
        }
    }

   
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM produit WHERE id_produit = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
