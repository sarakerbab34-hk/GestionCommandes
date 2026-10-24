
package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;
import ma.fstt.entities.Commande;

public interface CommandeService {

    void ajouter(Commande commande) throws SQLException;

    List<Commande> lister() throws SQLException;

    Commande trouverParId(int id) throws SQLException;

    void modifier(Commande commande) throws SQLException;

    void supprimer(int id) throws SQLException;
}
