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
		
		function hideAndSubmit(param){
			param.style.display = 'none';
			var buttonDiv = document.getElementById("buttonPanel").innerHTML;
			
			var num = document.getElementsByName("buttonDiv").length;
			var x = document.getElementsByName("buttonDiv")
			for (i=0; i < num; i++) {
				x[i].value=buttonDiv;
			}
			
			var form = param.form;
			form.submit();
		}
		
		window.onload = function() {
			var objDiv = document.getElementById("chat-output");
			objDiv.scrollTop = objDiv.scrollHeight;
		};
		

		</script>
	</head>
	
	

	<body>
	
	
	<% 	UBUassistantHandler ubuassistant= (UBUassistantHandler) session.getAttribute("ubuassistantHandler");
			String userText = request.getParameter("usertText"); 
		   	String divText = request.getParameter("div-content"); 
		   	String answer = request.getParameter("answer");
		   	String buttonDiv = request.getParameter("buttonDiv");
		   	session.setAttribute("buttonDiv", buttonDiv);

			String printText=userText.substring(0, 1).toUpperCase() + userText.substring(1);
			String printAnswer=null;
			if(answer.contains("http")){
				printAnswer="<p>"+ubuassistant.getRandomSentence()+"<p>"+"<a href="+answer+" target=\"_blank\">"+answer+"</a>";
			}else{
				printAnswer="<p>"+ubuassistant.getRandomSentence()+"<p>"+answer;
			}
			LinkedHashSet<String> words = new LinkedHashSet<String>();
			words.add(userText.toLowerCase());
			DatabaseConnection db = ubuassistant.getDb();
			db.aumentarNumBusquedas(words, answer);
		%>
		
		<script type="text/javascript">
		
		function getVoteAndSubmit(param){
			
			var vote=param.value;
			document.getElementById("vote").value=vote;
			document.getElementById("wordButton").value="<%=userText%>";
			document.getElementById("buttonDiv").value=document.getElementById("buttonPanel").innerHTML;
			
			param.form.submit()
		}
		
		
		</script>
		
		<div class="chat-output" id="chat-output">
		
			<%= divText %>
			
				<div class='bot-message'>   
				   
					<div class='message'> <%= printText  %></div>
					
				</div>
				
				<div class='user-message'>      
					<div class='message'> <%= printAnswer %></div>
				</div>		
		
		</div>
		
		
		<div id="buttonPanelContent" class="buttonPanelContent">

			<div id="buttonPanel" class="buttonPanel">
				  ¿Desea valorar esta respuesta?
				  <form method="post" action="multipleAnswerQuestion.jsp">
				  	<input type="hidden" id="div-content-suggest" name="div-content">
				  	<input type="hidden" id="response" name="response" value="si">
				  	<input type="hidden" id="userText" name="userText" value="<%=userText%>">
				  	<input type="submit" id="but" class="multBut" value="Si">
				  </form>
				  <form method="post" action="multipleAnswerQuestion.jsp">
				  	<input type="hidden" id="div-content-suggest" name="div-content">
				  	<input type="hidden" id="response" name="response" value="no">
				  	<input type="hidden" id="userText" name="userText" value="<%=userText%>">
				  	<input type="submit" id="but" class="multBut" value="No">
				  </form>
			 </div>
		 
		 </div>
		
		<div id="buttonPanel" class="chat-input">
				
		  <form method="post" id="user-input-form" action="response.jsp" onsubmit="if (document.getElementById('user-input').value.length < 1) return false;">
		    <input type="text" id="user-input" name="usertText" class="user-input" placeholder="Pregunta a UBUassistant">
		    <input type="hidden" id="div-content" name="div-content">
		    <input type="submit" class="enviar" value="Enviar">
		  </form>
		</div>
		
		
		<script>var num = document.getElementsByName("div-content").length;
				var x = document.getElementsByName("div-content")
				for (i=0; i < num; i++) {
					x[i].value=getDivContent();
				}
		</script>
		
	</body>
</html>
















