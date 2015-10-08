<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Compose</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
    <form action="send" method="post">
        <h1>Compose
            <span>Write your email</span>
        </h1>
        <label>
            <span>To :</span>
            <input type="text" name="to" 
                placeholder="Email of recipient" required/>
        </label>
        <label>
            <span>Subject :</span>
            <input type="text" name="subject" 
                placeholder="Subject"/>
        </label>
        <label>
            <span>Text :</span>
            <textarea name="text" 
                      placeholder="Message"></textarea>
        </label>
        <label>
            <span>&nbsp;</span> 
            <input type="submit" value="Send"/> 
        </label>    
    </form>
</body>
</html>