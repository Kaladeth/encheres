package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import fr.eni.encheres.bll.BLLException;
import fr.eni.encheres.bo.ArticleVendu;



import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

public class ArticleVenduDaoJdbcImpl implements ArticleVenduDAO{
	
	String SELECT_BY_NAME_ATRICLE = "SELECT * FROM ARTICLES_VENDUS where nom_article LIKE ? ";
	
	String SELECT_BY_ID = "SELECT a.no_article, a.nom_article, a.description, c.libelle, e.montant_enchere, ut.no_utilisateur, ut.pseudo, r.rue, r.code_postal, r.ville, a.prix_initial, a.date_debut_enchere, a.date_fin_enchere, ut.no_utilisateur, u.pseudo, a.etat_vente, a.no_utilisateur "
			+ "  FROM ARTICLES_VENDUS a LEFT JOIN encheres e on e.no_article = a.no_article LEFT JOIN utilisateurs u on u.no_utilisateur = a.no_utilisateur "
			+ "  LEFT JOIN utilisateurs ut on ut.no_utilisateur = e.no_utilisateur  LEFT JOIN CATEGORIES c on c.no_categorie = a.no_categorie "
			+ "  LEFT JOIN RETRAITS r on r.no_article = a.no_article "
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
                article.setNoArticle(rs.getInt(1));
                article.setNomArticle(rs.getString(2));
                article.setDescription(rs.getString(3));
                //cration catégorie
                categorie.setLibelle(rs.getString(4));
                //création enchere
                enchere.setMontant_enchere(rs.getInt(5));
                acheteur.setNoUtilisateur(rs.getInt(6));
                acheteur.setPseudo(rs.getString(7));
                
                //création retrait
                retrait.setRue(rs.getString("rue"));
                retrait.setCode_postale(rs.getString(9));
                retrait.setVille(rs.getString(10));
                article.setMiseAPrix(rs.getInt(11));
                article.setDateDebutEncheres(LocalDateTime.of((rs.getDate(12).toLocalDate()), rs.getTime(12).toLocalTime()));
                article.setDateFinEncheres(LocalDateTime.of((rs.getDate(13).toLocalDate()), rs.getTime(13).toLocalTime())); 
                vendeur.setNoUtilisateur(rs.getInt(14));
                vendeur.setPseudo(rs.getString(15));
                article.setEtatVente(rs.getString(16));
                //n° de vendeur ajouté à l'utilisateur
                article.setUtilisateur(rs.getInt(17));
            }
            article.setCategorie(categorie);
            enchere.setAcheteur(acheteur);
            article.setEncheres(enchere);
            article.setRetrait(retrait);
            article.setVendeur(vendeur);
            }catch (SQLException e) {
                DALException ex = new DALException("problème d'accès à cet article", e);
                throw ex;
            }
        
        return article;
    }
	
	
	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	// METHODE INSERT
//	String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_enchere, date_fin_enchere, prix_initial,"
//			+ " prix_vente, no_utilisateur, no_categorie, etat_vente)"
//			+ "values (?,?,?,?,?,?,?,?,?)";
	public void insert(ArticleVendu aInserer) throws DALException {
		if (aInserer == null) {
			DALException e = new DALException("L'article à insérer ne peut être vide");
			throw e;
		}
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			try(PreparedStatement stmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)){
				cnx.setAutoCommit(false);
				stmt.setString(1, aInserer.getNomArticle());
				stmt.setString(2, aInserer.getDescription());
				stmt.setTimestamp(3, java.sql.Timestamp.valueOf(aInserer.getDateDebutEncheres()));
				stmt.setTimestamp(4, java.sql.Timestamp.valueOf(aInserer.getDateFinEncheres()));
				stmt.setInt(5, aInserer.getMiseAPrix());
				stmt.setInt(6, aInserer.getMiseAPrix());
				stmt.setInt(7, aInserer.getUtilisateur());
				stmt.setInt(8, aInserer.getCategorie().getNoCategorie());
				stmt.setString(9, "CR");
				stmt.executeUpdate();
				
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					aInserer.setNoArticle(rs.getInt(1));
				}
				
				cnx.commit();
			} catch (SQLException e) {
				DALException ex = new DALException("Problème lors de l'insertion d'un article", e);
				cnx.rollback();
				throw ex;
				
			}
		} catch (Exception e) {
			DALException ex = new DALException("Probleme d'ajout d'un article", e);
			throw (ex);			
		}
	
		
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
