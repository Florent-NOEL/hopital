package dao;
import dao.JdbcContext;
import model.Compte;

public class App {
    public static void main(String[] args) {
        DaoCompte daoCompte = JdbcContext.getDaoCompte();
        System.out.println(daoCompte.findAll());
        
        

    }
}
