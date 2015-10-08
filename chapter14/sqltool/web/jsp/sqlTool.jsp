<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>SQL Tool</title>
    <style>
        table, th, td {
            border: 1px solid #989898;
        }
    </style>
</head>
<body>
    <h1>SQL Tool</h1>
    <p style="color:red">${sqlResponse.error}</p>
    <form action="sqltool" method="post">
        <p>
            <textarea name="sql" cols="60" 
                      rows="7">${param.sql.trim()}</textarea>
        </p>
        <p><input type="submit"/>
    </form>
    <c:if test="${sqlResponse != null && sqlResponse.columns != null}">
        <h3>ResultSet</h3>
        <table>
            <tr>
            <c:forEach var="column" items="${sqlResponse.columns}">
                <th>${column}</th>
            </c:forEach>
            </tr>
            <c:forEach var="row" items="${sqlResponse.rows}">
            <tr>
                <c:forEach var="cell" items="${row}">
                    <td>${cell}</td>
                </c:forEach>
            </tr>
            </c:forEach>
        </table>
    </c:if>
</body>
</html>