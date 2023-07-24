package dao;
<<<<<<< HEAD
import dao.JdbcContext;
import model.Compte;

public class App {
    public static void main(String[] args) {
        DaoCompte daoCompte = JdbcContext.getDaoCompte();
        System.out.println(daoCompte.findAll());
        
        

    }
=======

import java.time.LocalDate;
import java.time.Month;

import dao.DaoPatient;
import model.Patient;


public class App {
	
	public static void main(String[] args) {
		DaoPatient daoPatient=JdbcContext.getDaoPatient();
		Patient lucas=new Patient("Naejus", "Lucas");
		daoPatient.create(lucas);
		

	}

>>>>>>> lucas
}
