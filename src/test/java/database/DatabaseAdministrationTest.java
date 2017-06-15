package database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 
 * @author Daniel Santidrian Alonso
 *
 */
public class DatabaseAdministrationTest {
	
	DatabaseAdministration db;
	Connection con;
	String userID;
	
	@Before
	public void before(){
		
		db = new DatabaseAdministration();
		
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

	
	@Test
	public void addCaseTest() {
		
		String cat="";
		
		List<String> lista = new ArrayList<String>();
		
		lista.add("CasoInventado");
		
		db.addCase(lista, "categoriaInventada", "respInventada");
		
		try{
			
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM casedescription WHERE keyWord1='CasoInventado'");
			
			while (rs.next()) {
				if(rs.getString("keyWord1").equals("CasoInventado")){
					cat=rs.getString("categoria");
				}
			}	
			
			assertEquals(cat, "categoriaInventada");
			
			stmt.executeUpdate("DELETE FROM casedescription WHERE keyWord1='CasoInventado'");
			stmt.executeUpdate("DELETE FROM casesolution WHERE answer='respInventada'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void editCaseTest() {
		
		List<String> lista = new ArrayList<String>();
		
		lista.add("CasoInventado");
		
		db.addCase(lista, "categoriaInventada", "respInventada");
		
		
		
		try{
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id FROM casedescription where categoria='categoriaInventada'");
			
			rs.next();
			int id=rs.getInt("id");
			
			String[] lista2 = new String[5];
			lista2[0]="editCase";
			
			db.editCase(String.valueOf(id), lista2, "editCat", "editAnswer");
			
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT * FROM casedescription WHERE id="+id);
			
			Statement stmt3 = con.createStatement();
			ResultSet rs3 = stmt3.executeQuery("SELECT * FROM casesolution WHERE id="+id);
			
			String resp="";
			String cat="";
			
			while (rs2.next() && rs3.next()) {
				cat=rs2.getString("categoria");
				resp=rs3.getString("answer");
			}	
			
			
			assertEquals(cat, "editCat");
			assertEquals(resp, "editAnswer");
			
			stmt.executeUpdate("DELETE FROM casedescription WHERE categoria='editCat'");
			stmt.executeUpdate("DELETE FROM casesolution WHERE answer='editAnswer'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void removeCaseTest() {
		
		List<String> lista = new ArrayList<String>();
		
		lista.add("CasoInventado");
		
		db.addCase(lista, "categoriaInventada", "respInventada");
		
		
		
		try{
			
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id FROM casedescription where categoria='categoriaInventada'");
			
			rs.next();
			int id=rs.getInt("id");
			
			String[] lista2 = new String[5];
			lista2[0]="editCase";
			
			db.removeCase(String.valueOf(id));
			
			Statement stmt3 = con.createStatement();
			ResultSet rs3 = stmt3.executeQuery("SELECT COUNT(*) AS total FROM casedescription WHERE categoria='categoriaInventada'");
			
			rs3.next();
			int num=rs3.getInt("total");
			
			
			assertTrue(num==0);
			
			stmt.executeUpdate("DELETE FROM casedescription WHERE categoria='categoriaInventada'");
			stmt.executeUpdate("DELETE FROM casesolution WHERE answer='respInventada'");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void executeLearnTest() {
		
		try{
			
			PreparedStatement pst = 
				con.prepareStatement("INSERT INTO aprendizaje "
										+ "(userid,palabra1,palabra2) VALUES (?,?,?)");
		
			pst.setString(1, "01111111");
			pst.setString(2, "palabra1");
			pst.setString(3, "palabra2");
			
			pst.executeUpdate();
			
			db.executeLearn("palabra1", "palabra2");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM casedescription");
			
			String p="";
			
			while(rs.next()){
				if(rs.getString("keyWord1").equals("palabra1")){
					p = rs.getString("keyWord1");
				}
			}
			
			assertEquals(p, "palabra1");
			
			stmt.executeUpdate("DELETE FROM casedescription WHERE keyWord1='palabra1'");
			stmt.executeUpdate("DELETE FROM casesolution WHERE answer='palabra2'");
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void ignoreLearnTest() {
		
		try{
			
			PreparedStatement pst = 
				con.prepareStatement("INSERT INTO aprendizaje "
										+ "(userid,palabra1,palabra2) VALUES (?,?,?)");
		
			pst.setString(1, "01111111");
			pst.setString(2, "palabra1");
			pst.setString(3, "palabra2");
			
			pst.executeUpdate();
			
			db.ignoreLearn("palabra1", "palabra2");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM aprendizaje WHERE palabra1='palabra1'");
			
			rs.next();
			int count=rs.getInt("total");
			
			assertTrue(count==0);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void clearLogTest() {
		
		try{
			
			PreparedStatement pst = 
				con.prepareStatement("INSERT INTO logger "
										+ "(userid,fecha,keyWord1,keyWord2,keyWord3,keyWord4,keyWord5,categoria,respuesta,"+
						"num_busquedas,num_votos,valoracion_total) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
		
			pst.setString(1, "01111111");
			pst.setString(2, "2017-06-14 11:41:55");
			pst.setString(3, "palabra1");
			pst.setString(4, "palabra2");
			pst.setString(5, "palabra3");
			pst.setString(6, "palabra4");
			pst.setString(7, "palabra5");
			pst.setString(8, "cat");
			pst.setString(9, "resp");
			pst.setInt(10, 2);
			pst.setInt(11, 3);
			pst.setInt(12, 4);
			
			pst.executeUpdate();
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM logger");
			
			rs.next();
			int count1=rs.getInt("total");
			assertTrue(count1!=0);
			
			db.clearLog();
			
			Statement stmt2 = con.createStatement();
			ResultSet rs2 = stmt2.executeQuery("SELECT COUNT(*) AS total1 FROM logger");
			
			rs2.next();
			int count2=rs2.getInt("total1");
			assertTrue(count2==0);
			

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
