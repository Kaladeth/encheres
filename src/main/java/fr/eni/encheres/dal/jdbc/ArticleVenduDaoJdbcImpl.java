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
	
	String SELECT_BY_ID = "SELECT a.no_article, a.nom_article, a.description, c.libelle, e.montant_enchere, ut.no_utilisateur, ut.pseudo, r.rue, r.code_postal, r.ville, a.prix_initial, a.date_debut_enchere, a.date_fin_enchere, ut.no_utilisateur, u.pseudo, a.etat_vente "
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
        //System.out.println("coucou");
        ArticleVendu article = null;
        //System.out.println("coucou1");
        Categorie categorie = new Categorie();
        //System.out.println("coucou2");
        Retrait retrait = new Retrait();
        //System.out.println("coucou3");
        Utilisateur acheteur  = new Utilisateur();
        //System.out.println("coucou4");
        Utilisateur vendeur  = new Utilisateur();
        //System.out.println("coucou5");
        Enchere enchere = new Enchere();
        //System.out.println("coucou6");
        try(Connection cnx = ConnectionProvider.getConnection();
            PreparedStatement stmt = cnx.prepareStatement(SELECT_BY_ID)) {
        	//System.out.println("coucou7");
            stmt.setInt(1, id);   
            //System.out.println("coucou8");
            ResultSet rs =stmt.executeQuery();
            //System.out.println("coucou9");
            while(rs.next()) {
                article = new ArticleVendu();
                article.setNoArticle(rs.getInt(1));
                //System.out.println("1 - " +rs.getInt(1));
                article.setNomArticle(rs.getString(2));
                //System.out.println("2 - " + rs.getString(2));
                article.setDescription(rs.getString(3));
                //System.out.println("3 - " +rs.getString(3));
                //cration catégorie
                categorie.setLibelle(rs.getString(4));
               //System.out.println("4 - "+ rs.getString(4));
                //création enchere
                enchere.setMontant_enchere(rs.getInt(5));
               //System.out.println("5 - "+rs.getInt(5));
                acheteur.setNoUtilisateur(rs.getInt(6));
                //System.out.println("6 - "+rs.getInt(6));
                acheteur.setPseudo(rs.getString(7));
                //System.out.println("7 - "+ rs.getString(7));
                //création retrait
                retrait.setRue(rs.getString("rue"));
                //System.out.println("8 - "+rs.getString("rue"));
                retrait.setCode_postale(rs.getString(9));
                //System.out.println("9 - "+rs.getString(9));
                retrait.setVille(rs.getString(10));
                //System.out.println(" 10 - "+rs.getString(10));
                article.setMiseAPrix(rs.getInt(11));
               //System.out.println("11 - " + rs.getInt(11));
                article.setDateDebutEncheres(LocalDateTime.of((rs.getDate(12).toLocalDate()), rs.getTime(12).toLocalTime()));
                article.setDateFinEncheres(LocalDateTime.of((rs.getDate(13).toLocalDate()), rs.getTime(13).toLocalTime())); 
                vendeur.setNoUtilisateur(rs.getInt(14));
                //System.out.println("13 - "+ rs.getInt(14));
                vendeur.setPseudo(rs.getString(15));
                //System.out.println("14 - "+rs.getString(15));
                article.setEtatVente(rs.getString(16));
                //System.out.println("15 - " +rs.getString(16));
            }
            //System.out.println("coucou10");
            article.setCategorie(categorie);
           // System.out.println("categorie - " + categorie);
            enchere.setAcheteur(acheteur);
            article.setEncheres(enchere);
            article.setRetrait(retrait);
            //System.out.println("retrait - " + retrait);
            article.setVendeur(vendeur);
            //System.out.println("vendeur - " + vendeur);
            //System.out.println("article - " + article);
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
