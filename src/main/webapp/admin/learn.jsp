
<%@ page import="com.mysql.jdbc.jdbc2.optional.MysqlDataSource" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="database.DatabaseAdministration" %>


<html>
	<head>
		<title>UBUassistant Administration</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="sorttable.js"></script>
		
		<script src="tableExport/tableExport.js"></script>
		<script src="tableExport/jquery.base64.js"></script>
		<script src="tableExport/html2canvas.js"></script>
		<script src="tableExport/jspdf/libs/sprintf.js"></script>
		<script src="tableExport/jspdf/jspdf.js"></script>
		<script src="tableExport/jspdf/libs/base64.js"></script>
		
		<script type="text/javascript">
		window.onload = function() {
			  document.getElementById('learn').className = 'active';
			};
		</script>
		
		<script>
			$(document).ready(function(){
			   $(".saveas").click(function(){
			    $("#saveButtons").toggle( "slide" );
			  });
			});
		</script>
		
		<%
		boolean logged=false;
		
		if(session.getAttribute("logged")!=null){
			logged=(boolean)session.getAttribute("logged");
		}
		
		if(!logged){
			response.sendRedirect("adminLogin.jsp");
		}%>
		
	</head>
	<body>
	
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
		
		if(request.getParameter("palabra")!=null && request.getParameter("respuesta")!=null && request.getParameter("accion")!=null){
			
			String palabra=request.getParameter("palabra");
			String respuesta=request.getParameter("respuesta");
			String accion=request.getParameter("accion");
			DatabaseAdministration dba = new DatabaseAdministration();
			
			if(accion.equals("descartar")){
				dba.ignoreLearn(palabra, respuesta);
			}
			
			if(accion.equals("aprender")){
				dba.executeLearn(palabra, respuesta);
			}
		}
		%>
	
		<%@ include file="header.html" %>
		
		<div id="content" class="content">
		
		<%@ include file="saveMenu.html" %>
	
		<%
		//Tabla aprendizaje
		
		if(con!=null){
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM aprendizaje"); %>
		
		<br>
		<div class="divtable">
			<table id="tabla" class="sortable">
			<thead>
			  <tr><th>ID Usuario</th><th>Palabra sugerida</th><th>Respuesta sugerida</th><th>Acción</th></tr>
			</thead>
			<tbody>
		<%
			while (rs.next()) {
			%>
			  <tr>
				  <td><%=rs.getString("userid") %></td>
				  <td><%=rs.getString("palabra1") %></td>
				  <td><%=rs.getString("palabra2") %></td>
				  <td>
				  	<form method="post" action="learn.jsp" class="actionform">
				  		<input type="hidden" name="palabra" value="<%=rs.getString("palabra1") %>">
				  		<input type="hidden" name="respuesta" value="<%=rs.getString("palabra2") %>">
				  		<input type="hidden" name="accion" value="aprender">
				  		<input type="submit" id="button" class="aprender" value="Aprender">
				  	</form>
				  	<form method="post" action="learn.jsp" class="actionform">
				  		<input type="hidden" name="palabra" value="<%=rs.getString("palabra1") %>">
				  		<input type="hidden" name="respuesta" value="<%=rs.getString("palabra2") %>">
				  		<input type="hidden" name="accion" value="descartar">
				  		<input type="submit" id="button" class="descartar" value="Descartar">
				  	</form>
				  </td>
			  </tr>
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
