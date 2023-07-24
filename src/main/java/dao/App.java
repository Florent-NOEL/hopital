package dao;
import dao.JdbcContext;
import model.Compte;

import java.time.LocalDate;
import java.time.Month;

import dao.DaoPatient;
import model.Patient;


public class App {
	
	public static void main(String[] args) {
		  DaoCompte daoCompte = JdbcContext.getDaoCompte();
	        System.out.println(daoCompte.findAll());
	        
		DaoPatient daoPatient=JdbcContext.getDaoPatient();
		
		
		

	}


}
