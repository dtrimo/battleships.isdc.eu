<!DOCTYPE html>
<html>
<head>
	<title>Sign In</title>
	<script type="text/javascript" src="js/jquery-1.11.3.js"></script>
	<script type="text/javascript" src="js/jquery-ui.js"></script>
	<script src="js/signin.js" type="text/javascript"></script>	
	<link rel="stylesheet" type="text/css" href="css/signin.css">
	
</head>

<body>
  <section class="container">
    <div>
      <h1>Sign In</h1>
      <form method="post" class="signin" onsubmit="return validate(this, '${message}');">
        <p> Username: <input type="text" name="username" placeholder="Username" required id="username"></p>
        <p> Password:<input class="password" type="password" name="password" placeholder="Password" required id="password"></p>
        <p> Confirm Password:<input class="repeatPassword" type="password" name="repeatPassword" placeholder="Confirm Password" required id="repeatPassword"></p>
        <p> Birthday: <input type="date" name="bday" required id="birthday"></p>
        <br>

        <!-- <p class="submit"><input type="submit" name="commit" value="Login"></p> -->
        <button type="submit" class="button-submit">Sign In</button>
      </form>
    </div>
  </section>

</body>
</html>