
<%@page import="handler.UBUassistantHandler"%>

<%@page import="java.text.DateFormat" import="java.text.SimpleDateFormat" import="java.util.Date"%>


<html>
	<head>
		<title>UBUassistant</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script>
		$(document).ready(function(){
			   $(".btn-minimize").click(function(){


			    if($(".btn-minimize").text() == '-') {
			    	$(".btn-minimize").html('+');
			    } 
			    else {
			    	$(".btn-minimize").html('-');
			    }
			    
			    $(".divchat").slideToggle();
			    
			  });
			});
		
		$(document).ready(function(){
			   $(".btn-close").click(function(){
			    $(".divchat-window").slideToggle();
			    $('.pinguino').slideToggle();
			  });
			});
		
		
		$(document).ready(function(){
			   $(".pinguino").click(function(){
				   	var e = $('.iframe');
				   	e.attr("src", e.attr("src"));
					$('.pinguino').slideToggle();
			    	$(".divchat-window").slideToggle();
			  });
			});
		
			
		</script>

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
				</div>
			</section>
			
			<input type="image" id="pinguino" class="pinguino" src="pinguino.png" />
			
			<div id="divchat-window" class="divchat-window" style="display:none;">
			
				<div id="divchat-button" class="divchat-button">
					UBUassistant
					<button class="btn-minimize">-</button>
					<button class="btn-close">x</button>
				</div>
				
				   
				<div id="divchat" class="divchat">
					
				    <iframe class="iframe" src="./hellouser/hellouser.jsp"></iframe>
				</div>

			</div>
			
	
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
