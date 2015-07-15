<!DOCTYPE html>
<html>
<head>
	<title>Board</title>
	<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="css/gameboard.css">
	<script src="js/board.js" type="text/javascript"></script>
	<script src="js/selectionBoard.js" type="text/javascript"></script>
	<script src="js/gameSelectionScript.js" type="text/javascript"></script>	
	<style>
		.active {
			background-color: grey;
		}
	</style>
</head>

<body>
	<div class="ships-container"></div>
	<div class="board-container"></div>
	<div class="transformation-container">
		<button type="button" class="transform" id="left">Left</button>
		<button type="button" class="transform" id="reflectX">Reflect X</button>		
		<button type="button" class="transform" id="reflectY">Reflect Y</button>
		<button type="button" class="transform" id="right">Right</button>
		<div class="transformation-grid"></div>
	</div>
</body>
</html>