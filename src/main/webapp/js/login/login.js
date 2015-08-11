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
	
	var focusFirstInputField = function(){
		$($("form.login").find("input")[0]).focus();
	}
	
	var setupLoginFormValidation = function(){		
		$("form.login").find("input").each(function(){
			this.oninvalid =function(e) {
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
			this.oninput = function(e) {
				e.target.setCustomValidity("");
			};		
		});
	};
	
	var bindLoginFormSubmit = function(){
		var $form = $("form.login");
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
					password : $form.find("input[name=password]").val()
				},
				statusCode : {
					200 : function(response){
						window.location.href = "gametypes";
					}, 
					302 : function (xhr){
						window.location.replace(xhr.responseText);
					},
					403 : function(xhr){
						$form[0].reset();
						$form.find(".errorMessage").text(xhr.responseText);
						focusFirstInputField();
					}				
				}				
			});			
		});
	};
	
	$(function(){
		var alertManager = AlertManager.getInstance();
		alertManager.dequeueAllAlerts();
		focusFirstInputField();
		setupLoginFormValidation();
		bindLoginFormSubmit();		
	});
	
});