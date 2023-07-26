package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dao.DaoPatient;
import dao.DaoPatientJdbcImpl;
import dao.JdbcContext;

public class Secretaire extends Compte {
	private List<Patient> patients = new ArrayList<>();
	private DaoPatient daoPatient = JdbcContext.getDaoPatient();
	
	public Secretaire(String login, String password) {
		super(login, password);
		setTypeCompte("secretaire");
	}

	public void addPatients(Patient patient){
		DaoPatient daoPatient = JdbcContext.getDaoPatient();
		Patient isKnowPatient = daoPatient.findByNomPrenom(patient.getNom(), patient.getPrenom());
		if(isKnowPatient == null){
			daoPatient.create(patient);
			isKnowPatient = daoPatient.findByNomPrenom(patient.getNom(), patient.getPrenom());
			
		}
		patients.add(isKnowPatient);
		
	}

	
	public void ecrireListePatients(){
		try {
			FileOutputStream fos = new FileOutputStream("listePatients");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(patients);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void ecritureFichierTexte() {
		try {
			PrintWriter out = new PrintWriter(new File("listePatients.txt"));
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



	//getter and setter
	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

}
