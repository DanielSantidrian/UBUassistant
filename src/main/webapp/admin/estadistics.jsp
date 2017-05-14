
<%@ page import="com.mysql.jdbc.jdbc2.optional.MysqlDataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>


<html>
	<head>
		<title>UBUassistant Administration</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="sorttable.js"></script>
		
		<script type="text/javascript">
		window.onload = function() {
			  document.getElementById('estadis').className = 'active';
			};
		</script>
		
	</head>
	<body>
	
		<%@ include file="header.html" %>
		
		<div id="content" class="content">
	
		<%
		MysqlDataSource ds = new MysqlDataSource();

		ds.setUser("root");
		ds.setPassword("1234");
		ds.setDatabaseName("ubuassistant");
		ds.setURL("jdbc:mysql://localhost/ubuassistant");
		
		Connection con=null;
		try {
			con = ds.getConnection();
		} catch (SQLException e) {
			System.err.println("Error al conectar con la base de datos.");
		%> 
		<h3>Error al conectar con la base de datos.</h3> 
		
		<% }%>
		
		
		<br>
		
		<% 
		//Tabla estadisticas
		
		if(con!=null){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM estadisticas");
		%>
		<div>
			<table id="tabla" class="sortable">
			<thead>
			  <tr><th>ID Usuario</th><th>Fecha</th><th>Palabra clave 1</th>
			  <th>Palabra clave 2</th><th>Palabra clave 3</th><th>Palabra clave 4</th>
			  <th>Palabra clave 5</th><th>Categoría</th><th>Respuesta</th><th>Numero de búsquedas</th>
			  <th>Número de votos</th><th>Valoración total</th></tr>
			</thead>
			<tbody>
		<%
			while (rs.next()) {
			%>
			  <tr><td><%=rs.getString("userid")  %></td><td><%=rs.getString("fecha") %></td><td><%=rs.getString("keyWord1") %></td>
			  <td><%=rs.getString("keyWord2") %></td><td><%=rs.getString("keyWord3") %></td><td><%=rs.getString("keyWord4") %></td>
			  <td><%=rs.getString("keyWord5") %></td><td><%=rs.getString("categoria") %></td><td><%=rs.getString("respuesta") %></td>
			  <td><%=rs.getString("num_busquedas") %></td><td><%=rs.getString("num_votos") %></td><td><%=rs.getString("valoracion_total") %></td></tr>
			<%
			}
		}
		%>
			</tbody>
			</table>
		
		</div>
		
	</div>
	
	<%@ include file="footer.html" %>
	
	</body>
</html>
