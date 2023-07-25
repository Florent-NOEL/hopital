package dao;
import dao.JdbcContext;
import model.Compte;

import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

import dao.DaoPatient;
import model.Patient;
import model.Secretaire;


public class App {
	
	public static void main(String[] args) {
		menue1();
		menueSecretaire1();
	
	}

	public static void secretaire(){
		DaoPatient daoPatient = JdbcContext.getDaoPatient();
		Secretaire secretaire1 = new Secretaire("flora", "123");
		String nom = saisieString("nom du patient");
		String prenom = saisieString("prenom");
		Patient patient = new Patient(nom, prenom);
		secretaire1.addPatients(patient);
		secretaire1.ecrireListePatients();
		
	}

	public static void medecin(){
		
	}

	//Menue
	public static void menue1(){
		System.out.println(
			"////////////////Menu //////////////////////"+ "\n"
			+"Please enter 1 or 2 "+"\n"
			+"Secréraire : 1"+"\n"
			+"Medecin : 2"+"\n"
			+"/////////////////////////////////////////// \n"

		);
	}

	public static void menueSecretaire1(){
		System.out.println(
			"///////////////Secrétaire///////////////////"+ "\n"
			+"Ajouter patient à la file d'attente: 1"+"\n"
			+"Secréraire : 2"+"\n"
			+"Afficher l'etat de la file d'attente: 3"+"\n"
			+"Partire en pause: 4"+"\n"
			+"Ajouter un patient à la file d'attente: 4"+"\n"
			+"/////////////////////////////////////////// \n"

		);
	}

	




	// prompt
	static void saySomething(String msg) {
		System.out.println(msg);
	}

	static int addition(int nombre1, int nombre2) {
		return nombre1 + nombre2;
	}

	static int saisieInt(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextInt();
	}

	static double saisieDouble(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextDouble();
	}

	static String saisieString(String message) {
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextLine();
	}

}
