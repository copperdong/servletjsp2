<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Books</title>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
    </head>
    <body class="centered">
        <header>
            <a class="buttonLink" href="<c:url value="add-book"/>">Add Book</a>
        </header>
        <c:if test="${empty books}">
            You have not added any books.
        </c:if>
        <c:forEach var="book" items="${books}" varStatus="status">
            <c:if test="${status.first}">
                <table>
                    <tr>
                        <th>ISBN</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Price</th>
                        <th>&nbsp;</th>
                        <th>&nbsp;</th>
                    </tr>
            </c:if>
                    <tr>
                        <td>${book.isbn}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.price}</td>
                        <td><a href="edit-book?id=${book.id}">Edit</a></td>
                        <td><a href="delete-book?id=${book.id}">Delete</a></td>
                    </tr>
            <c:if test="${status.last}">
                </table>
            </c:if>
        </c:forEach>
    </body>
</html>