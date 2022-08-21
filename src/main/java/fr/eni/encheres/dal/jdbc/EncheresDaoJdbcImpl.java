package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.bo.Enchere;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;
import fr.eni.encheres.dal.EnchereDAO;

public class EncheresDaoJdbcImpl implements EnchereDAO {
	private static final String SELECT_ALL ="select * from encheres";
	

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
	
}
