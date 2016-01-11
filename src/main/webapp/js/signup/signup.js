requirejs.config({
	baseUrl : "js",
	shim : {
		'jquery.cookie' : ['jquery'] 
	},
	paths : {
		'jquery' : 'lib/jquery-1.11.3',
		'jquery.cookie' : 'plugins/jquery.cookie',
		'alertManager' : 'plugins/alertManager'
	}
});


require(['jquery','alertManager'],function($, AlertManager){
	var alertManager;
	
	var focusFirstInputField = function(){
		$($("form.signup").find("input")[0]).focus();
	}
	
	var bindSignUpFormSubmit = function(){
		var $form = $("form.signup");
		$form.on("click keyup", ".button.submit", function(event){
			if (event.type=="keyup" && e.keyCode != 13 && e.keyCode != 9){
				event.preventDefault();
				return;
			}
			if (event.type=="keyup" && e.keyCode == 9){
				return;
			}
			event.preventDefault();	
			$.ajax({
				method : "post",
				data : {
					username : $form.find("input[name=username]").val(),
					password : $form.find("input[name=password]").val(),
					repeatPassword : $form.find("input[name=repeatPassword]").val(),
					bday : $form.find("input[name=bday]").val()
				},
				success : function(response){
					if (response.success){
						alertManager.enqueueAlert("Account successfully created");
						top.location.href=response.redirectURL;
					} else {
						$form.find(".errorMessage").text(response.message);
						$form[0].reset();
					}		
				},
				error : function(){
					alertManager.enqueueAlert("An error has occurred. Please try again","error");
					top.location.reload();
				}
			});			
		});		
	}
	
	var setupSignUpFormValidation = function(){
		$("form.signup").find("input").each(function(){
			this.oninvalid =function(e) {
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
		    this.oninput = function(e) {
		        e.target.setCustomValidity("");
		    };
		});
	}
	
	$(function(){
		alertManager = AlertManager.getInstance();
		alertManager.dequeueAllAlerts();
		focusFirstInputField();
		setupSignUpFormValidation();
		bindSignUpFormSubmit();
	});
	
	
});

	
	
	
	