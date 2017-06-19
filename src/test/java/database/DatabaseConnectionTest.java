package database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 
 * @author Daniel Santidrian Alonso
 *
 */
public class DatabaseConnectionTest {
	
	DatabaseConnection db;
	Connection con;
	String userID;
	
	@Before
	public void before(){
		
		DateFormat formatForId = new SimpleDateFormat("yyMMddHHmmssSSS");
		userID=formatForId.format(new Date()); 
		
		db = new DatabaseConnection(userID);
		
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
		
	}

	@Test
	public void getListsTest() {
		
		List<String> salutes = new ArrayList<String>();
		
		salutes.add("Hola");
		salutes.add("Buenos dias");
		salutes.add("Buenas tardes");
		salutes.add("Buenas noches");
		salutes.add("Buenas");
		salutes.add("Adios");
		salutes.add("Hasta luego");
		salutes.add("Ciao");
		salutes.add("Eres una pesada");
		salutes.add("Callate");
	
		List<String> responses = new ArrayList<String>();

		responses.add("Hola, estoy preparada para responder, adelánte");
		responses.add("Buenos días, ponme a prueba con tus preguntas");
		responses.add("Buenas tardes, ¿tienes alguna duda? No dudes en preguntarme");
		responses.add("Buenas noches, ¿qué te apetece preguntar?");
		responses.add("Muy buenas serán si te contesto correctamente");
		responses.add("¡Adiós!, espero haberte servido de ayuda");
		responses.add("Hasta luego, espero que volvamos a vernos");
		responses.add("¡Arrivederci!");
		responses.add("No te pongas así, seguro que podemos tener una conversación productiva");
		responses.add("Lo siento, solo intentaba ayudar");
		
		assertTrue(db.getSaluteList().containsAll(salutes));
		assertTrue(db.getSaluteResponseList().containsAll(responses));
		
		List<String> sentences = new ArrayList<String>();
		
		sentences.add("Hola soy UBUassistant, ¿en qué puedo ayudarte?");
		sentences.add("Muy buenas, me llamo UBUassistant y estoy lista para ayudarte ¡adelante!");
		sentences.add("Hola, si necesitas ayuda aquí me tienes, aquí tienes a UBUassistant");
		sentences.add("Bienvenido, ante cualquier duda de tu universidad UBUassistant al rescate");
		sentences.add("UBUassistant te da la bienvenida, ¿Empezamos?, pregunta cualquier duda sobre la UBU");
		sentences.add("Tal vez esto te ayude");
		sentences.add("Prueba con la siguiente información");
		sentences.add("Espero que esto te ayude");
		sentences.add("Esto es lo que he encontrado al respecto");
		
		
		assertTrue(db.getSentenceList().containsAll(sentences));

	}
	
	@Test
	public void learnTest() {
		
		db.learnCases("inventada","respuesta inventada");
		
		try{
			String palabra2="";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM aprendizaje");
			
			while (rs.next()) {
				if(rs.getString("palabra1").equals("inventada")){
					palabra2=rs.getString("palabra2");
				}
			}	
			
			assertEquals(palabra2, "respuesta inventada");
			
			stmt.executeUpdate("DELETE FROM aprendizaje WHERE palabra1='inventada'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void learnMultipleTest() {
		
		db.learnCases("inventada","respuesta inventada1");
		db.learnCases("inventada","respuesta inventada2");
		
		try{
			List<String> palabra2= new ArrayList<String>();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM aprendizaje");
			
			while (rs.next()) {
				if(rs.getString("palabra1").equals("inventada")){
					palabra2.add(rs.getString("palabra2"));
				}
			}	
			
			List<String> temp = new ArrayList<String>();
			temp.add("respuesta inventada1");
			temp.add("respuesta inventada2");
			
			assertTrue(palabra2.containsAll(temp));
			
			stmt.executeUpdate("DELETE FROM aprendizaje WHERE palabra1='inventada'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void aumentarNumBusquedaTest() {
		
		LinkedHashSet<String> temp = new LinkedHashSet<String>();
		temp.add("inventada1");
		temp.add("inventada2");
		temp.add("inventada3");
		
		LinkedHashSet<String> temp2 = new LinkedHashSet<String>();
		temp2.add("inventada1");
		temp2.add("inventada2");
		
		db.aumentarNumBusquedas(temp, "respuesta inventada");
		db.aumentarNumBusquedas(temp2, "respuesta inventada");
		db.aumentarNumBusquedas(temp2, "respuesta inventada");
		
		try{
			
			List<Integer> num = new ArrayList<Integer>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM logger");
			
			while (rs.next()) {
				if(rs.getString("keyWord1").equals("inventada1")){
					num.add(rs.getInt("num_busquedas"));
				}
			}	
			

			assertSame(num.get(0),1);
			assertSame(num.get(1),2);
			
			stmt.executeUpdate("DELETE FROM logger WHERE keyWord1='inventada1'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void saveVoteTest() {
		
		LinkedHashSet<String> temp = new LinkedHashSet<String>();
		temp.add("inventada1");
		temp.add("inventada2");
		temp.add("inventada3");
		
		LinkedHashSet<String> temp2 = new LinkedHashSet<String>();
		temp2.add("inventada1");
		temp2.add("inventada2");
		
		db.aumentarNumBusquedas(temp, "respuesta inventada");
		db.aumentarNumBusquedas(temp2, "respuesta inventada");
		db.aumentarNumBusquedas(temp2, "respuesta inventada");
		
		db.saveVote(temp, 5);
		db.saveVote(temp2, 4);
		db.saveVote(temp2, 5);
		
		try{
			
			List<Integer> num = new ArrayList<Integer>();
			List<Integer> vot = new ArrayList<Integer>();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM logger");
			
			while (rs.next()) {
				if(rs.getString("keyWord1").equals("inventada1")){
					num.add(rs.getInt("num_votos"));
					vot.add(rs.getInt("valoracion_total"));
				}
			}	
			

			assertSame(num.get(0),1);
			assertSame(num.get(1),2);
			
			stmt.executeUpdate("DELETE FROM logger WHERE keyWord1='inventada1'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
