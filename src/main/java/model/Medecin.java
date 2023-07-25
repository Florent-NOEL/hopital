package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

import dao.JdbcContext;

public class Medecin extends Compte implements Serializable {

	public Medecin(String login, String password) {
		super(login, password);
		setTypeCompte("medecin");
	}
	
	

	public Medecin(Integer id, String login, String password) {
		super(id, login, password);
		setTypeCompte("medecin");
	}



	public void lectureListeAttente() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listePatients"));
			List<Patient> fileAttente = (List<Patient>) ois.readObject();
			fileAttente.forEach(patientLu -> {
				System.out.println(patientLu.getPrenom() + " " + patientLu.getNom());
			});
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// méthode qui, à l'ouverture de la salle, attribut une visite au premier
	// patient et ajoute cette visite dans le fichier "liste_visites"
	public void ouvertureSalle(int numSalle) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listePatients"));
			List<Patient> fileAttente = (List<Patient>) ois.readObject();
			Patient patient = fileAttente.get(0);
			Visite visite = new Visite(patient, this, numSalle);
			JdbcContext.getDaoVisite().create(visite);
			ois.close();
			FileOutputStream fos = new FileOutputStream("liste_visites");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(visite);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveVisitesMedecin() {
		List<Visite> visites = JdbcContext.getDaoVisite().findByMedecin(this);
		try {
			FileOutputStream fos = new FileOutputStream("liste_visites_medecin_" + this.getId());
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(visites);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// Méthode qui sauvegarde la liste des visites dans l BDD
	public void saveListeVisites() {

	}

}
