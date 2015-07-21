$(document).ready(function() {
	
	var $form = $("form.game");
	$form.on("submit", function(event){
		event.preventDefault();
		
		 var roles = ["role1", "role2", "role3"];

		var ships = [];
		var shipsData = [];
		
		ships = editingScreen.gameBoard.getShips();
		for (var i = 0; i < ships.length; i++){
			var transforms = ships[i].position.getTransforms();
			shipsData.push(transforms);
		}
				
		$.ajax({
			method : "post",
//			data : {
//				username : $form.find("input[name=username]").val(),
//				password : $form.find("input[name=password]").val()
//			},
			data : {
				bla: JSON.stringify(roles)
			},

			success : function(response){
//				if ($(response).find("#message").text().length > 0){
//					alert($(response).find("#message").text());
//				} else {
//					alert("Welcome!");
//					top.location="http://localhost:8080/battleships/home";
//				}
				alert(response);
			},
			
			error : function(error){
				alert(error);
			}
			
		});
		
	});
});