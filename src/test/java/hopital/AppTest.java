package hopital;

import dao.DaoCompte;
import dao.JdbcContext;
import model.Medecin;



public class AppTest {

	public static void main(String[] args) {
		medecin();

	}
	
	public static void medecin(){
		DaoCompte daoCompte = JdbcContext.getDaoCompte();
		Medecin med1 = new Medecin (daoCompte.findByKey(1).getId(),daoCompte.findByKey(1).getLogin(),daoCompte.findByKey(1).getPassword());
		med1.lectureListeAttente();
		med1.ouvertureSalle(1);
		med1.saveVisitesMedecin();
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
