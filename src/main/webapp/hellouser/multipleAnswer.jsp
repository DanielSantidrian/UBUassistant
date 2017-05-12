<%@page import="database.DatabaseConnection"%>
<%@page import="handler.UBUassistantHandler"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="storage.Storage" %>

<html>

	<head>
		<title>UBUassistant</title>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
		<link rel="stylesheet" href="css/style.css">
		
		
		
		<script>
		
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
		   	String answer = request.getParameter("answer");
		   	String buttonDiv = request.getParameter("buttonDiv");
		   	session.setAttribute("buttonDiv", buttonDiv);

			String printText=userText.substring(0, 1).toUpperCase() + userText.substring(1).toLowerCase();
			String printAnswer=null;
			if(answer.contains("http")){
				printAnswer="<p>"+ubuassistant.getRandomSentence()+"<p>"+"<a href="+answer+" target=\"_blank\">"+answer+"</a>";
			}else{
				printAnswer="<p>"+ubuassistant.getRandomSentence()+"<p>"+answer;
			}
			
			Storage storage = ubuassistant.getStorage();
			
			storage.setChatOutput("bot-message", printText);
			storage.setChatOutput("user-message", printAnswer);
			
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
		
			<%= storage.getChatOutput() %>	
		
		</div>
		
		
		<div id="buttonPanelContent" class="buttonPanelContent">

			<div id="buttonPanel" style="margin-bottom: -18px;" class="buttonPanel">
				  <div class="rate-text">�Desea valorar esta respuesta?</div>
				  <form method="post" action="multipleAnswerQuestion.jsp" style="display: inline-block;">
				  	<input type="hidden" id="response" name="response" value="si">
				  	<input type="hidden" id="userText" name="userText" value="<%=userText%>">
				  	<input type="submit" id="but" class="multBut" value="Si">
				  </form>
				  <form method="post" action="multipleAnswerQuestion.jsp" style="display: inline-block;">
				  	<input type="hidden" id="response" name="response" value="no">
				  	<input type="hidden" id="userText" name="userText" value="<%=userText%>">
				  	<input type="submit" id="but" class="multBut" value="No">
				  </form>
			 </div>
		 
		 </div>
		
		<%@ include file="form.html" %>
		
	</body>
</html>
