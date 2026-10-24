
package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;
import ma.fstt.entities.Client;

public interface ClientService {

    void ajouter(Client client) throws SQLException;

    List<Client> lister() throws SQLException;

    Client trouverParId(int id) throws SQLException;

    void modifier(Client client) throws SQLException;

    void supprimer(int id) throws SQLException;
}
