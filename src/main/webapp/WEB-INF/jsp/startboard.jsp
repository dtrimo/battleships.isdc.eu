<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="baseUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.localPort}${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">window.baseUrl="<c:out value="${baseUrl}"/>";</script>
	<base href="${baseUrl}/"></base>
	<title>Board</title>
	<script type="text/javascript" src="js/lib/sockjs.js"></script>
	<script type="text/javascript" src="js/lib/stomp.js"></script>
	<script type="text/javascript" src="js/require.js" 
		data-main="js/boardConfiguration/boardConfigurationScript"></script>
	<link rel="stylesheet" type="text/css" href="css/common-styles.css">
	<link rel="stylesheet" type="text/css" href="css/gameboard.css">
	<link rel="stylesheet" type="text/css" href="css/basic-game-board.css">
</head>

<body>
	<div class="game-selection-screen clearfix">
		<span class="hidden" id="gameId">${gameId}</span>
		<span class="hidden" id="userId">${userId}</span>
		<span class="hidden" id="startConfigId">${startConfigId}</span>
		<span class="hidden" id="gameConfigData">${jsonGameTypeData}</span>
		<span class="hidden" id="startConfigData">${jsonStartConfigData}</span>
		<div class="ships-container"></div>
		<div class="board-container" oncontextmenu = "return false;"></div>
		<div class="transformation-container">
			<button type="button" class="transform rotateCounterClockwise">Left</button>
			<button type="button" class="transform reflectX">Reflect
				X</button>
			<button type="button" class="transform reflectY">Reflect
				Y</button>
			<button type="button" class="transform rotateClockwise">Right</button>
		</div>
		<div class="right-items clearfix">
			<div class="box transparent-black-background clock">
				<span class="title">Remaining time</span>
				<span class="remaining-time">01:00</span>
			</div>
			<div class="controls box start transparent-black-background clearfix">
				<a href="javascript:;" class="button submit start-game disabled">Start game</a>
			</div>
			<div class="controls hidden waiting box transparent-black-background clearfix">
				<span></span>
			</div>
		</div>
		<div class="disabling-overlay"></div>
	</div>
</body>
</html>