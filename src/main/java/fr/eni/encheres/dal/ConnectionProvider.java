package fr.eni.encheres.dal;
import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	private static DataSource datasource;
	static {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx_msea");

		} catch (NamingException e) {
		e.printStackTrace();
		}}

		public static Connection getConnection() throws DALException {
			try{return datasource.getConnection();
			}catch(Exception e) {
				DALException ex = new DALException("Connexion impossible à la base de données", e);
				throw ex;
			}
		}
}