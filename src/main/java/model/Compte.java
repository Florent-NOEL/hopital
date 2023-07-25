package model;

import java.util.Objects;

public class Compte {
	private Integer id;
	private String login;
	private String password;
	private String typeCompte = null;
	
	
	
	public Compte() {

	}

	

	public Compte(Integer id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}



	public Compte(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getLogin() {
		return login;
	}

	public String getTypeCompte() {
		return typeCompte;
	}



	public void setLogin(String login) {
		this.login = login;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Compte other = (Compte) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
