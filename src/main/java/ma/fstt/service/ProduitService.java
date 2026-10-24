
package ma.fstt.service;

import java.sql.SQLException;
import java.util.List;
import ma.fstt.entities.Produit;

public interface ProduitService {

    void ajouter(Produit produit) throws SQLException;

    List<Produit> lister() throws SQLException;

    Produit trouverParId(int id) throws SQLException;

    void modifier(Produit produit) throws SQLException;

    void supprimer(int id) throws SQLException;
}
