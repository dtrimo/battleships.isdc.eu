<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="baseUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.localPort}${pageContext.request.contextPath}"/>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="baseUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.localPort}${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">window.baseUrl="<c:out value="${baseUrl}"/>";</script>
	<title>Log In</title>
	<script type="text/javascript" src="js/require.js" data-main="js/login/login"></script>
	<link rel="stylesheet" type="text/css" href="css/common-styles.css">
	<link rel="stylesheet" type="text/css" href="css/login-signup.css">
</head>

<body>
  <section class="container">
    <div class="formContainer">
      <h1>Log In to Battleships</h1>
      <form class="login" method="post">
        <p class="errorMessage">${message}</p>
        <div class="formfield required clearfix">
	      	<label>Username:</label>
	        <input type="text" name="username" placeholder="Username" required class="username" tabindex="1">
        </div>
        
        <div class="formfield required clearfix">
	        <label>Password:</label>
	        <input type="password" name="password" placeholder="Password" required class="password" tabindex="2">
        </div>
        
        <div class="formfield clearfix">
	        <a href="javascript:;" class="button submit" tabindex="3">Log In</a>     
        </div>
      </form>
        <p class="centered"> Don't have an account? <a href="signup" tabindex="4">Sign Up</a></p>
    </div>
  </section>
</body>
</html>