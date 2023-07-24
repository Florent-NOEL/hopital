package dao;
import dao.JdbcContext;
import model.Compte;

public class App {
    public static void main(String[] args) {
        DaoCompte daoCompte = JdbcContext.getDaoCompte();
        
        Compte compte1 = new Compte("flo", "wxc");
        compte1.setId(1);
        daoCompte.update(compte1);
    }
}
