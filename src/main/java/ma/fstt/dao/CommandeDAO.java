
package ma.fstt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.entities.Commande;
import ma.fstt.service.CommandeService;

public class CommandeDAO implements CommandeService {

    public void ajouter(Commande commande) throws SQLException {
        String sql = "INSERT INTO commande (date_commande, statut, id_client) VALUES (?, ?, ?)";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (commande.getDateCommande() == null) {
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setTimestamp(1, Timestamp.valueOf(commande.getDateCommande()));
            }

            ps.setString(2, commande.getStatut());
            ps.setInt(3, commande.getIdClient());
            ps.executeUpdate();
        }
    }


    public List<Commande> lister() throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String sql = "SELECT * FROM commande";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Timestamp timestamp = rs.getTimestamp("date_commande");

                Commande commande = new Commande(
                    rs.getInt("id_commande"),
                    timestamp == null ? null : timestamp.toLocalDateTime(),
                    rs.getString("statut"),
                    rs.getInt("id_client")
                );

                commandes.add(commande);
            }
        }

        return commandes;
    }


    public Commande trouverParId(int id) throws SQLException {
        String sql = "SELECT * FROM commande WHERE id_commande = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Timestamp timestamp = rs.getTimestamp("date_commande");

                    return new Commande(
                        rs.getInt("id_commande"),
                        timestamp == null ? null : timestamp.toLocalDateTime(),
                        rs.getString("statut"),
                        rs.getInt("id_client")
                    );
                }
            }
        }

        return null;
    }

   
    public void modifier(Commande commande) throws SQLException {
        String sql = "UPDATE commande SET date_commande = ?, statut = ?, id_client = ? WHERE id_commande = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            if (commande.getDateCommande() == null) {
                ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            } else {
                ps.setTimestamp(1, Timestamp.valueOf(commande.getDateCommande()));
            }

            ps.setString(2, commande.getStatut());
            ps.setInt(3, commande.getIdClient());
            ps.setInt(4, commande.getIdCommande());
            ps.executeUpdate();
        }
    }


    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM commande WHERE id_commande = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
