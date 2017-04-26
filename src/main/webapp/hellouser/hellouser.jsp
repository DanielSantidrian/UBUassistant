
<%@ page import="handler.UBUassistantHandler" %>

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
		
		</script>
		
	</head>

	<body>	
	
		<% 	UBUassistantHandler ubuassistant= (UBUassistantHandler) session.getAttribute("ubuassistantHandler");%>
		<% 	String salute=ubuassistant.getSalute();	%>

		<div class="chat-output" id="chat-output">
			<div class="user-message">
		    	<div class="message"><%= salute %></div>
		  	</div>
		</div>
		
		
		<div class="chat-input" id="chat-input">
			<form method="post" id="user-input-form" action="response.jsp" >
		    	<input type="text" id="user-input" name="usertText" class="user-input" placeholder="Pregunta a UBUassistant">
		    	<input type="hidden" id="div-content" name="div-content">
		    	<input type="submit" value="Enviar">
		  	</form>
		</div>
		
		<script>document.getElementById("div-content").value=getDivContent();</script>
			  
	</body>
	
</html>