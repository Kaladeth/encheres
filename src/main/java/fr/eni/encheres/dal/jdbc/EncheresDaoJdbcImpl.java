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
import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EncheresDaoJdbcImpl implements EnchereDAO {
	private static final String SELECT_ALL ="select * from encheres";
	private static final String SELECT_ARTICLE_BY_ID ="select * from encheres where no_article=?";
	private static final String UPDATE_CREDIT_ANCIEN_ACHETEUR = "UPDATE UTILISATEURS SET credit=? where no_utilisateur=?";
	private static final String SELECT_CREDIT_ANCIEN_ACHETEUR = "select * FROM UTILISATEURS u LEFT JOIN ENCHERES e on e.no_utilisateur = u.no_utilisateur WHERE e.no_article=?";
	private static final String SELECT_CREDIT_NOUVEL_ACHETEUR = "select * from utilisateurs where no_utilisateur=?";
	private static final String UPDATE_MONTANT_ENCHERE = "UPDATE ENCHERES SET montant_enchere=?, no_utilisateur=?, date_enchere=? WHERE no_article=?";
	private static final String UPDATE_CREDIT_NOUVEL_ACHETEUR ="UPDATE UTILISATEURS SET credit=? WHERE no_utilisateur=?";
	private static final String ADD_ENCHERE = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (?, ?, ?, ?)";
	
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
			PreparedStatement stmtReccupEnchere = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
            PreparedStatement stmtUpdateAncienAcheteur = cnx.prepareStatement(UPDATE_CREDIT_ANCIEN_ACHETEUR);
			PreparedStatement stmtUpdateMontantEnchere = cnx.prepareStatement(UPDATE_MONTANT_ENCHERE);
			PreparedStatement stmtUpdateNouvelAcheteur = cnx.prepareStatement(UPDATE_CREDIT_NOUVEL_ACHETEUR);
			PreparedStatement stmtSelectAncienAcheteur = cnx.prepareStatement(SELECT_CREDIT_ANCIEN_ACHETEUR);
			PreparedStatement stmtSelectNouvelAcheteur = cnx.prepareStatement(SELECT_CREDIT_NOUVEL_ACHETEUR);
			PreparedStatement stmtAddEnchere = cnx.prepareStatement(ADD_ENCHERE)) {
			try {
				cnx.setAutoCommit(false);
				int creditAncienAcheteur = -1;
				int creditNouvelAcheteur = 0;
				int idAncienAcheteur = 0;
				int valeurAncienneEnchere = 0;
				
				//réccupère les info s'il existe déjà une enchere et met à jour le crédit de l'ancien encherisseur 
				// s'il n'y en a pas encore, il en crée une nouvelle associée à l'article
				try{
					//reccupere montant de l'ancienne enchere
					stmtReccupEnchere.setInt(1, idArticle);
					ResultSet rs = stmtReccupEnchere.executeQuery();
					while(rs.next()) {
						valeurAncienneEnchere = rs.getInt("montant_enchere");
					}
					
					//reccupère ancien enchérisseur
					stmtSelectAncienAcheteur.setInt(1, idArticle);
					ResultSet rsAA = stmtSelectAncienAcheteur.executeQuery();
					while(rsAA.next()) {
						//reccupère crédit ancien enchérisseur
						creditAncienAcheteur = rsAA.getInt("credit");
						idAncienAcheteur = rsAA.getInt("no_utilisateur");
						
						if(idAncienAcheteur == 0 || creditAncienAcheteur == -1) {
							cnx.rollback();
							DALException ex = new DALException("Problème dans 'accès à l'ancien enchéreur");
							throw (ex);
						}	
						
						//met à jour le crédit de l'ancien encherisseur
						stmtUpdateAncienAcheteur.setInt(1, (creditAncienAcheteur + valeurAncienneEnchere)); 
						stmtUpdateAncienAcheteur.setInt(2, idAncienAcheteur); 
						stmtUpdateAncienAcheteur.executeUpdate();

									

						}
					
						//crréation de l'enchère si besoin			
				}catch (Exception e) {
					stmtAddEnchere.setInt(1, idAcheteur);
					stmtAddEnchere.setInt(2, idArticle);
					Date date = Date.valueOf(LocalDate.now());
					stmtAddEnchere.setDate(3, date);
					stmtAddEnchere.setInt(4, 0);
					stmtAddEnchere.executeUpdate();
					}
			//reccupère crédit nouveau enchérisseur
				stmtSelectNouvelAcheteur.setInt(1, idAcheteur);
				
				ResultSet rsNA = stmtSelectNouvelAcheteur.executeQuery();
				
				while(rsNA.next()) {
					creditNouvelAcheteur = rsNA.getInt("credit");
					}
				//vérif crédit
				if(creditNouvelAcheteur < valeurEnchere) {
					cnx.rollback();
					DALException ex = new DALException("Problème dans le crédit disponible");
					throw (ex);
					}
				//MAJ crédit nouveau enchérisseur
				stmtUpdateNouvelAcheteur.setInt(1, (creditNouvelAcheteur - valeurEnchere)); 
				stmtUpdateNouvelAcheteur.setInt(2, idAcheteur); 
				stmtUpdateNouvelAcheteur.executeUpdate();
				
				
				//MAJ enchère
				stmtUpdateMontantEnchere.setInt(1, valeurEnchere);
				stmtUpdateMontantEnchere.setInt(2, idAcheteur);
				Date date = Date.valueOf(LocalDate.now());
				stmtUpdateMontantEnchere.setDate(3, date);
				stmtUpdateMontantEnchere.setInt(4, idArticle);
				stmtUpdateMontantEnchere.executeUpdate();
	            
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
