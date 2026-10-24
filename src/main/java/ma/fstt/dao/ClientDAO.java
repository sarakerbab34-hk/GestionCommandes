
package ma.fstt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.fstt.entities.Client;
import ma.fstt.service.ClientService;

public class ClientDAO implements ClientService {

  
    public void ajouter(Client client) throws SQLException {
        String sql = "INSERT INTO client (nom, email, telephone) VALUES (?, ?, ?)";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, client.getNom());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getTelephone());
            ps.executeUpdate();
        }
    }

   
    public List<Client> lister() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Client client = new Client(
                    rs.getInt("id_client"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getString("telephone")
                );
                clients.add(client);
            }
        }

        return clients;
    }

   
    public Client trouverParId(int id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id_client = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Client(
                        rs.getInt("id_client"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("telephone")
                    );
                }
            }
        }

        return null;
    }

 
    public void modifier(Client client) throws SQLException {
        String sql = "UPDATE client SET nom = ?, email = ?, telephone = ? WHERE id_client = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, client.getNom());
            ps.setString(2, client.getEmail());
            ps.setString(3, client.getTelephone());
            ps.setInt(4, client.getIdClient());
            ps.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM client WHERE id_client = ?";

        try (Connection con = Connexion.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
