package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EncheresDaoJdbcImpl implements EnchereDAO {
	private static final String SELECT_ALL ="select * from encheres";
	private static final String SELECT_BY_ARTICLE_LIBELLE ="select * from ENCHERES E, ARTICLES_VENDUS A, CATEGORIES C where E.no_article = A.no_article AND A.no_categorie = C.no_categorie AND nom_article like ? AND libelle = ?";
	private static final String SELECT_BY_ARTICLE ="select * from ENCHERES E, ARTICLES_VENDUS A, CATEGORIES C where E.no_article = A.no_article AND A.no_categorie = C.no_categorie AND nom_article like ?";


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
	
}
