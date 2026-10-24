
package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;
import ma.fstt.entities.LigneDeCommande;

public interface LigneDeCommandeService {

    void ajouter(LigneDeCommande ligne) throws SQLException;

    List<LigneDeCommande> lister() throws SQLException;

    LigneDeCommande trouverParId(int id) throws SQLException;

    void modifier(LigneDeCommande ligne) throws SQLException;

    void supprimer(int id) throws SQLException;
}
