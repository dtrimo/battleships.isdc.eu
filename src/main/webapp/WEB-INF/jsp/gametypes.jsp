<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="js/battleshipPosition.js"></script>
		<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
		<script type="text/javascript" src="js/jquery-ui.js"></script>
		<script src="js/board.js" type="text/javascript"></script>
		<script src="js/gametypes.js" type="text/javascript"></script>
		<link rel="stylesheet" type="text/css" href="css/gametypes.css">
		
		<style>
			.gametypes {
				list-style-type: none;
			}
			
			.description {
				margin-top: 30px;
				display: block;
				width: 500px;
				font-family: monospace;
				font-size: 20px;
			}
			
			.expand {
				margin-top: 10px;
				margin-bottom: 50px;
				display: block;
			}
		</style>
	</head>
	
	<body>
		<div class="loading"></div>
		<button  class="cancel">Cancel</button>
		<p>Welcome ${x.name}</p>
		<div style="text-align: center; font-size: 30px; font-family: serif; margin-top: 20px; margin-bottom: 60px;">Available
			game modes</div>	
		<ul class="types"></ul>
		<span class="hidden" id="gameConfigData"/>${jsonGameTypes}</span>		
	</body>
</html>

