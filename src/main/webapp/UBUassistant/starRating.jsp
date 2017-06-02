<%@page import="database.DatabaseConnection"%>
<%@page import="handler.UBUassistantHandler"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="storage.Storage" %>
		

<html>

	<head>
		<title>UBUassistant</title>
		<link rel="stylesheet" href="../css/ubuassistant/normalize.css">
		<link rel="stylesheet" href="../css/ubuassistant/style.css">
		
		<script>
		
		window.onload = function() {
			var objDiv = document.getElementById("chat-output");
			objDiv.scrollTop = objDiv.scrollHeight;
		};
		

		</script>
	</head>

	<body>


		<% 	UBUassistantHandler ubuassistant= (UBUassistantHandler) session.getAttribute("ubuassistantHandler"); 
		   	LinkedHashSet<String> currentWords=ubuassistant.getCurrentWords();
		   	String vote=request.getParameter("vote");
		   	DatabaseConnection db = ubuassistant.getDb();
		   	db.saveVote(currentWords, Integer.parseInt(vote));

			Storage storage = ubuassistant.getStorage();
		%>
		
		<div class="chat-output" id="chat-output">
		
			<%= storage.getChatOutput() %>
		</div>
		
		<div id="buttonPanelContent" class="buttonPanelContent">
			<div id="buttonPanel" class="buttonPanel">
				  	Voto guardado con �xito. Su voto ha sido <%=vote %>
			 </div>
		</div>
		
		<%@ include file="form.html" %>
		
		<script>document.getElementById("div-content").value=getDivContent();</script>
		
	</body>
</html>















