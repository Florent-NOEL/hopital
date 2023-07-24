package model;

public class Secretaire extends Compte {

	public Secretaire(String login, String password) {
		super(login, password);
		setTypeCompte("secretaire");
	}

}
