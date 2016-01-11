<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="baseUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.localPort}${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">window.baseUrl="<c:out value="${baseUrl}"/>";</script>
	<base href="${baseUrl}/"></base>
	<title>Game screen</title>
	<script type="text/javascript" src="js/lib/sockjs.js"></script>
	<script type="text/javascript" src="js/lib/stomp.js"></script>
	<script type="text/javascript" src="js/require.js" 
		data-main="js/gameScreen/gameScreen"></script>
	<link rel="stylesheet" type="text/css" href="css/common-styles.css">
	<link rel="stylesheet" type="text/css" href="css/basic-game-board.css">
	<link rel="stylesheet" type="text/css" href="css/gameScreen.css">
</head>

<body>
	<div class="game-screen clearfix">
		<span class="hidden" id="gameId">${gameId}</span>
		<span class="hidden" id="userId">${userId}</span>
		<span class="hidden" id="startConfigId">${startConfigId}</span>
		<span class="hidden" id="gameConfigData">${jsonGameTypeData}</span>
		<span class="hidden" id="startConfigData">${jsonStartConfigData}</span>
		<div class="boards clearfix">
			<div class="player-board float-right">
				<div class="title transparent-black-background box">Opponent
					<span class="opponent-hit-count">0</span>
				</div>
				<div id="current-player-board" class="board-container" oncontextmenu = "return false;"></div>		
			</div>
			
			<div class="box transparent-black-background clock">
				<span class="title">Round</span>
				<span class="round">1</span>
				<span class="title">Remaining time</span>
				<span class="remaining-time">00:20</span>
				<span class="title">Elapsed time</span>
				<span class="elapsed-time">00:00</span>
			</div>
			
			<div class="player-board float-left">
				<div class="title transparent-black-background box">You
					<span class="my-hit-count">0</span>
				</div>
				<div id="opponent-board" class="board-container" oncontextmenu = "return false;"></div>					
			</div>
		</div>
	</div>
</body>
</html>