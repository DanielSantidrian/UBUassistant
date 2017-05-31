<%@page import="database.DatabaseConnection"%>
<%@page import="handler.UBUassistantHandler"%>
<%@page import="java.util.LinkedHashSet"%>
<%@ page import="storage.Storage" %>
			

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
	
	
	<% 		UBUassistantHandler ubuassistant= (UBUassistantHandler) session.getAttribute("ubuassistantHandler");
			String userText = request.getParameter("userText"); 

		   	String responseQ = request.getParameter("response");
		   	String starBar=ubuassistant.getStarBarButton();
		   	String buttonDiv=(String)session.getAttribute("buttonDiv");
		   
			Storage storage = ubuassistant.getStorage();
		   	
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
		
			<div id="buttonPanel" class="buttonPanel">
				  <%if(responseQ.equals("no")){ %>
				  	<%=buttonDiv %>
				  	<%session.removeAttribute("buttonDiv"); %>
				  <%}else{ %>
				  	<%=starBar %>
				  <%} %>
			</div>
			
		</div>
		
		<%@ include file="form.html" %>
		
	</body>
</html>
