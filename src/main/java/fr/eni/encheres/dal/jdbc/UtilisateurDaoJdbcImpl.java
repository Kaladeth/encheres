package fr.eni.encheres.dal.jdbc;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurDaoJdbcImpl implements UtilisateurDAO {
	
	private static final String SELECT_BY_ID = "select * from utilisateurs where no_utilisateur=?";
	private static final String VALIDATE_LOGIN ="SELECT * FROM utilisateurs where (pseudo=? OR email=?) AND mot_de_passe=?"; 
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES ( ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?)";
	private static final String SELECT_BY_PSEUDO_EMAIL = "SELECT * FROM utilisateurs where pseudo=? OR email=?";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo=?, nom=? ,prenom=? ,email=?, telephone=? ,rue=?, code_postal=? ,ville=?, mot_de_passe=? WHERE no_utilisateur=?";
	
	
	public Utilisateur selectById(int id) throws DALException {
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)){
			stmt.setInt(1, id);			
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				utilisateur = new Utilisateur();
            	utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
				utilisateur.setPseudo(rs.getString("pseudo"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setCredit(rs.getInt("credit"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setRue(rs.getString("rue"));
				utilisateur.setCodePostal(rs.getString("code_postal"));
				utilisateur.setVille(rs.getString("ville"));
            }
            }catch (Exception e) {
            	DALException ex = new DALException("Problème d'accès à cet utilisateur", e);
            	throw ex;}
		return utilisateur;
	}
//INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES ( ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?)";
	public List<Utilisateur> selectAll() throws DALException {
		
		return null;
	}

	public void insert(Utilisateur element) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
			PreparedStatement stmtChk = cnx.prepareStatement(SELECT_BY_PSEUDO_EMAIL)){
System.out.println(1);			
			try {
				cnx.setAutoCommit(false);
System.out.println(2);
				stmtChk.setString(1, element.getPseudo());
				stmtChk.setString(2, element.getEmail());
				ResultSet rs = stmtChk.executeQuery();
System.out.println(3);
				if(!rs.next()) {			
					stmt.setString(1, element.getPseudo() );
System.out.println(4);
					stmt.setString(2, element.getNom());
System.out.println(5);
					stmt.setString(3, element.getPrenom() );
System.out.println(6);
					stmt.setString(4, element.getEmail());
System.out.println(7);					
					stmt.setString(5, element.getTelephone());
System.out.println(8);
					stmt.setString(6, element.getRue());
System.out.println(9);
					stmt.setString(7, element.getCodePostal());
System.out.println(10);
					stmt.setString(8, element.getVille());
System.out.println(11);
					stmt.setString(9, element.getMotDePasse());
System.out.println(12);
					stmt.setInt(10, element.getCredit());
System.out.println(13);
					stmt.setBoolean(11, element.getAdministrateur());
System.out.println(14);
System.out.println(element);
					stmt.executeUpdate();
					rs = stmt.getGeneratedKeys();
System.out.println(15);
System.out.println(element);
					if(rs.next()) {
						element.setNoUtilisateur(rs.getInt(1));
System.out.println(16);				
					}
					
				}
				else
				{				
					throw new DALException("L'identifiant et/ou l'email existent déjà !" );			
				}
System.out.println(17);
				cnx.commit();
			}catch(Exception e) {
				cnx.rollback();
				throw new DALException("Probleme d'ajout d'utilisateur", e);
				}
		}catch (Exception e) {
			throw new DALException("Probleme d'ajout d'utilisateur", e);
		}
		
	}
// UPDATE_USER = "UPDATE UTILISATEURS SET pseudo=?, nom=? ,prenom=? ,
	//email=?, telephone=? ,rue=?, code_postal=? ,ville=? WHERE no_utilisateur=?";
	public void update(Utilisateur utilisateur) throws DALException{ 
	try (Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(UPDATE_USER);
			PreparedStatement stmtChk = cnx.prepareStatement(SELECT_BY_PSEUDO_EMAIL)){ 

			try {
				cnx.setAutoCommit(false);				
				stmtChk.setString(1, utilisateur.getPseudo());
				stmtChk.setString(2, utilisateur.getEmail());
				stmtChk.executeQuery();
				ResultSet rs = stmtChk.executeQuery();
				if(rs.next())
					if(rs.getInt("no_utilisateur") != utilisateur.getNoUtilisateur()) {
						DALException ex = new DALException("L'identifiant et/ou l'email existent déjà !" );
						throw (ex);	
					}
				stmt.setString(1, utilisateur.getPseudo());
				stmt.setString(2, utilisateur.getNom());
				stmt.setString(3, utilisateur.getPrenom());
				stmt.setString(4, utilisateur.getEmail());
				stmt.setString(5, utilisateur.getTelephone());
				stmt.setString(6, utilisateur.getRue());
				stmt.setString(7, utilisateur.getCodePostal());
				stmt.setString(8, utilisateur.getVille());
				stmt.setInt(9, utilisateur.getNoUtilisateur());
				
				stmt.executeUpdate();	
				
				
				cnx.commit();
			}catch(Exception e) {
				cnx.rollback();
				throw new DALException("Probleme d'ajout d'utilisateur : ", e);	
			}
		}catch (Exception e) {
			throw new DALException("Probleme d'ajout d'utilisateur : ", e);			
		}
		
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
