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
		   	String divText = request.getParameter("div-content"); 
		   	String vote=request.getParameter("vote");
		   	String wordButton=request.getParameter("wordButton");

		   	DatabaseConnection db = ubuassistant.getDb();
		   	LinkedHashSet<String> words = new LinkedHashSet<String>();
		   	words.add(wordButton.toLowerCase());
		   	db.saveVote(words, Integer.parseInt(vote));
		   	
		   	String buttonDiv=(String)session.getAttribute("buttonDiv");
		   	session.removeAttribute("buttonDiv");
		   	
		%>
		
		<div class="chat-output" id="chat-output">
		
			<%= divText %>
		</div>
		
		<%if(buttonDiv==null){ %>
			<div id="buttonPanelContent" class="buttonPanelContent">
				<div id="buttonPanel" class="buttonPanel">
					  	Voto guardado con éxito. Su voto ha sido <%=vote %>
				</div>
			</div>
		<%}else{ %>
			<div id="buttonPanelContent" class="buttonPanelContent">
			 	<div id="buttonPanel" class="buttonPanel">
			 		<%=buttonDiv %>
			 	</div>
			</div>
		<%} %>
		
		<%@ include file="form.html" %>
		
		<script>document.getElementById("div-content").value=getDivContent();</script>
		
	</body>
</html>
















