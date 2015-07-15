<!DOCTYPE html>
<html>
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
	
	<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<link rel="stylesheet" type="text/css" href="css/gameboard.css">
	<script src="js/gametypes.js" type="text/javascript"></script>	
	
	<style>
		.gametypes{
			
			list-style-type: none;
		}
		.description{
			margin-top:30px;
			display: block;
			width:500px;
			font-family: monospace;
			font-size: 20px;
			
		}
		.expand{
			margin-top:10px;
			margin-bottom:50px;
			display:block;
			
		}
		
	</style>
</head>

<body>
	<div style="text-align: center; font-size: 30px; font-family: serif; margin-top:20px;margin-bottom:60px;">Available game modes</div>
	<ul class="gametypes">
		<c:forEach var="gametype" begin= end=>  
		  <li><span class="description">${description}</span> 
			<button type="button" class="expand" on>Show available battleships</button>
			<div class="ships-container"></div>
		</c:forEach>
		<!-- <li><span class="description">Beginner(5 x 5): bsdfhkfgklsdgkkdghksdhgk fsdgs  gsdgs hfjsd dghjsdgh hjdg sgdjk tjkts itdjs m jjo 2djd sfdfdjf</span> 
			<button type="button" class="expand">Show available battleships</button>
			<div class="ships-container"></div>
		</li>
		<li><span class="description">Interediate(9 x 9): bsdfhkfgklsdgkkdghksdhgk fsdgs  sgdjk tjkts itdjs m jjo 2djd sfdfdjf</span> 
			<button type="button" class="expand">Show available battleships</button>
		</li>
		<li><span class="description">Expert(15 x 15): bsdfhkfgklsdgkkdghksdhgk fsdgs  sgdjk tjkts itdjs m jjo 2djd sfdfdjf</span> 
			<button type="button" class="expand">Show available battleships</button> -->
		</li>
	</ul>
</body>
</html>

