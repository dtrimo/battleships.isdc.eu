<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="js/require.js" data-main="js/gameTypeSelection/gameTypeSelection"></script>
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
		<p>Welcome ${userName}</p>
		<div style="text-align: center; font-size: 30px; font-family: serif; margin-top: 20px; margin-bottom: 60px;">Available
			game modes</div>	
		<ul class="types"></ul>
		<span class="hidden" id="gameConfigData">${jsonGameTypes}</span>		
	</body>
</html>

