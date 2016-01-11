<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="baseUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.localPort}${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">window.baseUrl="<c:out value="${baseUrl}"/>";</script>
	<title>Home</title>
	<base href="${baseUrl}/"></base>
	<script type="text/javascript" src="js/lib/sockjs.js"></script>
	<script type="text/javascript" src="js/lib/stomp.js"></script>
	<script type="text/javascript" src="js/lib/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="js/lib/jquery-ui.js"></script>
	<script type="text/javascript" src="js/lib/angular-1.4.5.js"></script>
	<script type="text/javascript" src="js/lib/angular-resource.js"></script>
	<script type="text/javascript" src="js/angular/modules/users.js"></script>
	<script type="text/javascript" src="js/angular/modules/chat.js"></script>
	<script type="text/javascript" src="js/angular/modules/uiUtilities.js"></script>
	<script type="text/javascript" src="js/angular/modules/onlinePresence.js"></script>
	<script type="text/javascript" src="js/homepage/homepage.js"></script>
	<script type="text/javascript" src="js/require.js" data-main="js/gameTypeSelection/gameTypeSelection"></script>

	<link rel="stylesheet" type="text/css" href="css/common-styles.css">
	<link rel="stylesheet" type="text/css" href="css/basic-game-board.css">
	<link rel="stylesheet" type="text/css" href="css/homepage.css">	
	<link rel="stylesheet" type="text/css" href="css/gametypes.css">
	
</head>
<body ng-app="homepage" ng-controller="HomepageController" class="clearfix">
	<input type="hidden" id="baseUrl" value="${baseUrl}"/>
	<div logged-in-header ng-controller="HeaderController"></div>

	<div ng-controller="FriendsController" class="chat-box">
		<div friends-list></div>
		<div chat-manager></div>	
	</div>

	<div class="available-game-types box transparent-black-background">
		<div class="loading">
			<a href="javascript:;" class="button cancel-game-request">Cancel</a>
		</div>
		<div class="box-title">Available game modes</div>	
		<ul class="types"></ul>
		<span class="hidden" id="gameConfigData">${jsonGameTypes}</span>
	</div>

	<div class="mask"></div>
</body>
</html>