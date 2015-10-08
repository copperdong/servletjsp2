<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Add Book</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
    </head>
    <body>
        <form method="post" action="save-book">
            <h1> 
                <span>Add Book</span>
            </h1>
            <label>
                <span>ISBN</span>
                <input type="text" name="isbn" required/>
            </label>
            <label>
                <span>Title: </span>
                <input type="text" name="title" required/>
            </label>
            <label>
                <span>Author: </span>
                <input type="text" name="author" required/>
            </label>
            <label>
                <span>Price: </span>
                <input type="number" name="price" step="any" 
                       min="0" required/>
            </label>
            <label>
                <span></span>
                <input type="submit"/>
            </label>
        </form>
    </body>
</html>