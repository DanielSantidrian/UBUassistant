<%@page import="database.DatabaseConnection"%>
<%@page import="handler.UBUassistantHandler"%>
<%@page import="java.util.LinkedHashSet"%>
<html>

	<head>
		<title>UBUassistant</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
		<link rel="stylesheet" href="css/style.css">
		
		<script>
		
		function getDivContent(){
			
			var outputAreaText = document.getElementById("chat-output").innerHTML;
			return outputAreaText;
		}
		
		window.onload = function() {
			var objDiv = document.getElementById("chat-output");
			objDiv.scrollTop = objDiv.scrollHeight;
		};
		

		</script>
	</head>

	<body>


		<% 	UBUassistantHandler ubuassistant= (UBUassistantHandler) session.getAttribute("ubuassistantHandler"); 
		   	String divText = request.getParameter("div-content"); 
		   	LinkedHashSet<String> currentWords=ubuassistant.getCurrentWords();
		   	String vote=request.getParameter("vote");
		   	DatabaseConnection db = ubuassistant.getDb();
		   	db.saveVote(currentWords, Integer.parseInt(vote));
		   	
		%>
		
		<div class="chat-output" id="chat-output">
		
			<%= divText %>
		</div>
		
		<div id="buttonPanelContent" class="buttonPanelContent">
			<div id="buttonPanel" class="buttonPanel">
				  	Voto guardado con éxito. Su voto ha sido <%=vote %>
			 </div>
		</div>
		
		<%@ include file="form.html" %>
		
		<script>document.getElementById("div-content").value=getDivContent();</script>
		
	</body>
</html>
















