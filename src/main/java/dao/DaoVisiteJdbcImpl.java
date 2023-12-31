package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Medecin;
import model.Patient;
import model.Visite;

class DaoVisiteJdbcImpl implements DaoVisite {

	@Override
	public void create(Visite obj) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"insert into visite(visite_patient_id,visite_medecin_id,visite_salle,visite_date) "
							+ "values(?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, obj.getPatient().getId());
			ps.setInt(2, obj.getMedecin().getId());
			ps.setInt(3, obj.getNumeroSalle());
			if (obj.getDate() != null) {
				ps.setDate(4, Date.valueOf(obj.getDate()));
			} else {
				ps.setNull(4, Types.DATE);
			}
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setNumeroVisite(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
	}

	@Override
	public void update(Visite obj) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getContext().getConnection()
					.prepareStatement("update visite set " + "visite_patient_id=?, visite_medecin_id=?, "
							+ "visite_salle=?, visite_date=?) " + "values(?,?,?,?)");
			ps.setInt(1, obj.getPatient().getId());
			ps.setInt(3, obj.getNumeroSalle());
			if (obj.getMedecin().getId() != null) {
				ps.setInt(2, obj.getMedecin().getId());
			} else {
				ps.setNull(2, Types.INTEGER);
			}
			if (obj.getDate() != null) {
				ps.setDate(4, Date.valueOf(obj.getDate()));
			} else {
				ps.setNull(4, Types.DATE);
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();

	}

	@Override
	public void delete(Visite obj) {
		deleteByKey(obj.getNumeroVisite());

	}

	@Override
	public void deleteByKey(Integer key) {
		PreparedStatement ps = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement("delete visite where visite_numero=?");
			ps.setInt(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();

	}

	static Visite getVisite(ResultSet rs) throws SQLException {
		Visite visite = new Visite(rs.getInt("visite_numero"), DaoPatientJdbcImpl.getPatient(rs), rs.getInt("visite_salle"));
		if (rs.getInt("visite_medecin_id") != 0) {
			visite.setNumeroSalle(rs.getInt("visite_medecin_id"));
		}
		if (rs.getDate("visite_date") != null) {
			visite.setDate(rs.getDate("visite_date").toLocalDate());
		}
		return visite;

	}

	@Override
	public Visite findByKey(Integer key) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Visite visite = null;
		try {
			ps = JdbcContext.getContext().getConnection()
					.prepareStatement("select * from visite v join patient p on v.visite_patient_id=p.patient_id "
							+ "join compte c on v.visite_medecin_id=c.compte_id where visite_numero=?");
			ps.setInt(1, key);
			rs = ps.executeQuery();
			if (rs.next()) {
				visite = getVisite(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return visite;
	}

	@Override
	public List<Visite> findAll() {
		List<Visite> list = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = JdbcContext.getContext().getConnection()
					.createStatement();
			rs = st.executeQuery("select * from visite v join patient p on v.visite_patient_id=p.patient_id "
					+ "join compte c on v.visite_medecin_id=c.compte_id");
			while (rs.next()) {
				list.add(getVisite(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return list;
	}
	
	public List<Visite> findByMedecin(Medecin medecin) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Visite> list = new ArrayList<>();
		try {
			ps = JdbcContext.getContext().getConnection()
					.prepareStatement("select * from visite v join patient p on v.visite_patient_id=p.patient_id "
							+ "join compte c on v.visite_medecin_id=c.compte_id where visite_medecin_id=?");
			ps.setInt(1, medecin.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(getVisite(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return list;
	}
	
	public List<Visite> findByPatient(Patient patient) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Visite> list = new ArrayList<>();
		try {
			ps = JdbcContext.getContext().getConnection()
					.prepareStatement("select * from visite v join patient p on v.visite_patient_id=p.patient_id "
							+ "join compte c on v.visite_medecin_id=c.compte_id where visite_patient_id=?");
			ps.setInt(1, patient.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Visite visite = getVisite(rs);
				Medecin medecin = new Medecin(rs.getString("compte_login"),rs.getString("compte_password"));
				visite.setMedecin(medecin);
				list.add(visite);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return list;
	}

}
