
<%@page import="handler.UBUassistantHandler"%>

<%@page import="java.text.DateFormat" import="java.text.SimpleDateFormat" import="java.util.Date"%>


<html>
	<head>
		<title>UBUassistant</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />

	</head>
	<body>
	
		<% 
		
		DateFormat formatForId = new SimpleDateFormat("yyMMddHHmmssSSS");
			
		String userID=formatForId.format(new Date()); 
		
		UBUassistantHandler ubuassistant = new UBUassistantHandler(userID);
		
		session.setAttribute("ubuassistantHandler", ubuassistant); 
				
		%>
	
		

		<!-- Header -->
			<section id="header">
				<div class="inner">
					
					<img src="LogoWeb.png" width="150" height="124" />
					<h1>Hola, soy <strong>UBUassistant</strong>, un asistente<br />
					que te ayudar&aacute; con cualquier duda sobre la UBU</h1>
					<p>Preguntame cualquier aspecto sobre la Universidad<br />
					de Burgos y te echar&eacute; una mano.</p>
					<ul class="actions">
						<li><a href="./hellouser/hellouser.jsp" class="button scrolly">DESC&Uacute;šBREME</a></li>
					</ul>
				</div>
			</section>
			
	
		<!-- Footer -->
			<section id="footer">
				<ul class="icons">
					<li><a href="https://github.com/DanielSantidrian/UBUassistant"><img src="GitHub.png" width="80" height="54" /></a></li>
					<li><a href="#"><img src="HTMLCSS.png" width="80" height="54" /></a></li>
				</ul>
				<ul class="copyright">
					<li>&copy; UBUassistant</li><li>Realizado por: Daniel Santidri&aacute;n Alonso</li><li>Tutor: Pedro Renedo Fern&aacute;ndez</li>
				</ul>
			</section>

	</body>
</html>
