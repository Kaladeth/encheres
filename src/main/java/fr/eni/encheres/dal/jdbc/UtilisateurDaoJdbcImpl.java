package fr.eni.encheres.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurDaoJdbcImpl implements UtilisateurDAO {
	String VALIDATE_LOGIN ="SELECT * FROM utilisateurs where (pseudo=? OR email=?) AND mot_de_passe=?"; 
	String SELECTID="select*from utilisateur where nutilisateur";
	
	public Utilisateur selectById(Utilisateur element) throws DALException {
		try(Connection cnx = ConnectionProvider.getConnection()
				){
			Statement stmt =  cnx.createStatement();
			
            ResultSet rs = ((java.sql.Statement) stmt).executeQuery(SELECTID); 
            while(rs.next()) {
            Utilisateur	utilisateur = new Utilisateur();
				utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
            }
		}
			
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	public List<Utilisateur> selectAll(Utilisateur element) throws DALException {
		
		return null;
	}

	public void insert(Utilisateur element) throws DALException {
		
		
	}

	public void update(Utilisateur element) throws DALException {
		
		
	}

	@Override
	public void delete(int element) throws DALException {
		
		
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
				
				if(rs.getByte("administrateur") ==0) {
					utilisateur.setAdministrateur(false);
					}else {utilisateur.setAdministrateur(true);}
				}
			
			}catch (Exception e) {
			DALException ex = new DALException("Problème dans l'authentification de l'utilisateur", e);
			throw ex;}
	return utilisateur;
	}

}
