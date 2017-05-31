<%@ page import="database.DatabaseAdministration" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<html>
	<head>
		<title>UBUassistant Administration</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
		<script type="text/javascript">
		window.onload = function() {
			  document.getElementById('add').className = 'active';
			};
			
		function checkInput(){
			var k1=document.getElementById('keyWord1');
			var k2=document.getElementById('keyWord2');
			var k3=document.getElementById('keyWord3');
			var k4=document.getElementById('keyWord4');
			var k5=document.getElementById('keyWord5');
			var c =document.getElementById('categoria');
			var r =document.getElementById('respuesta');
			
			if(k1.value!="" && (k2.value!="" || k3.value!="" || k4.value!="" || k5.value!="") && c.value!="" && r.value!=""){
				return true;
			}else{

				document.getElementById('error').style.display = "block";
				return false;
			}
			
			
		}
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
		
		List<String> lista = new ArrayList<String>();
		String categoria="";
		String respuesta="";
		
		if(request.getParameter("keyWord1")!=null){
			if(!request.getParameter("keyWord1").equals("")){
				lista.add(request.getParameter("keyWord1"));
			}
		}
		if(request.getParameter("keyWord2")!=null){
			if(!request.getParameter("keyWord2").equals("")){
				lista.add(request.getParameter("keyWord2"));
			}
		}
		if(request.getParameter("keyWord3")!=null){
			if(!request.getParameter("keyWord3").equals("")){
				lista.add(request.getParameter("keyWord3"));
			}
		}
		if(request.getParameter("keyWord4")!=null){
			if(!request.getParameter("keyWord4").equals("")){
				lista.add(request.getParameter("keyWord4"));
			}
		}
		if(request.getParameter("keyWord5")!=null){
			if(!request.getParameter("keyWord5").equals("")){
				lista.add(request.getParameter("keyWord5"));
			}
		}
		if(request.getParameter("categoria")!=null){
			if(!request.getParameter("categoria").equals("")){
				categoria=request.getParameter("categoria");
			}
		}
		if(request.getParameter("respuesta")!=null){
			if(!request.getParameter("respuesta").equals("")){
				respuesta=request.getParameter("respuesta");
			}
		}
		
		
		if(lista.size()>0 && !categoria.equals("") && !respuesta.equals("")){
			DatabaseAdministration dba = new DatabaseAdministration();
			dba.addCase(lista,categoria, respuesta);
		}
		
		
		%>
	
		<%@ include file="header.html" %>
		
		<div id="content" class="content">
		
			<form id="addForm" class="addForm" action="addCase.jsp" method="POST" onsubmit="return checkInput()">
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 1:</label>
					<input id="keyWord1" type="text" name="keyWord1">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 2:</label>
					<input id="keyWord2" type="text" name="keyWord2">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 3:</label>
					<input id="keyWord3" type="text" name="keyWord3">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 4:</label>
					<input id="keyWord4" type="text" name="keyWord4">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Palabra Clave 5:</label>
					<input id="keyWord5" type="text" name="keyWord5">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Categor&iacute;a:</label>
					<input id="categoria" type="text" name="categoria">
				</p>
				
				<p class="pForm">
					<label class="txt" for="b">Respuesta:</label>
					<input style="width: 300%;" id="respuesta" type="text" name="respuesta">
				</p>
				
				<input class="aceptButton" type="submit" value="Aceptar">
				
			</form>
			
			<p id="error">
					*Debe rellenar al menos la palabra clave 1, la categor&iacute;a y la respuesta.
			</p>
	
		</div>
		

		<%@ include file="footer.html" %>
	
	</body>
	
</html>
