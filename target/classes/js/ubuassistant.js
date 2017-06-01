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

function getVoteAndSubmit(param){
			
	var vote=param.value;
	document.getElementById("vote").value=vote;
	document.getElementById("wordButton").value="<%=userText%>";
	document.getElementById("buttonDiv").value=document.getElementById("buttonPanel").innerHTML;

	param.form.submit()
}