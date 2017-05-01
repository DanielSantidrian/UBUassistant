<%@page import="handler.UBUassistantHandler"%>
<%@page import="java.util.LinkedHashSet"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="jcolibri.method.retrieve.RetrievalResult"%>
<%@page import="representation.CaseSolution"%>
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
			String userText = request.getParameter("usertText"); 
		   	String divText = request.getParameter("div-content"); 
		   	String answer = request.getParameter("answer");
		   	String numString = request.getParameter("num");
		   	int num = Integer.parseInt(numString);

			String printText=userText.substring(0, 1).toUpperCase() + userText.substring(1);
			String printAnswer=null;
			if(answer.contains("http")){
				printAnswer="<p>"+ubuassistant.getRandomSentence()+"<p>"+"<a href="+answer+" target=\"_blank\">"+answer+"</a>";
			}else{
				printAnswer="<p>"+ubuassistant.getRandomSentence()+"<p>"+answer;
			}
			
			LinkedHashSet<String> words = new LinkedHashSet<String>();
			words.add(userText);
			ubuassistant.getDb().aumentarNumBusquedas(words, answer);
			
		    LinkedHashSet<String> suggestWord = new LinkedHashSet<String>();
			HashMap<String, List<RetrievalResult>> badResuts=ubuassistant.getBadResuts();
			List<RetrievalResult> listOfValues = ubuassistant.getListOfValues();
			
			for (String o : badResuts.keySet()) {
				if (badResuts.get(o).contains(listOfValues.get(num))) {
					suggestWord.add(o);
				}		
			}

			//When there are no answers and the user push a suggestion button it is supposed
			//That the text input by the user is related with the button so we store all this 
			//information for making the system learn
			ubuassistant.getDb().learnCases(suggestWord.iterator().next(), ((CaseSolution)listOfValues.get(num).get_case().getSolution()).getAnswer().toString());
			%>
		
		<script>
		function getVoteAndSubmit(param){
			
			var vote=param.value;
			document.getElementById("vote").value=vote;
			document.getElementById("wordButton").value="<%=userText%>";
			
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
		
		<% String starBar = ubuassistant.getStarBarButton();
		if(starBar!=null){%>
			<div id="buttonPanel" class="buttonPanel">		
				  	<%=starBar %>
		  	</div>
		<%} %>
		
		<div class="chat-input">
				
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
















