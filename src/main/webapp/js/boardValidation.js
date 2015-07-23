$(document).ready(function() {
	 
	 var $form = $("form.game");
	 $form.on("submit", function(event){
	  event.preventDefault();
	  
	  var ships = [];
	  var shipsData = [];
	  
	  ships = editingScreen.gameBoard.getShips();
	  for (var i = 0; i < ships.length; i++){
	   var shipData = [];
	   var shipTransformData = [];
	   
	   var transforms = ships[i].position.getTransforms();
	   for (var j = 0; j < transforms.length; j++)
	    shipTransformData.push(Transform.getTransformName(transforms[j]));
	   shipsData.push(shipTransformData);
	  }
	    
	  $.ajax({
	   method : "post",
	   data : {
	    sdata: JSON.stringify(shipsData) 
	   },
	
	   success : function(response){
	//    if ($(response).find("#message").text().length > 0){
	//     alert($(response).find("#message").text());
	//    } else {
	//     alert("Welcome!");
	//     top.location="http://localhost:8080/battleships/home";
	//    }
	    alert(response);
	   },
	   
	   error : function(error){
	    alert(error);
	   }
	   
	  });
	  
	 });
});