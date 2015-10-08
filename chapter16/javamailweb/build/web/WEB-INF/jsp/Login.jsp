<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
    </head>
    <body>
        <form action="login" method="post">
            <h1>Login
                <span>Please enter your username and password</span>
            </h1>
            <label>
                <span>User Name :</span>
                <input type="text" name="userName" 
                       placeholder="UserName or email" required/>
            </label>
            <label>
                <span>Password :</span>
                <input id="email" type="password" name="password" 
                       placeholder="Your password" required />
            </label>
            <label>
                <span>&nbsp;</span> 
                <input type="submit" value="Login"/> 
            </label>    
        </form>
    </body>
</html>