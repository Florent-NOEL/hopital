package dao;
import model.Compte;
import model.Medecin;

import java.util.List;
import java.util.Scanner;

import model.Patient;
import model.Secretaire;
import model.Visite;


public class App {

	public static void main(String[] args) {
		hospitalApp();
	}

	public static void hospitalApp(){
		int numero=0;
		while (numero!=1 & numero!=2) {
			numero= saisieInt("Entrer 1 pour creer un compte et 2 pour se connecter:");
		}
		if(numero == 1){
			createCompte();
		} else {
			login();
		}
	}

	public static void createCompte(){
		DaoCompte daoCompte = JdbcContext.getDaoCompte();
		String login;
		String password;
		int numero=0;
		login = saisieString("entrer votre login:");
		password = saisieString("entrer votre password:");

		while (numero!=1 & numero!=2) {
			numero= saisieInt("entrer 1 si vous êtes une secrétaire et 2 si vous êtes un medecin:");
		}
		if(numero == 1){
			daoCompte.create(new Secretaire(login, password));
		} else {
			daoCompte.create(new Medecin(login, password));
		}
		hospitalApp();
	}

	public static void login(){
		DaoCompte daoCompte = JdbcContext.getDaoCompte();
		String login;
		String password;
		Compte compte = null;
		login = saisieString("entrer votre login:");
		password = saisieString("entrer votre password:");
		compte = daoCompte.findLoginAndPassword(login, password);
		if(compte.getTypeCompte().equals("medecin")){
			logMedecin(compte);
		} else if(compte.getTypeCompte().equals("secretaire")){
			logSecretaire(compte);
		} else{ System.out.println("non renseigner");}
	} 

	public static void  logMedecin(Compte compte){
		Medecin medecin = null;
		medecin = new Medecin(compte.getId(),compte.getLogin(), compte.getPassword());
		medecin(medecin);
	}

	public static void logSecretaire(Compte compte){
		Secretaire secraitaire = null;
		secraitaire = new Secretaire(compte.getId(),compte.getLogin(), compte.getPassword());
		secretaire(secraitaire);
	}


	public static void secretaire(Secretaire secretaire){
		menueSecretaire1();
		int i= 0;
		while( i !=5){
			i = saisieInt("choisir un chiffre");
			switch(i){
				case 1: menueSecretaire2(); secretaireAddPatient(secretaire);menueSecretaire1();
				break;
				case 2: menueSecretaire3(); secretaireAfficherFile(secretaire);menueSecretaire1();
				break;
				case 3: menueSecretaire4();secretairePartirEnPause(secretaire);menueSecretaire1();
				break;
				case 4: secretaireAfficherVisitePatient(secretaire); menueSecretaire1();
				break;
				case 5: login();
				break;
			}
		}
	}
	
	public static void secretaireAddPatient(Secretaire secretaire){
		
		String nom = saisieString("nom du patient:");
		String prenom = saisieString("prenom du patient:");
		Patient patient = new Patient(nom, prenom);
		secretaire.addPatients(patient);
		secretaire.ecrireListeAttente();
		
	}

	public static void secretairePartirEnPause(Secretaire secretaire){
		secretaire.ecritureFichierTexte();
	}

	public static void secretaireAfficherFile(Secretaire secretaire){
		secretaire.lectureListeAttente();
	}

	public  static void secretaireAfficherVisitePatient(Secretaire secretaire){
		String nom = saisieString("nom du patient:");
		String prenom = saisieString("prenom du patient:");
		Patient patient = secretaire.trouverPatient(new Patient(nom, prenom));
		DaoVisite daoVisite = JdbcContext.getDaoVisite();
		List<Visite> list = daoVisite.findByPatient(patient);
		list.forEach(visite -> {
			System.out.println("visite date: "+visite.getDate()+ " avec le Docteur : "+visite.getMedecin().getLogin());
		});
	}

	// medecin

	public static void medecin(Medecin medecin){
		menueMedecin1();
	
		int i= 0;
		while( i !=6){
			i = saisieInt("choisir un chiffre");
			switch(i){
				case 1: menueMedecin2(); medecinOuvrirSalle(medecin);
				break;
				case 2: menueMedecin3(); medecinVisualiserListePatient(medecin);menueMedecin1();
				break;
				case 3: menueMedecin6(); medecinVisualiserProchainPatient(medecin);menueMedecin1();
				break;
				case 4: menueMedecin4();medecinSauvegarderListeVisite(medecin);menueMedecin1();
				break;
				case 5: menueMedecin5();medecinIscrireVisiteDB(medecin); menueMedecin1();
				break;
				case 6:login();
				break;	
			}
			}
	}

	public static void medecinOuvrirSalle(Medecin medecin){
		List <Visite> visites = medecin.getVisites();
		int salle=0;
		while (salle!=1 & salle!=2) {
			salle = saisieInt("entrer numéro salle");
		}
		medecin.ouvertureSalle(salle);
		if (visites.size() == 10) {
			medecinIscrireVisiteDB(medecin);
		}
		menueMedecin1();
	}

	public static void medecinVisualiserListePatient(Medecin medecin){
		medecin.lectureListeAttente();
	}
	
	public static void medecinVisualiserProchainPatient(Medecin medecin){
		medecin.lectureProchainPatient();
	}

	public static void medecinSauvegarderListeVisite(Medecin medecin){
		medecin.saveVisitesMedecin();

	}

	public static void medecinIscrireVisiteDB(Medecin medecin){
		medecin.saveListeVisites();
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
			+"Partir en pause: 3"+"\n"
			+"Afficher visite patients 4"+"\n"
			+"Menu login: 5"+"\n"
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
			+"Ouvrir sa salle: 1"+"\n"
			+"visualiser la liste d'attente : 2"+"\n"
			+"visualiser le prochain patient : 3"+"\n"
			+"Sauvegarder votre liste de visite: 4"+"\n"
			+"Enregistrer la liste de visite dans la BDD: 5"+"\n"
			+"Menu principal: 6"+"\n"
			+"/////////////////////////////////////////// \n"
		);
	}
	
	public static void menueMedecin2(){
		System.out.println(
				
//			"///////////////Voulez-vous ouvrir la Salle?///////////////////"+ "\n"+
			"///////////////Salle ouverte///////////////////"+ "\n"
			+"/////////////////////////////////////////// "+"\n"
		);
	}
	
	
	public static void menueMedecin3(){
		System.out.println(
			"///////////////Visualiser Liste Patient///////////////////"+ "\n"
			+"/////////////////////////////////////////// "+ "\n"
		);
	}
	
	public static void menueMedecin6(){
		System.out.println(
			"///////////////Visualiser Prochain Patient///////////////////"+ "\n"
			+"/////////////////////////////////////////// "+ "\n"
		);
	}
	
	public static void menueMedecin4(){
		System.out.println(
			"///////////////Sauvegarder Liste Visite ///////////////////"+ "\n"
			+"///////////////////////////////////////////"+"\n"
		);
	}
	
	public static void menueMedecin5(){
		System.out.println(
			"///////////////Enregistrer Liste Visite dans la base de donnée ///////////////////"+ "\n"
			+"///////////////////////////////////////////"+"\n"
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
