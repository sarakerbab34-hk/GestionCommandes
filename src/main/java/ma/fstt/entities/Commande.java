
package ma.fstt.entities;

import java.time.LocalDateTime;

public class Commande {

    private int idCommande;
    private LocalDateTime dateCommande;
    private String statut;
    private int idClient;

    public Commande() {
    }

    public Commande(int idCommande, LocalDateTime dateCommande,
                    String statut, int idClient) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.statut = statut;
        this.idClient = idClient;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public LocalDateTime getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDateTime dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
