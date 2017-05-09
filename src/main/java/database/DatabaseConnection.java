package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


/**
 * 
 * @author Daniel Santidrian Alonso
 *
 */
public class DatabaseConnection {
	
	/**
	 * Global variables
	 */
	private static Connection con = null;
	private static List<String> sentenceList = new ArrayList<String>();
	private static List<String> saluteList = new ArrayList<String>();
	private static List<String> saluteResponseList = new ArrayList<String>();
	private long userID;
	
	/**
	 * Constructor of the class that connects the database.
	 * @param userID unique identifier for a user.
	 */
	public DatabaseConnection(String userID) {
		
		this.userID=Long.parseLong(userID.toString());
		
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
		
		createLists();
	}
	
	/**
	 * Method that reads the tables of the database and creates the sentenceList, 
	 * saluteList and saluteResponseList.
	 */
	private void createLists(){
		
		//Creation of the list containing the sentences
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM frases");
			while (rs.next()) {
				sentenceList.add(rs.getString("frase"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//Creation of the list containing the salutes
		try {
			Statement stmt = con.createStatement();
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
	 * Method that store in a table of the database the words that the users
	 * select when there is not an answer available
	 * @param p1 the word that gets the best similarity of the text input by the user
	 * @param p2 the answer that will be associated to the first word
	 */
	public void learnCases(String p1, String p2){
		
		boolean flag = false;
		String palabra2=" ";
		
		try {
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM aprendizaje");
			while (rs.next()) {
				if(p1.equals(rs.getString("palabra1"))){
					flag=true;
					palabra2=rs.getString("palabra2");
				}
			}	
			
			if(!flag || !palabra2.equals(p2)){
				
				PreparedStatement pst = con.prepareStatement(
						"INSERT INTO aprendizaje (userid, palabra1, palabra2) VALUES (?, ?, ?)");
				pst.setLong(1, userID);
				pst.setString(2, p1);
				pst.setString(3, p2);
				
				pst.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method that increases the field numBusquedas in the database
	 * @param palabras the words that are searched
	 * @param respuesta response for that set of words
	 */
	public void aumentarNumBusquedas(LinkedHashSet<String> palabras, String respuesta){
		
		boolean flag=false;
		int palabraId = 0;
		int num_busquedas=0;
		String categoria=null;
		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Object[] p = palabras.toArray();
		
		try{
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM estadisticas");
			while (rs.next()) {
				
				if(p.length==1){
					if(p[0].equals(rs.getString("keyWord1")) && rs.getString("keyWord2")==null && 
							rs.getString("keyWord3")==null && rs.getString("keyWord4")==null &&  
							rs.getString("keyWord5")==null && userID==rs.getLong("userid")){
						
						flag=true;
						palabraId=rs.getInt("id");
						num_busquedas=rs.getInt("num_busquedas");
					}
				}else{
					
					for(int i=0; i<p.length;i++){
						
						if((p[i].equals(rs.getString("keyWord1")) || p[i].equals(rs.getString("keyWord2")) || 
							p[i].equals(rs.getString("keyWord3")) || p[i].equals(rs.getString("keyWord4")) ||  
							p[i].equals(rs.getString("keyWord5"))) && userID==rs.getLong("userid")){

							flag=true;
							palabraId=rs.getInt("id");
							num_busquedas=rs.getInt("num_busquedas");
						}else{

							flag=false;
							break;
						}
					}
					if(flag==true)
						break;
				}
				
				
			}	
			
			if(flag){
				
				PreparedStatement pst = con.prepareStatement("UPDATE estadisticas SET num_busquedas = ?, fecha=? WHERE id=?");

				pst.setInt(1, (num_busquedas+=1));
				pst.setString(2, sdf.format(new Date()));
				pst.setInt(3, palabraId);
				pst.executeUpdate();
				
			}else{
				
				categoria=getCategoria(respuesta);
				
				PreparedStatement pst = 
						con.prepareStatement("INSERT INTO estadisticas "
												+ "(keyWord1, keyWord2, keyWord3, keyWord4, keyWord5, categoria, num_busquedas, num_votos, valoracion_total,"
												+ " fecha, respuesta, userid) "
												+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
				
				for(int i=0; i<p.length; i++){
					pst.setString(i+1, (String) p[i]);
				}
				
				for(int i=p.length+1;i<6;i++)
					pst.setNull(i, java.sql.Types.VARCHAR);
				
				pst.setString(6, categoria);
				pst.setInt(7, 1);
				pst.setInt(8, 0);
				pst.setInt(9, 0);
				pst.setString(10, sdf.format(new Date()));
				pst.setString(11, respuesta);
				pst.setLong(12, userID);
				pst.executeUpdate();
				
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
	}
	
	/**
	 * Method that saves the rating of the user to a specific answer into the database
	 * @param palabras the words that are searched
	 * @param vote the rating to the answer for the words
	 */
	public void saveVote(LinkedHashSet<String> palabras,int vote){
		
		int palabraId = 0;
		int num_votos=0;
		int valoracion_total=0;
		Object[] p = palabras.toArray();

			
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM estadisticas");
			while (rs.next()) {
				
				if(p.length==1){
					if(p[0].equals(rs.getString("keyWord1")) && rs.getString("keyWord2")==null && 
							rs.getString("keyWord3")==null && rs.getString("keyWord4")==null &&  
							rs.getString("keyWord5")==null && userID==rs.getLong("userid")){
						
						palabraId=rs.getInt("id");
						num_votos=rs.getInt("num_votos");
						valoracion_total=rs.getInt("valoracion_total");
					}
				}else{
					
					for(int i=0; i<p.length;i++){
						if((p[i].equals(rs.getString("keyWord1")) || p[i].equals(rs.getString("keyWord2")) || 
							p[i].equals(rs.getString("keyWord3")) || p[i].equals(rs.getString("keyWord4")) ||  
							p[i].equals(rs.getString("keyWord5"))) && userID==rs.getLong("userid")){
							
							palabraId=rs.getInt("id");
							num_votos=rs.getInt("num_votos");
							valoracion_total=rs.getInt("valoracion_total");

						}else
							break;
					}
				}
			}
			
			
			PreparedStatement pst = con.prepareStatement("UPDATE estadisticas SET num_votos=?, valoracion_total=? WHERE id=?");
			pst.setInt(1, (num_votos+=1));
			pst.setInt(2, (valoracion_total+=vote));
			pst.setInt(3, palabraId);
			pst.executeUpdate();
					
		} catch (SQLException e) {
			e.printStackTrace();
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
		
		try{
			PreparedStatement pst = con.prepareStatement("SELECT * FROM casesolution WHERE answer=?");
			pst.setString(1, respuesta);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				id=rs.getInt("id");
			}	
			
			PreparedStatement pst1 = con.prepareStatement("SELECT * FROM casedescription WHERE id=?");
			pst1.setInt(1, id);
			ResultSet rs1 = pst1.executeQuery();
			while (rs1.next()) {
				categoria=rs1.getString("categoria");
			}	
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return categoria;
		
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
