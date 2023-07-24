package dao;

import model.Patient;

public interface DaoPatient extends DaoGeneric<Patient, Integer> {
    public Patient findByNomPrenom(String nom, String prenom);
}
