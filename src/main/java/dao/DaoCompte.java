package dao;
import model.Compte;
import model.Patient;

public interface DaoCompte extends DaoGeneric<Compte, Integer>  {
    public Compte findByNomPrenom(String nom, String prenom);
}
