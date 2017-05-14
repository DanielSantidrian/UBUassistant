
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
			  document.getElementById('lea').className = 'active';
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
		
		<%
		 }
		
		//Tabla aprendizaje
		
		if(con!=null){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM aprendizaje");
		%>
		<br>
		<div>
			<table id="tabla" class="sortable">
			<thead>
			  <tr><th>ID Usuario</th><th>Palabra sugerida</th><th>Respuesta sugerida</th></tr>
			</thead>
			<tbody>
		<%
			while (rs.next()) {
			%>
			  <tr><td><%=rs.getString("userid") %></td><td><%=rs.getString("palabra1") %></td><td><%=rs.getString("palabra2") %></td></tr>
			<%
			}
		}
		%>
			</tbody>
			</table>

		</div>
		
		<br>

	</div>
	<%@ include file="footer.html" %>
	</body>
</html>
