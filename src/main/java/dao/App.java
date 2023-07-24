package dao;

import java.time.LocalDate;
import java.time.Month;

import dao.DaoPatient;
import model.Patient;


public class App {
	
	public static void main(String[] args) {
		DaoPatient daoPatient=JdbcContext.getDaoPatient();
		Patient lucas=new Patient("Naejus", "Lucas");
		daoPatient.create(lucas);
		

	}

}
