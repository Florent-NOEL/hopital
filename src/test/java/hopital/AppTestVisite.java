package hopital;

import dao.DaoPatient;
import dao.DaoVisite;
import dao.JdbcContext;
import model.Medecin;
import model.Patient;
import model.Visite;

public class AppTestVisite {

	public static void main(String[] args) {
		
		testVisite();
	}
	
	private static void testVisite() {
		DaoPatient daoPatient = JdbcContext.getDaoPatient();
		DaoVisite daoVisite = JdbcContext.getDaoVisite();
		Patient pat4 = new Patient("nom4", "prenom4");
		daoPatient.create(pat4);
//		Patient pat1 = daoPatient.findByKey(1);
//		Medecin med1 = new Medecin("medecin1", "password1");
		Visite visite1 = new Visite(pat4,1);
		daoVisite.create(visite1);
	}

}
