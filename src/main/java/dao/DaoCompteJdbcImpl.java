package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Compte;
import model.Patient;

public class DaoCompteJdbcImpl implements DaoCompte {

    @Override
    public void create(Compte obj) {
       PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"insert into compte(compte_login, compte_password, compte_typeCompte) values(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, obj.getLogin());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getTypeCompte());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				obj.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
    }

    @Override
    public void update(Compte obj) {
        PreparedStatement ps = null;

		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"Update compte set compte_login=?, compte_password=?, compte_typeCompte=? where compte_id=?");
			ps.setString(1, obj.getLogin());
            ps.setString(2, obj.getPassword());
            ps.setString(3, obj.getTypeCompte());
            ps.setInt(4, obj.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
    }

    @Override
    public void delete(Compte obj) {
        deleteByKey(obj.getId());
        
    }

    @Override
    public void deleteByKey(Integer key) {
         PreparedStatement ps = null;

		try {
			ps = JdbcContext.getContext().getConnection().prepareStatement(
					"select * from compte where compte_id=?");
            ps.setInt(1, key);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
    }

    @Override
    public Compte findByKey(Integer key) {
        PreparedStatement ps = null;
		ResultSet rs = null;
		Compte compte = null;
		try {
			ps = JdbcContext.getContext().getConnection()
					.prepareStatement("select * from compte where compte_id=?");
			ps.setInt(1, key);
			rs = ps.executeQuery();
			if (rs.next()) {
				compte= new Compte(rs.getString("compte_login"), rs.getString("compte_password"));
                compte.setId(rs.getInt("compte_id"));
                compte.setTypeCompte(rs.getString("compte_typeCompte"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return compte;
    }

    @Override
    public List<Compte> findAll() {
        List<Compte> comptes = new ArrayList<>();
		Statement st = null;
		ResultSet rs = null;
        Compte compte = null;
		try {
			st = JdbcContext.getContext().getConnection().createStatement();
			rs = st.executeQuery("select * from compte");
			while (rs.next()) {
				compte= new Compte(rs.getString("compte_login"), rs.getString("compte_password"));
                compte.setId(rs.getInt("compte_id"));
                compte.setTypeCompte(rs.getString("compte_typeCompte"));
                comptes.add(compte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return comptes;
    }

	public static Compte getCompte(ResultSet rs) throws SQLException {
			Compte compte = null;
			compte= new Compte(rs.getString("compte_login"), rs.getString("compte_password"));
            compte.setId(rs.getInt("compte_id"));
            compte.setTypeCompte(rs.getString("compte_typeCompte"));
			return compte;
	}

	@Override
	public Compte findByNomPrenom(String nom, String prenom) {
		
		PreparedStatement ps = null;
		Compte compte = null;
		ResultSet rs = null;
		try {
			ps = JdbcContext.getContext().getConnection()
					.prepareStatement("select * from compte where compte_nom=? and compte_prenom=?");
			ps.setString(1, nom);
			ps.setString(2, prenom);
			rs = ps.executeQuery();
			if (rs.next()) {
				compte = getCompte(rs);
				if(rs.getInt("compte_id") != 0){
					compte.setId(rs.getInt("compte_id"));
					compte.setLogin("compte_password");
				} else{compte.setId(0);}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JdbcContext.close();
		return compte;
		
	}
    
}
