package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


/**
 * 
 * @author Daniel Santidrian Alonso
 *
 */
public class DatabaseAdministration {
	
	/**
	 * Global variables
	 */
	private Connection con = null;

	
	/**
	 * Constructor of the class that connects the database.
	 */
	public DatabaseAdministration() {
		
		MysqlDataSource ds = new MysqlDataSource();

		ds.setUser("root");
		ds.setPassword("1234");
		ds.setDatabaseName("ubuassistant");
		ds.setURL("jdbc:mysql://localhost/ubuassistant");
		
		/*ds.setUrl("jdbc:mysql://sql11.freesqldatabase.com:3306/sql11162792");
		ds.setUser("sql11162792");
		ds.setPassword("5v53hZNDmT");*/

		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos.");
		}
	}
	
	/**
	 * Method that adds a case to the database
	 * @param lista list of keyWords of the case
	 * @param categoria category of the case
	 * @param respuesta answer for the case
	 */
	public void addCase(List<String> lista, String categoria, String respuesta){
		
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		
		try{
			
		
			pst = con.prepareStatement("INSERT INTO casedescription "
											+ "(keyWord1,keyWord2,keyWord3,keyWord4,keyWord5, categoria) "
											+ " VALUES (?,?,?,?,?,?)");
			
			for(int i=0;i<lista.size();i++)
				pst.setString(i+1, removeEspecialChar(lista.get(i)));
			
			for(int i=lista.size(); i<5;i++)
				pst.setNull(i+1, java.sql.Types.VARCHAR);
			
			pst.setString(6, removeEspecialChar(categoria));
			
			pst.executeUpdate();
			
			pst2 = con.prepareStatement("INSERT INTO casesolution "
											+ "(answer) "
											+ " VALUES (?)");
			pst2.setString(1, respuesta);
			pst2.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst!=null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pst2!=null)
					pst2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Method that updates a case to the database
	 * @param id identifier of the case
	 * @param keyWords array of keyWords of the case
	 * @param categoria category of the case
	 * @param respuesta answer for the case
	 */
	public void editCase(String id, String[] keyWords, String categoria, String respuesta){
		
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		
		try{
			
			pst = con.prepareStatement("UPDATE casedescription SET keyWord1=?, keyWord2=?, keyWord3=?"
											+ ", keyWord4=?, keyWord5=?, categoria=? WHERE id=?");
			pst.setInt(7, Integer.parseInt(id));
			
			for(int i=0; i<keyWords.length; i++){
				if(keyWords[i]!=null)
					pst.setString(i+1, removeEspecialChar(keyWords[i]));
				else
					pst.setNull(i+1, java.sql.Types.VARCHAR);
				
			}
			
			pst.setString(6, removeEspecialChar(categoria));
			
			pst.executeUpdate();
			
			pst2 = con.prepareStatement("UPDATE casesolution SET answer=? WHERE id=?");
			pst2.setString(1, respuesta);
			pst2.setInt(2, Integer.parseInt(id));
			
			pst2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			try {
				if(pst!=null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pst2!=null)
					pst2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	/**
	 * Method that removes a case to the database
	 * @param id identifier of the case
	 */
	public void removeCase(String id){
		
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		
		try{
			pst = con.prepareStatement("DELETE FROM casedescription WHERE id=?");
			pst.setInt(1, Integer.parseInt(id));
			
			pst.executeUpdate();
			
			pst2 = con.prepareStatement("DELETE FROM casesolution WHERE id=?");
			pst2.setInt(1, Integer.parseInt(id));
			
			pst2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst!=null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pst2!=null)
					pst2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	/**
	 * Method that adds a case to the database from the learn table
	 * @param palabra suggested word
	 * @param respuesta suggested answer
	 */
	public void executeLearn(String palabra, String respuesta){
		
		PreparedStatement pst0 = null;
		PreparedStatement pst = null;
		PreparedStatement pst2 = null;
		
		try{
			pst0 = con.prepareStatement("DELETE FROM aprendizaje WHERE palabra1=? AND palabra2=?");
			pst0.setString(1, palabra);
			pst0.setString(2, respuesta);
			pst0.executeUpdate();
			
			String newPalabra=removeEspecialChar(palabra);
			
			pst = con.prepareStatement("INSERT INTO casedescription "
											+ "(keyWord1,keyWord2,keyWord3,keyWord4,keyWord5, categoria) "
											+ " VALUES (?,?,?,?,?,?)");
			pst.setString(1, newPalabra);
			for(int i=2; i<=5;i++)
				pst.setNull(i, java.sql.Types.VARCHAR);
			
			pst.setString(6, getCategoria(respuesta));
			pst.executeUpdate();
			
			pst2 = con.prepareStatement("INSERT INTO casesolution "
											+ "(answer) "
											+ " VALUES (?)");
			pst2.setString(1, respuesta);
			pst2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			try {
				if(pst0!=null)
					pst0.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pst!=null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pst2!=null)
					pst2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	/**
	 * Method that ignores a suggest learn from the table
	 * @param palabra suggested word
	 * @param respuesta suggested answer
	 */
	public void ignoreLearn(String palabra, String respuesta){
		
		PreparedStatement pst = null;
		
		try{
			
			pst = con.prepareStatement("DELETE FROM aprendizaje WHERE palabra1=? AND palabra2=?");
			pst.setString(1, palabra);
			pst.setString(2, respuesta);
			pst.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			try {
				if(pst!=null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	/**
	 * Method that clears all entries of the logger table.
	 */
	public void clearLog(){
		
		Statement stmt = null;
		
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("DELETE FROM logger");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(stmt!=null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * Function that returns the category of a response.
	 * @param respuesta response from which we want to know its category.
	 * @return categoria category of the response.
	 */
	private String getCategoria(String respuesta){
		
		int id = 0;
		String categoria = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		
		try{
			pst = con.prepareStatement("SELECT * FROM casesolution WHERE answer=?");
			pst.setString(1, respuesta);
			rs = pst.executeQuery();
			while (rs.next()) {
				id=rs.getInt("id");
			}	
			
			pst1 = con.prepareStatement("SELECT * FROM casedescription WHERE id=?");
			pst1.setInt(1, id);
			rs1 = pst1.executeQuery();
			while (rs1.next()) {
				categoria=rs1.getString("categoria");
			}	
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			try {
				if(pst!=null)
					pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(pst1!=null)
					pst1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(rs1!=null)
					rs1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return categoria;
		
	}
	
	/**
	 * Function that returns a String without special characters
	 * @param input String with special characters
	 * @return output String without special characters
	 */
	private String removeEspecialChar(String input) {
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ´";
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC ";
	    
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }
	    return output;
	}
}
