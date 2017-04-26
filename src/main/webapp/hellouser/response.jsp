<%@page import="handler.UBUassistantHandler"%>
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


		<% 	UBUassistantHandler ubuassistant= (UBUassistantHandler) session.getAttribute("ubuassistantHandler");
			String userText = request.getParameter("usertText"); 
		   	String divText = request.getParameter("div-content"); 
		%>
		
		<% 	String answer = null;
			String printText=null;
			if(userText.length()>0){
			ubuassistant.setUsertText(userText);
			answer = ubuassistant.getResponse();
			printText=userText.substring(0, 1).toUpperCase() + userText.substring(1);
			}
			
		%>
		
		
		<div class="chat-output" id="chat-output">
		
			<%= divText %>
			
			<%if(printText!=null && answer!=null){%>
			
				<div class='bot-message'>      
					<div class='message'> <%= printText  %></div>
				</div>
				
				<div class='user-message'>      
					<div class='message'> <%= answer %></div>
				</div>
			
			<%} %>
			
		</div>
		
		<div class="chat-input">
				
		  <form method="post" id="user-input-form" action="response.jsp" >
		    <input type="text" id="user-input" name="usertText" class="user-input" placeholder="Pregunta a UBUassistant">
		    <input type="hidden" id="div-content" name="div-content">
		    <input type="submit" value="Enviar">
		  </form>
		</div>
		
		<script>document.getElementById("div-content").value=getDivContent();</script>
		
	</body>
</html>
















