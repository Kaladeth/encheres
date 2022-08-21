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
	
	String SELECTID="select*from utilisateur where no_utilisateur=?";
	private static final String VALIDATE_LOGIN ="SELECT * FROM utilisateurs where (pseudo=? OR email=?) AND mot_de_passe=?"; 
	private static final String INSERT_USER = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) VALUES ( ?, ?,?, ?, ?, ?, ?, ?,?, ?, ?)";
	private static final String SELECT_BY_PSEUDO_EMAIL = "SELECT * FROM utilisateurs where pseudo=? OR email=?";
	
	
	public Utilisateur selectById(int id) throws DALException {
		Utilisateur utilisateur = new Utilisateur();
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SELECTID)){
				stmt.setInt(1, id);			
				ResultSet rs =stmt.executeQuery();
			
            while(rs.next()) {
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
            }catch (SQLException e) {
            	DALException ex = new DALException("Login et/ou mot de passe non valides", e);
            	throw ex;}
		return utilisateur;
	}

	public List<Utilisateur> selectAll() throws DALException {
		
		return null;
	}

	public void insert(Utilisateur element) throws DALException {
		try (Connection cnx = ConnectionProvider.getConnection();){
			
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_PSEUDO_EMAIL, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, element.getPseudo());
			stmt.setString(2, element.getEmail());
			ResultSet rs = stmt.executeQuery();
			
			if(!rs.next()) {			
				stmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setString(1, element.getPseudo() );
				stmt.setString(2, element.getNom());
				stmt.setString(3, element.getPrenom() );
				stmt.setString(4, element.getEmail());
				stmt.setString(5, element.getTelephone());
				stmt.setString(6, element.getRue());
				stmt.setString(7, element.getCodePostal());
				stmt.setString(8, element.getVille());
				stmt.setString(9, element.getMotDePasse());
				stmt.setInt(10, element.getCredit());
				stmt.setBoolean(11, element.getAdministrateur());
				stmt.executeUpdate();
				rs = stmt.getGeneratedKeys();
				if(rs.next()) {
					element.setNoUtilisateur(rs.getInt(1));
				}
			}
			else
			{
				DALException ex = new DALException("L'identifiant et/ou l'email existent déjà !" );
				throw (ex);				
			}
			rs.close();
			stmt.close();
		}catch (SQLException e) {
			DALException ex = new DALException("Probleme d'ajout d'utilisateur", e);
			throw (ex);				
		}
		
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
			
			}catch (SQLException e) {
				DALException ex = new DALException("Login et/ou mot de passe non valides", e);
				throw ex;}
					
	return utilisateur;
	}

}
