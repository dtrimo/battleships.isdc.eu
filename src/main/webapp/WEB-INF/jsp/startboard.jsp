<!DOCTYPE html>
<html>
<head>
	<title>Board</title>
	<script type="text/javascript" src="js/require.js" 
		data-main="js/boardConfiguration/boardConfigurationScript"></script>
	<link rel="stylesheet" type="text/css" href="css/gameboard.css">
</head>

<body>
	<span class="hidden" id="gameConfigData">${jsonGameTypeData}</span>
	<div class="ships-container"></div>
	<div class="board-container" oncontextmenu = "return false;"></div>
	  <form class="game" method="post"><p><input type="submit" value="Da click"></p></form>
	<div class="transformation-container">
		<button type="button" class="transform rotateCounterClockwise">Left</button>
		<button type="button" class="transform reflectX">Reflect
			X</button>
		<button type="button" class="transform reflectY">Reflect
			Y</button>
		<button type="button" class="transform rotateClockwise">Right</button>
	</div>
</body>
</html>