function validate(form, a)
  {
	 var password = $(".password").val();
	 var rpassword = $(".repeatPassword").val();
	 
    if(password != rpassword) {
    	alert("Passwords don't match!");
        return false;
    } 
    else if (a == "This user already exists!"){
    	alert(a + " ");
        return false;
    }
    else {
    	return true;
    }
  }

$(document).ready(function() {
	var msg="";
	var elements = document.getElementsByTagName("input");

	for (var i = 0; i < elements.length; i++) {
	   elements[i].oninvalid =function(e) {
	        if (!e.target.validity.valid) {
	        switch(e.target.id){
	        	case 'repeatPassword' : 
	            e.target.setCustomValidity("Please confirm password");break;
	            case 'password' : 
	            e.target.setCustomValidity("Please insert password");break;
	            case 'username' : 
	            e.target.setCustomValidity("Username cannot be blank");break;
	            case 'birthday' : 
		        e.target.setCustomValidity("Please set your birthday");break;
	        default : e.target.setCustomValidity("");break;

	        }
	       }
	    };
	   elements[i].oninput = function(e) {
	        e.target.setCustomValidity(msg);
	    };
	} 
	
	
	
	})
	
	
	
	
	