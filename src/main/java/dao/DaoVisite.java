package dao;

import java.util.List;

import model.Medecin;
import model.Patient;
import model.Visite;

public interface DaoVisite extends DaoGeneric<Visite, Integer>{
	List<Visite> findByMedecin(Medecin medecin);
	List<Visite> findByPatient(Patient patient);
}
