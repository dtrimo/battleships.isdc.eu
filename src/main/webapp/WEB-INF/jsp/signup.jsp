<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<c:set var="baseUrl" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.localPort}${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">window.baseUrl="<c:out value="${baseUrl}"/>";</script>
	<title>Sign In</title>
	<script type="text/javascript" src="js/require.js" data-main="js/signup/signup"></script>
	<link rel="stylesheet" type="text/css" href="css/common-styles.css">	
	<link rel="stylesheet" type="text/css" href="css/login-signup.css">	
</head>

<body>
  <section class="container">
    <div class="formContainer">
      <h1>Sign up to Battleships</h1>
      <form class="signup" method="post">
        <p class="errorMessage">${message}</p>
        <div class="formfield required clearfix">
	      	<label>Username:</label>
	        <input type="text" name="username" placeholder="Username" required class="username" tabindex="1">
        </div>
        
        <div class="formfield required clearfix">
	        <label>Password:</label>
	        <input type="password" name="password" placeholder="Password" required class="password" tabindex="2">
        </div>
        
        <div class="formfield required clearfix">
	        <label>Confirm password:</label>
	        <input type="password" name="repeatPassword" placeholder="Confirm password" required class="password" tabindex="3">
        </div>
        
        <div class="formfield required clearfix">
	        <label>Birthday:</label>
	        <input type="date" name="bday" required tabindex="4">
        </div>
        
        <div class="formfield clearfix">
	        <a href="javascript:;" class="button submit" tabindex="5">Sign up</a>     
        </div>
      </form>
        <p class="centered"> Already have an account? <a href="login" tabindex="6">Log in</a></p>
    </div>
  </section>
</body>
</html>