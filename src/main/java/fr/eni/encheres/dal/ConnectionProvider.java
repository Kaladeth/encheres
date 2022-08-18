package fr.eni.encheres.dal;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionProvider {
	private static DataSource datasource;
	static {
		try {
			Context context = new InitialContext();
			datasource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx_encheres");

		} catch (NamingException e) {
		e.printStackTrace();
		}}

		public static Connection getConnection() throws SQLException, RuntimeException {
			return datasource.getConnection();
		}
}