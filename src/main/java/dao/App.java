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
		secretaire();
	
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
