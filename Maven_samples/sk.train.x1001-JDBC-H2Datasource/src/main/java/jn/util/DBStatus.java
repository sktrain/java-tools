package jn.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DBStatus {
	
	public static void showState(Connection con, String zeitpunkt) {
		try {
			System.err.println("********** " + zeitpunkt + " **************");
			ResultSet rs = con.createStatement().executeQuery("SELECT * FROM ACCOUNT");
			StringBuffer spalten = new StringBuffer("[");
			ResultSetMetaData metaData = rs.getMetaData();
			for (int i=1; i <= metaData.getColumnCount(); ++i) {
				spalten.append(metaData.getColumnName(i) + ", ");
			}
			spalten.deleteCharAt(spalten.length()-1);
			spalten.setCharAt(spalten.length()-1, ']');
			System.err.println(spalten);	
			while (rs.next()) {
				System.err.println("[" + rs.getString(1) + ", " + rs.getString(2) +"]");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
