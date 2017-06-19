package database;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * 
 * @author Daniel Santidrian Alonso
 *
 */
public class DatabaseUtilTest {
	
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

		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos.");
		}
		
	}

	
	@Test
	public void getCategoriaTest() {
		
		DatabaseUtil dbu = new DatabaseUtil();
		assertEquals("universidad", dbu.getCategoria(con, "http://www3.ubu.es/ubuespacio/"));
		assertEquals("internacional", dbu.getCategoria(con, "http://www.ubu.es/servicio-de-relaciones-internacionales"));
	}

}
