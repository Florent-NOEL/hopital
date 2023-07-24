package model;

import java.util.Objects;

public class VisiteKey {
private Medecin medecin;
private Patient patient;


public VisiteKey() {
	super();
}


public VisiteKey(Medecin medecin, Patient patient) {
	super();
	this.medecin = medecin;
	this.patient = patient;
}


@Override
public int hashCode() {
	return Objects.hash(medecin, patient);
}


@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	VisiteKey other = (VisiteKey) obj;
	return Objects.equals(medecin, other.medecin) && Objects.equals(patient, other.patient);
}


public Medecin getMedecin() {
	return medecin;
}


public void setMedecin(Medecin medecin) {
	this.medecin = medecin;
}


public Patient getPatient() {
	return patient;
}


public void setPatient(Patient patient) {
	this.patient = patient;
}



}
