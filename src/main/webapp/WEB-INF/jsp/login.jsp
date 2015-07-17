<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
<!DOCTYPE html>
<html>
<head>
	<title>Log In</title>
	<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script src="js/login.js" type="text/javascript"></script>	
	<link rel="stylesheet" type="text/css" href="css/login.css">
</head>

<body>

  <section class="container">
    <div>
      <h1>Log In to Battleships</h1>
      <form class="login" method="post" onsubmit="show('${message}');">
        <p><sup>*</sup><input type="text" name="username" placeholder="Username" required id="username"></p>
        <p><sup>*</sup><input type="password" name="password" placeholder="Password" required id="password"></p>
        <p><input type="submit" value="Log In"></p>
        <!-- <p id="message">${message}</p> -->
      </form>
        <p id="signin"> Don't have an account? <a href="signin.html" class="button">Sign In</a></p>
    </div>
  </section>

</body>
</html>