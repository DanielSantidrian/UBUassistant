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
		
		function getVoteAndSubmit(param){
			
			var vote=param.value;
			document.getElementById("vote").value=vote;
			
			param.form.submit()
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
				printText=userText.substring(0, 1).toUpperCase() + userText.substring(1).toLowerCase();
				ubuassistant.setUsertText(userText);
				answer = ubuassistant.getResponse();
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
		

		<%String suggestButtons=ubuassistant.getSuggestButtons();
		if(suggestButtons!=null){%>
		
		<div id="buttonPanelContent" class="buttonPanelContent" >
			
			<div id="buttonPanel" class="buttonPanel" style="margin-bottom: -15px">
			  	<%=suggestButtons %>
			  	<% LinkedHashSet<String> temp = new LinkedHashSet<String>();
			  	temp.add(userText);
			  	ubuassistant.getDb().aumentarNumBusquedas(temp, null); %>
		  	</div>
		  	
		 </div>
		<%} %>
		
		<%String multipleButtons=ubuassistant.getMultipleButtons();
		if(multipleButtons!=null){%>
			
		<div id="buttonPanelContent" class="buttonPanelContent">
			<div id="buttonPanel" class="buttonPanel">
				
			  	<%=multipleButtons %>
			
		  	</div>
		  	
		</div>
		<%} %>
		
		<% String starBar = ubuassistant.getStarBar();
		if(starBar!=null){%>
		
		<div id="buttonPanelContent" class="buttonPanelContent">
			<div id="buttonPanel" class="buttonPanel">		
				  	<%=starBar %>
		  	</div>
		  	
		</div>
		<%} %>
		
		
		<%@ include file="form.html" %>
		
		
		<script>var num = document.getElementsByName("div-content").length;
				var x = document.getElementsByName("div-content")
				for (i=0; i < num; i++) {
					x[i].value=getDivContent();
				}
		</script>
		
		<script>
		
		var buttonDiv = document.getElementById("buttonPanel").innerHTML;
		var num = document.getElementsByName("buttonDiv").length;
		var x = document.getElementsByName("buttonDiv")
		for (i=0; i < num; i++) {
			x[i].value=buttonDiv;
		}
		
		</script>
		
	</body>
</html>
















