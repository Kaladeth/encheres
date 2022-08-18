package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurDaoJdbcImpl implements UtilisateurDAO {
	String VALIDATE_LOGIN ="SELECT * FROM utilisateurs where (pseudo=? OR email=?) AND mot_de_passe=?"; 

	public Utilisateur selectById(Utilisateur element) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Utilisateur> selectAll(Utilisateur element) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	public void insert(Utilisateur element) throws DALException {
		// TODO Auto-generated method stub
		
	}

	public void update(Utilisateur element) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int element) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override

	public Utilisateur validate(String login, String motDePasse) throws DALException {
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(VALIDATE_LOGIN)){
			stmt.setString(1, login);			
			stmt.setString(2, login);
			stmt.setString(3, motDePasse);
			
			ResultSet rs =stmt.executeQuery();
			if (rs.next());{
				utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
				utilisateur.setCredit(rs.getInt("credit"));
				if(rs.getByte("administrateur") ==0) {
					utilisateur.setAdministrateur(false);
					}else {utilisateur.setAdministrateur(true);}
				}
			}catch (SQLException e) {
				DALException ex = new DALException("Login et/ou mot de passe non valides", e);
				throw ex;}
					
	return utilisateur;
	}

}
