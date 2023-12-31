package model;

import java.io.File;
import java.io.PrintWriter;
import dao.DaoPatient;
import dao.JdbcContext;

public class Secretaire extends Compte {

	private DaoPatient daoPatient = JdbcContext.getDaoPatient();
	
	public Secretaire(String login, String password) {
		super(login, password);
		setTypeCompte("secretaire");
	}

	

	public Secretaire(Integer id, String login, String password) {
		super(id, login, password);
		setTypeCompte("secretaire");
	}

	public Patient trouverPatient(Patient patient) {
		DaoPatient daoPatient = JdbcContext.getDaoPatient();
		Patient isKnowPatient = daoPatient.findByNomPrenom(patient.getNom(), patient.getPrenom());
		if(isKnowPatient == null){
			daoPatient.create(patient);
			isKnowPatient = daoPatient.findByNomPrenom(patient.getNom(), patient.getPrenom());
		}
		return isKnowPatient;
	}

	public void addPatients(Patient patient){
		Patient isKnowPatient = trouverPatient(patient);
		patients.add(isKnowPatient);
	}


	public void ecritureFichierTexte() {
		try {
			PrintWriter out = new PrintWriter(new File("listeAttente.txt"));
			patients.stream().forEach(
			obj ->{ out.println(obj.getNom()+" "+obj.getPrenom()+", ");
		});
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void afficherListeAttente() {
		patients.stream().forEach(
			obj ->{ System.out.println(obj.getNom()+" "+obj.getPrenom()+", ");
		});
	}



}
