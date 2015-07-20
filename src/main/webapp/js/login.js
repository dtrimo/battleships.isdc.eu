function show(a) {
	if (a == "Incorrect password!"){
		alert(a + " ");
	}
//		return false;
	else {
//		return true;
	}
}
$(document).ready(function() {
	var msg="";
	var elements = document.getElementsByTagName("input");

	for (var i = 0; i < elements.length; i++) {
	   elements[i].oninvalid =function(e) {
	        if (!e.target.validity.valid) {
	        switch(e.target.id){
	            case 'password' : 
	            e.target.setCustomValidity("Please insert password");break;
	            case 'username' : 
	            e.target.setCustomValidity("Username cannot be blank");break;
	        default : e.target.setCustomValidity("");break;

	        }
	       }
	    };
	   elements[i].oninput = function(e) {
	        e.target.setCustomValidity(msg);
	    };
	} 
	
	if ($('#message').text().length > 0) {
		alert($('#message').text());
	} 
	
	var $form = $("form.login");
	$form.on("submit", function(event){
		event.preventDefault();
		//do validations;
		//if valid
		$.ajax({
			method : "post",
			data : {
				username : $form.find("input[name=username]").val(),
				password : $form.find("input[name=password]").val()
			},
			success : function(response){
				if ($(response).find("#message").text().length > 0){
					alert($(response).find("#message").text());
				} else {
					alert("Welcome!");
					top.location="http://localhost:8080/battleships/home";
				}
			},
			error : function(error){
				alert(error);
			}
			
		});
		
	});
	
})