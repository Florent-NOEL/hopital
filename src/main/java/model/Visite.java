package model;

import java.time.LocalDate;
import java.util.Objects;

public class Visite {
	private Integer numeroVisite;
	private VisiteKey id;
	private int tarif= 20;
	private int numeroSalle;
	private LocalDate date=LocalDate.now();
	
	public Visite(VisiteKey id, int numeroSalle, LocalDate date) {
		super();
		this.id = id;
		this.numeroSalle = numeroSalle;
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

	public Integer getNumeroVisite() {
		return numeroVisite;
	}

	public void setNumeroVisite(Integer numeroVisite) {
		this.numeroVisite = numeroVisite;
	}

	public VisiteKey getId() {
		return id;
	}

	public void setId(VisiteKey id) {
		this.id = id;
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
	
	
	
}
