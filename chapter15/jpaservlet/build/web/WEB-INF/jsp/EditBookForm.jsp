<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Book</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
    </head>
    <body>
        <form method="post" action="update-book">
            <input type="hidden" name="id" value="${book.id}"/>
            <h1> 
                <span>Edit Book</span>
            </h1>
            <label>
                <span>ISBN:</span>
                <input type="text" name="isbn" required
                       value="${book.isbn}"/>
            </label>
            <label>
                <span>Title: </span>
                <input type="text" name="title" required
                    value="${book.title}"/>
            </label>
            <label>
                <span>Author: </span>
                <input type="text" name="author" required
                    value="${book.author}"/>
            </label>
            <label>
                <span>Price: </span>
                <input type="number" name="price" required
                       step="any" min="0" value="${book.price}"/>
            </label>
            <label>
                <span></span>
                <input type="submit"/>
            </label>
        </form>
    </body>
</html>