package dao;
import model.Compte;
import model.Patient;

public interface DaoCompte extends DaoGeneric<Compte, Integer>  {
    public Compte findLoginAndPassword(String nom, String prenom);
}
