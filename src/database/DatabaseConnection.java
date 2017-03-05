package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


/**
 * 
 * @author Daniel Santidrian Alonso
 *
 */
public class DatabaseConnection {
	
	private static Connection con = null;
    static List<String> sentenceList = new ArrayList<String>();
    List<String> saluteList = new ArrayList<String>();
	List<String> saluteResponseList = new ArrayList<String>();
	
	/**
	 * Constructor of the class that connects the database
	 */
	public DatabaseConnection() {
		MysqlDataSource ds = new MysqlDataSource();

		ds.setUser("root");
		ds.setPassword("1234");
		ds.setDatabaseName("ubuassistant");
		ds.setURL("jdbc:mysql://localhost/ubuassistant");

		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos.");
		}
		
		//Creation of the list containing the sentences
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM frases");
			while (rs.next()) {
				sentenceList.add(rs.getString("frase"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Creation of the list containing the salutes
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM saludos");
			while (rs.next()) {
				saluteList.add(rs.getString("saludo"));
				saluteResponseList.add(rs.getString("respuesta"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Function that returns the sentenceList
	 * @return sentenceList List of the sentences
	 */
	public List<String> getSentenceList(){
		return sentenceList;
	}
	
	/**
	 * Function that returns the saluteList
	 * @return saluteList List of salutes
	 */
	public List<String> getSaluteList(){
		return saluteList;
	}
	
	/**
	 * Function that returns the saluteResponseList
	 * @return saluteResponseList List of responses to salutes
	 */
	public List<String> getSaluteResponseList(){
		return saluteResponseList;
	}

}
