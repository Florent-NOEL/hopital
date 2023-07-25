package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Visite implements Serializable {
	private Integer numeroVisite;
	private Patient patient;
	private Medecin medecin;
	private int tarif= 20;
	private int numeroSalle;
	private LocalDate date=LocalDate.now();
	
	public Visite(Patient patient, int numeroSalle) {
		super();
		this.patient = patient;
		this.numeroSalle = numeroSalle;
	}

	public Visite(Patient patient, Medecin medecin, int numeroSalle, LocalDate date) {
		super();
		this.patient = patient;
		this.medecin = medecin;
		this.numeroSalle = numeroSalle;
		this.date = date;
	}
	
	

	public Visite(Integer numeroVisite, Patient patient, int numeroSalle) {
		super();
		this.numeroVisite = numeroVisite;
		this.patient = patient;
		this.numeroSalle = numeroSalle;
	}

	public Visite(Patient patient, Medecin medecin, int numeroSalle) {
		super();
		this.patient = patient;
		this.medecin = medecin;
		this.numeroSalle = numeroSalle;
	}

	public Visite(Integer numeroVisite, Patient patient, Medecin medecin, int numeroSalle, LocalDate date) {
		super();
		this.numeroVisite = numeroVisite;
		this.patient = patient;
		this.medecin = medecin;
		this.numeroSalle = numeroSalle;
		this.date = date;
	}
	
	

	public Integer getNumeroVisite() {
		return numeroVisite;
	}

	public void setNumeroVisite(Integer numeroVisite) {
		this.numeroVisite = numeroVisite;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Medecin getMedecin() {
		return medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	public int getTarif() {
		return tarif;
	}

	public void setTarif(int tarif) {
		this.tarif = tarif;
	}

	public int getNumeroSalle() {
		return numeroSalle;
	}

	public void setNumeroSalle(int numeroSalle) {
		this.numeroSalle = numeroSalle;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroVisite);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Visite other = (Visite) obj;
		return Objects.equals(numeroVisite, other.numeroVisite);
	}
	
	
	

	
	
	
	
}
