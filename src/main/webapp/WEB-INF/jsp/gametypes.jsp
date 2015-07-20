<!DOCTYPE html>
<%@page import="org.json.JSONArray"%>
<html>
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.util.List"%>
<%@page import="eu.isdc.internship.db.dto.AvailableBattleshipDTO"%>
<%@page import="eu.isdc.internship.db.dto.GameTypeDTO"%>
<%@page import="eu.isdc.internship.db.dto.PositionDTO"%>

<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
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
	<script src="js/board.js" type="text/javascript"></script>
	<script src="js/gametypes.js" type="text/javascript"></script>
	<div
		style="text-align: center; font-size: 30px; font-family: serif; margin-top: 20px; margin-bottom: 60px;">Available
		game modes</div>
	
	<ul class="types">
		<%	
			@SuppressWarnings("unchecked")
			List<GameTypeDTO> gtlist = (List<GameTypeDTO>) request.getAttribute("gametypesList");
			int i = 0;
			JSONObject obj;
			
		%>
		<c:forEach items="${gametypesList}" var="gametype">
			<% GameTypeDTO gtDTO = gtlist.get(i); 
			JSONArray arr = new JSONArray();
			%>
			<li>
			<span class="description">${gametype.shortDescription}</span>
				<%
					for(AvailableBattleshipDTO abDTO : gtDTO.getAvailableBTs()){
						obj = new JSONObject();
						JSONArray x = new JSONArray();
						JSONArray y = new JSONArray();
						for(PositionDTO pDTO : abDTO.getModel().getPositions()){
							x.put(pDTO.getX());
							y.put(pDTO.getY());
						}
						obj.put("x", x);
						obj.put("y", y);
						arr.put(obj);
				}
				%>
				<button type="button" class="play">Play this!</button>
				<button type="button" class="expand">Show available battleships</button>
				<div class="board-container" id = "<%='c'+String.valueOf(i)%>"></div>
				</li>
				<script>
					<%if(arr.length() != 0){ %>
						draw(<%=arr%>, '<%="c"+String.valueOf(i)%>');
					<%}%>
				</script>
				<% i++; %>
		</c:forEach>
		
	</ul>
	
	<script src="js/gametypes2.js" type="text/javascript"></script>
	
	<%-- <c:forEach items="${gametype.availableBTs}" var="availableBTs">
				<c:set var="model" scope="session" value="${availableBTs.model}"/>
				<c:forEach items="${model.positions}" var="positions">
					
				</c:forEach>
			</c:forEach> --%>
	
	 
 
</body>
</html>

