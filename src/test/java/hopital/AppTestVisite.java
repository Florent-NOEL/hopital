package hopital;

import java.time.LocalDate;

import dao.DaoVisite;
import dao.JdbcContext;
import model.Medecin;
import model.Patient;
import model.Visite;
import model.VisiteKey;

public class AppTestVisite {

	public static void main(String[] args) {
		
		

	}
	
	private static void testVisite() {
		DaoVisite daoVisite = JdbcContext.getDaoVisite();
		Patient pat1 = new Patient("nom1", "prenom1");
		Medecin med1 = new Medecin("medecin1", "password1");
		VisiteKey vKey = new VisiteKey(med1,pat1);
		Visite visite1 = new Visite(vKey,1,LocalDate.now());
		daoVisite.create(visite1);
	}

}
