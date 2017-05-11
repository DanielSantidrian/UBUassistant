
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
		
		
		<%@ include file="form.html" %>
		

		<script>document.getElementById("div-content").value=getDivContent();</script>
			  
	</body>
	
</html>