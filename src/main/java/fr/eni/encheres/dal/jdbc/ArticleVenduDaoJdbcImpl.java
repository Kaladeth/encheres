package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

public class ArticleVenduDaoJdbcImpl implements ArticleVenduDAO{
<<<<<<< HEAD
	String SELECT_BY_ID = "SELECT * FROM ARTICLES_VENDUS WHERE no_article=?";
	String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie=?";
	String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial,"
			+ " prix_vente, no_utilisateur, no_categorie, etat_vente)"
			+ "values (?,?,?,?,?,?,?,?,?)";
=======
>>>>>>> branch 'main' of https://github.com/Kaladeth/encheres.git
	
//	String SELECT_BY_FILTER = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article=? OR no_categorie=?";
	
	String SELECT_BY_ID = "SELECT a.nom_article ,a.description ,c.libelle ,e.montant_enchere ,u2.pseudo as acheteur\r\n"
			+ "	  ,r.rue ,r.code_postal ,r.ville ,prix_initial ,date_fin_enchere ,u.pseudo as vendeur ,image\r\n"
			+ "  FROM ARTICLES_VENDUS a\r\n"
			+ "  LEFT JOIN encheres e on e.no_article = a.no_article\r\n"
			+ "  LEFT JOIN utilisateurs u on u.no_utilisateur = a.no_utilisateur\r\n"
			+ "  LEFT JOIN utilisateurs u2 on u.no_utilisateur = e.no_utilisateur\r\n"
			+ "  LEFT JOIN CATEGORIES c on c.no_categorie = a.no_categorie\r\n"
			+ "  LEFT JOIN RETRAITS r on r.no_article = a.no_article\\r\\n"
			+ "  WHERE a.no_article=?";
	String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie=?";


	@Override
	// METHODE SELECT BY ID
	public ArticleVendu selectById(int id) throws DALException {
		ArticleVendu article = null;
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)) {
			stmt.setInt(1, id);			
			ResultSet rs =stmt.executeQuery();
			while(rs.next()) {
				article = new ArticleVendu();
				article.setNoArticle(rs.getInt("no_article"));
				article.setNomArticle(rs.getString("nom_article"));
				article.setDescription(rs.getString("description"));
				article.setDateDebutEncheres(LocalDateTime.of((rs.getDate("date_debut_enchere").toLocalDate()), rs.getTime("date_debut_enchere").toLocalTime()));
				article.setDateFinEncheres(LocalDateTime.of((rs.getDate("date_fin_enchere").toLocalDate()), rs.getTime("date_fin_enchere").toLocalTime()));
				article.setMiseAPrix(rs.getInt("prix_initial"));
				article.setPrixVente(rs.getInt("prix_vente"));
				article.setUtilisateur(rs.getInt("no_utilisateur"));
				article.setEtatVente(rs.getString("etat_vente"));
				//article.setCategorie(rs.getInt("no_categorie"));
			}		
			
            }catch (SQLException e) {
            	DALException ex = new DALException("problème d'accès à cet article", e);
            	throw ex;}
		return article;
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	// METHODE INSERT
	public void insert(ArticleVendu element) throws DALException {
		
		
	}

	@Override
	public void update(ArticleVendu element) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int element) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArticleVendu AfficherParCategorie(String categorie) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
