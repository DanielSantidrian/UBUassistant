<%@ page import="database.DatabaseAdministration" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<html>
	<head>
		<title>UBUassistant Administration</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="../css/admin/main.css" />
		
		<script src="../js/jquery.js"></script>
		<script src="../js/admin.js"></script>
		
		<script type="text/javascript">
		window.onload = function() {
			document.getElementById('edit').className = 'active';
			};
			
		</script>
		
		<%@ include file="checkSession.jsp" %>
		
	</head>
	<body>
	
		<%
		String id="";
		String keyWord1="";
		String keyWord2="";
		String keyWord3="";
		String keyWord4="";
		String keyWord5="";
		String categoria="";
		String respuesta="";
		
		if(request.getParameter("id")!=null){
			id=request.getParameter("id");
		}
		if(request.getParameter("keyWord1")!=null){
			keyWord1=request.getParameter("keyWord1");
			if(keyWord1.equals("-")|| keyWord1.equals("null")){
				keyWord1="";
			}
		}
		if(request.getParameter("keyWord2")!=null){
			keyWord2=request.getParameter("keyWord2");
			if(keyWord2.equals("-")|| keyWord2.equals("null")){
				keyWord2="";
			}
		}
		if(request.getParameter("keyWord3")!=null){
			keyWord3=request.getParameter("keyWord3");
			if(keyWord3.equals("-")|| keyWord3.equals("null")){
				keyWord3="";
			}
		}
		if(request.getParameter("keyWord4")!=null){
			keyWord4=request.getParameter("keyWord4");
			if(keyWord4.equals("-")|| keyWord4.equals("null")){
				keyWord4="";
			}
		}
		if(request.getParameter("keyWord5")!=null){
			keyWord5=request.getParameter("keyWord5");
			if(keyWord5.equals("-")|| keyWord5.equals("null")){
				keyWord5="";
			}
		}
		if(request.getParameter("categoria")!=null){
			categoria=request.getParameter("categoria");
		}
		if(request.getParameter("answer")!=null){
			respuesta=request.getParameter("answer");
		}
		
		%>
	
		<%@ include file="header.html" %>
		
		<script type="text/javascript">
			document.getElementById("title").innerHTML="Editar caso";
			document.getElementById("subtitle").innerHTML="Formulario para editar un caso de la base de datos.";
		</script>
		
		<div id="content" class="content">
		
		<ol class="breadcrumb">
		  <li class="breadcrumb-item"><a href="modifyCases.jsp">Modificar Casos</a></li>
		  <li class="breadcrumb-item actual">Editar Caso</li>
		</ol>
		
			<form id="addForm" class="addForm" action="modifyCases.jsp;jsessionid=<%=session.getId()%>" method="POST" onsubmit="return checkInput()">
				
				<input type="hidden" name="id" value="<%=id %>">
			
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 1:</label>
					<input id="keyWord1" type="text" name="keyWord1" value="<%=keyWord1 %>">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 2:</label>
					<input id="keyWord2" type="text" name="keyWord2" value="<%=keyWord2 %>">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 3:</label>
					<input id="keyWord3" type="text" name="keyWord3" value="<%=keyWord3 %>">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 4:</label>
					<input id="keyWord4" type="text" name="keyWord4" value="<%=keyWord4 %>">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 5:</label>
					<input id="keyWord5" type="text" name="keyWord5" value="<%=keyWord5 %>">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Categor&iacute;a:</label>
					<input id="categoria" type="text" name="categoria" value="<%=categoria %>">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Respuesta:</label>
					<input style="width: 300%;" id="respuesta" type="text" name="respuesta" value="<%=respuesta %>">
				</p>
				
				<input type="hidden" name="edit" value="yes">
				
				<input class="aceptButton" type="submit" value="Aceptar">
				
			</form>
			
			<p id="error">
					*Debe rellenar al menos la palabra clave 1, la categor&iacute;a y la respuesta.
			</p>
	
		</div>
		

		<%@ include file="footer.html" %>
	
	</body>
	
</html>
