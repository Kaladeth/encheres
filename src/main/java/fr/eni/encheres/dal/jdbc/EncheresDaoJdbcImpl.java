package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EncheresDaoJdbcImpl implements EnchereDAO {
	private static final String SELECT_ALL ="select * from encheres";
	private static final String SELECT_BY_ARTICLE_LIBELLE ="select * from ENCHERES E, ARTICLES_VENDUS A, CATEGORIES C where E.no_article = A.no_article AND A.no_categorie = C.no_categorie AND nom_article like ? AND libelle = ?";
	private static final String SELECT_BY_ARTICLE ="select * from ENCHERES E, ARTICLES_VENDUS A, CATEGORIES C where E.no_article = A.no_article AND A.no_categorie = C.no_categorie AND nom_article like ?";
	private static final String UPDATE_CREDIT_ANCIEN_ACHETEUR = "UPDATE UTILISATEURS SET credit=? where no_utilisateur=?";
	private static final String SELECT_CREDIT_ANCIEN_ACHETEUR = "select * FROM UTILISATEURS u LEFT JOIN ENCHERES e on e.no_utilisateur = u.no_utilisateur WHERE e.no_article=?";
	private static final String SELECT_CREDIT_NOUVEL_ACHETEUR = "select * from utilisateurs where no_utilisateur=?";
	private static final String UPDATE_MONTANT_ENCHERE = "UPDATE ENCHERES SET montant_enchere=?, no_utilisateur=?, date_enchere=? WHERE no_article=?";
	private static final String UPDATE_CREDIT_NOUVEL_ACHETEUR ="UPDATE UTILISATEURS SET credit=? WHERE no_utilisateur=?";

	@Override
	public List<Enchere> selectAll() throws DALException {

			List<Enchere> listEncheres = new ArrayList<Enchere>();
			try (Connection cnx = ConnectionProvider.getConnection();){
				
				Statement stmt = cnx.createStatement();
				ResultSet rs = stmt.executeQuery(SELECT_ALL);
				while(rs.next()) {
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noArticle = rs.getInt("no_article");
				LocalDateTime dateEnchere = LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime());
				int montant_enchere = rs.getInt("montant_enchere");
				Enchere enchere = new Enchere(noUtilisateur , noArticle, dateEnchere, montant_enchere);
				listEncheres.add(enchere);
					
				}	
				
			}catch (SQLException e) {
				DALException ex = new DALException("Probleme d'afficher listes Encheres", e);
				throw (ex);				
			}
			return listEncheres;	
		}

	@Override
	public Enchere selectByArticle(int id) throws DALException {
		Enchere enchere = null;
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ARTICLE)){
			stmt.setInt(1, id);			
			ResultSet rs =stmt.executeQuery();
			while(rs.next()) {
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noArticle = rs.getInt("no_article");
				LocalDateTime dateEnchere = LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime());
				int montant_enchere = rs.getInt("montant_enchere");
				enchere = new Enchere(noUtilisateur , noArticle, dateEnchere, montant_enchere);
				}
            }catch (SQLException e) {
            	DALException ex = new DALException("problème d'accès à cet article", e);
            	throw ex;}
		return enchere;
	}

	@Override
	public List<Enchere> filtrerListeEncheres(String nomArticle, String categorie) throws DALException {

			List<Enchere> listEncheres = new ArrayList<Enchere>();
			try (Connection cnx = ConnectionProvider.getConnection();){
			PreparedStatement stmt = null;
				
				if (categorie.toLowerCase().equals("toutes"))
				{
					stmt = cnx.prepareStatement(SELECT_BY_ARTICLE);
					stmt.setString(1, "%" + nomArticle + "%");
				}
				else
				{
					stmt = cnx.prepareStatement(SELECT_BY_ARTICLE_LIBELLE);
					stmt.setString(1, "%" + nomArticle + "%");
					stmt.setString(2, categorie);
				}
				
				ResultSet rs =stmt.executeQuery();
				while(rs.next()) {
					int noUtilisateur = rs.getInt("no_utilisateur");
					int noArticle = rs.getInt("no_article");
					LocalDateTime dateEnchere = LocalDateTime.of((rs.getDate("date_enchere").toLocalDate()), rs.getTime("date_enchere").toLocalTime());
					int montant_enchere = rs.getInt("montant_enchere");
					Enchere enchere = new Enchere(noUtilisateur , noArticle, dateEnchere, montant_enchere);
					listEncheres.add(enchere);
				}	

			}catch (SQLException e) {
				DALException ex = new DALException("Probleme d'afficher listes Encheres", e);
				throw (ex);				
			}
			return listEncheres;	
	}

	
	@Override
	public void insert(Enchere element) throws DALException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(Enchere element) throws DALException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void delete(int element) throws DALException {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Enchere selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public int updateEnchere(int idArticle, int idAcheteur, int valeurEnchere) throws DALException  {
		int idArticleMAJ = idArticle;
		
		try(Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement stmtUpdateAncienAcheteur = cnx.prepareStatement(UPDATE_CREDIT_ANCIEN_ACHETEUR);
			PreparedStatement stmtUpdateMontantEnchere = cnx.prepareStatement(UPDATE_MONTANT_ENCHERE);
			PreparedStatement stmtUpdateNouvelAcheteur = cnx.prepareStatement(UPDATE_CREDIT_NOUVEL_ACHETEUR);
			PreparedStatement stmtSelectAncienAcheteur = cnx.prepareStatement(SELECT_CREDIT_ANCIEN_ACHETEUR);
			PreparedStatement stmtSelectNouvelAcheteur = cnx.prepareStatement(SELECT_CREDIT_NOUVEL_ACHETEUR)) {
			try {
				cnx.setAutoCommit(false);
				System.out.println(1);
				int creditAncienAcheteur = 0;
				int creditNouvelAcheteur = 0;
				int idAncienAcheteur = 0;
				
				stmtSelectAncienAcheteur.setInt(1, idArticle);
				ResultSet rsAA = stmtSelectAncienAcheteur.executeQuery();
				while(rsAA.next()) {
					creditAncienAcheteur = rsAA.getInt("credit");
					idAncienAcheteur = rsAA.getInt("no_utilisateur");
					}
				if(idAncienAcheteur == 0 || creditAncienAcheteur == 0) {
					cnx.rollback();
					DALException ex = new DALException("Problème dans 'accès à l'ancien enchéreur");
					throw (ex);
				}
				
				stmtSelectNouvelAcheteur.setInt(1, idAcheteur);
				
				ResultSet rsNA = stmtSelectNouvelAcheteur.executeQuery();
				
				System.out.println(22);
				while(rsNA.next()) {
					creditNouvelAcheteur = rsNA.getInt("credit");
					}
				System.out.println(23);
				if(creditNouvelAcheteur < valeurEnchere) {
					cnx.rollback();
					DALException ex = new DALException("Problème dans le crédit disponible");
					throw (ex);
					}
				System.out.println(3);		
				
				stmtUpdateAncienAcheteur.setInt(1, (creditAncienAcheteur + valeurEnchere)); 
				stmtUpdateAncienAcheteur.setInt(2, idAncienAcheteur); 
				stmtUpdateAncienAcheteur.executeUpdate();
				System.out.println(10);
				stmtUpdateAncienAcheteur.setInt(1, (creditNouvelAcheteur - valeurEnchere)); 
				stmtUpdateAncienAcheteur.setInt(2, idAcheteur); 
				stmtUpdateAncienAcheteur.executeUpdate();
					System.out.println(20);					
				stmtUpdateMontantEnchere.setInt(1, valeurEnchere);
				stmtUpdateMontantEnchere.setInt(2, idAcheteur);
				Date date = Date.valueOf(LocalDate.now());
				stmtUpdateMontantEnchere.setDate(3, date);
				stmtUpdateMontantEnchere.setInt(4, idArticle);
				stmtUpdateMontantEnchere.executeUpdate();
				System.out.println(30);
	               
	   
               	cnx.commit();
			}catch(SQLException e) {
				cnx.rollback();
				DALException ex = new DALException("Probleme dans la mise à jour de l'enchère", e);
				throw (ex);	
			}
        }catch (SQLException e) {
            DALException ex = new DALException("problème d'accès à cet article", e);
            throw ex;}
    return idArticleMAJ;
	}
	
	


	
	
}
