package dao;
import model.Compte;

public interface DaoCompte extends DaoGeneric<Compte, Integer>  {
    public Compte findLoginAndPassword(String nom, String prenom);
}
