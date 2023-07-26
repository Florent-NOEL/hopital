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
		Secretaire secretaire = new Secretaire("azeazeaze","azeazeaze");
		secretaire(secretaire);
//		menueSecretaire1();
//		menueSecretaire2();
		//secretaire();
	
	}


	public static void secretaire(Secretaire secretaire){
		menueSecretaire1();
		int i= 0;
		while( i !=4){
			i = saisieInt("choisir un chiffre");
			switch(i){
				case 1: menueSecretaire2(); secretaireAddPatient(secretaire);
				break;
				case 2: menueSecretaire3(); secretaireAfficherFile(secretaire);
				break;
				case 3: menueSecretaire4();secretairePartirEnPause(secretaire);
				break;
				case 4: menue1();
				break;
			}
		}
	}
	
	public static void secretaireAddPatient(Secretaire secretaire){
		
		String nom = saisieString("nom du patient");
		String prenom = saisieString("prenom");
		Patient patient = new Patient(nom, prenom);
		secretaire.addPatients(patient);
		
	}

	public static void secretairePartirEnPause(Secretaire secretaire){
		secretaire.ecrireListePatients();
		secretaire.ecritureFichierTexte();
	}

	public static void secretaireAfficherFile(Secretaire secretaire){
		secretaire.afficherListeAttente();
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
			+"Afficher l'etat de la file d'attente: 2"+"\n"
			+"Partire en pause: 3"+"\n"
			+"Menu principal: 4"+"\n"
			+"/////////////////////////////////////////// \n"

		);
	}

	public static void menueSecretaire2(){
		System.out.println(
			"///////////////Secrétaire ajouter patient////////////////"+"\n"
			+"///////////////////////////////////////////"+ "\n"
			
		);
	}

	public static void menueSecretaire3(){
		System.out.println(
			"///////////////Afficher file////////////////"+"\n"
			+"///////////////////////////////////////////"+ "\n"
			
		);
	}

	public static void menueSecretaire4(){
		System.out.println(
			"///////////////En pause////////////////"+"\n"
			+"///////////////////////////////////////////"+ "\n"
			
		);
	}

	

	public static void menueMedecin1(){
		System.out.println(
			"///////////////Médecin///////////////////"+ "\n"
			+"Rendre sa salle dispo: 1"+"\n"
			+"visualiser la liste d'attente : 2"+"\n"
			+"afficher prochain patients: 3"+"\n"
			+"Sauvgarder liste de visite: 4"+"\n"
			+"Menu principal: 5"+"\n"
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
