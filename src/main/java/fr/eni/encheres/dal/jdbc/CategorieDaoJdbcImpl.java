package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.valves.rewrite.InternalRewriteMap.LowerCase;

import fr.eni.encheres.bo.Categorie;

import fr.eni.encheres.dal.CategorieDAO;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.DALException;

public class CategorieDaoJdbcImpl implements CategorieDAO  {
	
	private static final String SELECT_ALL ="select * from categories";
	private static final String SELECT_ID_BY_LIBELLE ="select no_categorie from categories where libelle = ?";

	@Override
	public Categorie selectById(int id) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Categorie element) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Categorie element) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int element) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Categorie> selectAll() throws DALException {
	
				List<Categorie> listCategories = new ArrayList<Categorie>();
				try (Connection cnx = ConnectionProvider.getConnection();){
					
					Statement stmt = cnx.createStatement();
					ResultSet rs = stmt.executeQuery(SELECT_ALL);
					while(rs.next()) {
					int noCategorie = rs.getInt("no_categorie");
					String libelle  = rs.getString("libelle");
					Categorie categorie = new Categorie(noCategorie ,libelle);
					listCategories.add(categorie);
						
					}	
					
				}catch (SQLException e) {
					DALException ex = new DALException("Probleme d'afficher listes Categorie", e);
					throw (ex);				
				}
				
				return listCategories;	
	}
	
}
