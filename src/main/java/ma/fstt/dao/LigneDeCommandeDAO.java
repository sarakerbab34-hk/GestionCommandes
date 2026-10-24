
package ma.fstt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.entities.LigneDeCommande;
import ma.fstt.service.LigneDeCommandeService;

public class LigneDeCommandeDAO implements LigneDeCommandeService {

   
    public void ajouter(LigneDeCommande ligne) throws SQLException {
        String sql = "INSERT INTO ligne_de_commande "
                   + "(id_commande, id_produit, quantite, prix_unitaire) "
                   + "VALUES (?, ?, ?, ?)";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ligne.getIdCommande());
            ps.setInt(2, ligne.getIdProduit());
            ps.setInt(3, ligne.getQuantite());
            ps.setDouble(4, ligne.getPrixUnitaire());

            ps.executeUpdate();
        }
    }

    public List<LigneDeCommande> lister() throws SQLException {
        List<LigneDeCommande> lignes = new ArrayList<>();
        String sql = "SELECT * FROM ligne_de_commande";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LigneDeCommande ligne = new LigneDeCommande(
                    rs.getInt("id_ligne"),
                    rs.getInt("id_commande"),
                    rs.getInt("id_produit"),
                    rs.getInt("quantite"),
                    rs.getDouble("prix_unitaire")
                );

                lignes.add(ligne);
            }
        }

        return lignes;
    }

   
    public LigneDeCommande trouverParId(int id) throws SQLException {
        String sql = "SELECT * FROM ligne_de_commande WHERE id_ligne = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new LigneDeCommande(
                        rs.getInt("id_ligne"),
                        rs.getInt("id_commande"),
                        rs.getInt("id_produit"),
                        rs.getInt("quantite"),
                        rs.getDouble("prix_unitaire")
                    );
                }
            }
        }

        return null;
    }

   
    public void modifier(LigneDeCommande ligne) throws SQLException {
        String sql = "UPDATE ligne_de_commande "
                   + "SET id_commande = ?, id_produit = ?, "
                   + "quantite = ?, prix_unitaire = ? "
                   + "WHERE id_ligne = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ligne.getIdCommande());
            ps.setInt(2, ligne.getIdProduit());
            ps.setInt(3, ligne.getQuantite());
            ps.setDouble(4, ligne.getPrixUnitaire());
            ps.setInt(5, ligne.getIdLigne());

            ps.executeUpdate();
        }
    }

   
    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM ligne_de_commande WHERE id_ligne = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
