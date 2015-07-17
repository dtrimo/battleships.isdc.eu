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
	})