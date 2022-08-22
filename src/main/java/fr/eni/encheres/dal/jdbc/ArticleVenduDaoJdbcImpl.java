package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

public class ArticleVenduDaoJdbcImpl implements ArticleVenduDAO{
	
//	String SELECT_BY_FILTER = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article=? OR no_categorie=?";
	
	String SELECT_BY_ID = "SELECT a.no_article, a.nom_article, a.description, c.libelle, e.montant_enchere, ut.no_utilisateur, ut.pseudo, r.rue, r.code_postal, r.ville, a.prix_initial, a.date_debut_enchere, a.date_fin_enchere, ut.no_utilisateur, u.pseudo, a.etat_vente "
			+ "  FROM ARTICLES_VENDUS a LEFT JOIN encheres e on e.no_article = a.no_article LEFT JOIN utilisateurs u on u.no_utilisateur = a.no_utilisateur "
			+ "  LEFT JOIN utilisateurs ut on ut.no_utilisateur = e.no_utilisateur  LEFT JOIN CATEGORIES c on c.no_categorie = a.no_categorie "
			+ "LEFT JOIN RETRAITS r on r.no_article = a.no_article "
			+ "  WHERE a.no_article=?";
	String SELECT_CATEGORIE_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie=?";
	String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial,"
			+ " prix_vente, no_utilisateur, no_categorie, etat_vente)"
			+ "values (?,?,?,?,?,?,?,?,?)";
	
	@Override
	// METHODE SELECT BY ID
	public ArticleVendu selectById(int id) throws DALException {
		ArticleVendu article = null;
		Categorie categorie = new Categorie();
		Retrait retrait = new Retrait();
		Utilisateur acheteur  = new Utilisateur();
		Utilisateur vendeur  = new Utilisateur();
		Enchere enchere = new Enchere();
		try(Connection cnx = ConnectionProvider.getConnection();
			PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)) {
			stmt.setInt(1, id);		
			ResultSet rs =stmt.executeQuery();
			while(rs.next()) {
				
				article = new ArticleVendu();
				categorie = new Categorie();
				retrait = new Retrait();
				acheteur = new Utilisateur();
				vendeur = new Utilisateur();
				enchere = new Enchere();
				
				article.setNoArticle(rs.getInt(1));
				System.out.println("1 - " +rs.getInt(1));
				article.setNomArticle(rs.getString(2));
				System.out.println("2 - " + rs.getString(2));
				article.setDescription(rs.getString(3));
				System.out.println("3 - " +rs.getString(3));
								
				//cration catégorie
				categorie.setLibelle(rs.getString(4));
				System.out.println("4 - "+ rs.getString(4));
				
				//création enchere
			
				enchere.setMontant_enchere(rs.getInt(5));
				System.out.println("5 - "+rs.getInt(5));
				acheteur.setNoUtilisateur(rs.getInt(6));
				System.out.println("6 - "+rs.getInt(6));
				acheteur.setPseudo(rs.getString(7));
				System.out.println("7 - "+ rs.getString(7));
				
				//création retrait
				retrait.setRue(rs.getString("rue"));
				System.out.println("8 - "+rs.getString("rue"));
				retrait.setCode_postale(rs.getString(9));
				System.out.println("9 - "+rs.getString(9));
				retrait.setVille(rs.getString(10));
				System.out.println(" 10 - "+rs.getString(10));
			
				article.setMiseAPrix(rs.getInt(11));
				System.out.println("11 - " + rs.getInt(11));
				
				article.setDateDebutEncheres(LocalDateTime.of((rs.getDate(12).toLocalDate()), rs.getTime(12).toLocalTime()));
				article.setDateFinEncheres(LocalDateTime.of((rs.getDate(13).toLocalDate()), rs.getTime(13).toLocalTime()));	

				vendeur.setNoUtilisateur(rs.getInt(14));
				System.out.println("13 - "+ rs.getInt(14));
				vendeur.setPseudo(rs.getString(15));
				System.out.println("14 - "+rs.getString(15));
				article.setEtatVente(rs.getString(16));
				System.out.println("15 - " +rs.getString(16));
				
			}		
			article.setCategorie(categorie);
			System.out.println("categorie - " + categorie);
			enchere.setAcheteur(acheteur);
			article.setEncheres(enchere);
			article.setRetrait(retrait);
			System.out.println("retrait - " + retrait);
			article.setVendeur(vendeur);
			System.out.println("vendeur - " + vendeur);
			System.out.println("article - " + article);
			
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
