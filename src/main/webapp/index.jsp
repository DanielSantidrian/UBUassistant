
<html>
	<head>
		<title>UBUassistant</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<link rel="stylesheet" href="css/main.css" />
		
		<script src="./js/jquery.js"></script>
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
	

			<%@ include file="header.html" %>
			
			<input type="image" id="pinguino" class="pinguino" src="img/pinguino3.png" />
			
			<div id="divchat-window" class="divchat-window" style="display:none;">
			
				<div id="divchat-button" class="divchat-button">
					UBUassistant
					<button class="btn-minimize">-</button>
					<button class="btn-close">x</button>
				</div>
				
				   
				<div id="divchat" class="divchat">
					
				    <iframe class="iframe" src="./UBUassistant/hellouser.jsp"></iframe>
				</div>

			</div>
			
			<div class="admin"> 
				<a href="./admin/adminLogin.jsp">
					<input type="image" class="adminLink" src="img/admin.png" />
				</a>
			</div>
			
			
			<div class="content">
			<p>
			<br><br><br><br><br><br><br>
			</p>
			
			</div>
			

			<%@ include file="footer.html" %>
	</body>
</html>
