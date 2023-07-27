package hopital;

import dao.DaoCompte;
import dao.DaoVisite;
import dao.JdbcContext;
import model.Medecin;
import model.Visite;



public class AppTest {

	public static void main(String[] args) {
		medecin();

	}
	
	public static void medecin(){
		DaoCompte daoCompte = JdbcContext.getDaoCompte();
		DaoVisite daoVisite = JdbcContext.getDaoVisite();
		Visite name = null;
		Medecin med1 = new Medecin (daoCompte.findByKey(1).getId(),daoCompte.findByKey(1).getLogin(),daoCompte.findByKey(1).getPassword());
		med1.lectureListeAttente();
		med1.ouvertureSalle(1);
		med1.saveVisitesMedecin();
		med1.saveListeVisites();
	}
	
//	private static void testVisite() {
//		DaoPatient daoPatient = JdbcContext.getDaoPatient();
//		DaoVisite daoVisite = JdbcContext.getDaoVisite();
//		Patient pat4 = new Patient("nom4", "prenom4");
//		daoPatient.create(pat4);
//		Patient pat1 = daoPatient.findByKey(1);
//		Medecin med1 = new Medecin("medecin1", "password1");
//		Visite visite1 = new Visite(pat4,1);
//		daoVisite.create(visite1);
//	}

}
