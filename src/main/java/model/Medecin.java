package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.JdbcContext;

public class Medecin extends Compte implements Serializable {
	private List<Visite> visites = new ArrayList<>();

	public Medecin(Integer id, String login, String password) {
		super(id, login, password);
		setTypeCompte("medecin");
	}

	public Medecin(String login, String password) {
		super(login, password);
		setTypeCompte("medecin");
	}


	// méthode qui, à l'ouverture de la salle, attribut une visite au premier
	// patient et ajoute cette visite dans la liste visites
	public void ouvertureSalle(int numSalle) {
		try {

			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("listeAttente"));
			patients = (List<Patient>) ois.readObject();
			if(patients.size() !=0){
				Patient patient = patients.get(0);
				patients.remove(patient);
				Visite visite = new Visite(patient, this, numSalle);
				visites.add(visite);
				ois.close();
				ecrireListeAttente();
			}else {
				System.out.println("plus de patients fait une pause fréro !!");
			}
			
//			ObjectInputStream fichierEntree = new ObjectInputStream(new FileInputStream("listePatients.txt"));
//			Scanner sc = new Scanner(fichierEntree);
//			File file = new File("ListeVisite.txt");
//			PrintWriter sortie = new PrintWriter(file);
//			String ligneLu;
//			while (sc.hasNextLine()) {
//				ligneLu = sc.nextLine();
//				ligneLu = ligneLu.replace("1 ere ligne", "");
//				sortie.println(ligneLu);
//			}
//			sc.close();
//			sortie.close();
//			fichierEntree.delete();
//			file.renameTo(new File("BUILDING.txt"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Méthode qui sauvegarde dans un fichier la liste des visites inscrites dans la
	// BDD pour un médecin
	public void saveVisitesMedecin() {
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

	// Méthode qui sauvegarde la liste des visites de la liste visites dans la BDD
	public void saveListeVisites() {
		try {
			visites.forEach(visite -> {
				JdbcContext.getDaoVisite().create(visite);
			});
			visites.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Visite> getVisites() {
		return visites;
	}

	public void setVisites(List<Visite> visites) {
		this.visites = visites;
	}
}
