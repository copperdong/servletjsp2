<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><title>Customers</title>
    <style>
        table, th, td {
            border: 1px solid #898933;
        }
    </style>
</head>
<body>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Address Line 1</th>
            <th>Address Line 2</th>
            <th>City</th>
            <th>Zip Code</th>
            <th>&nbsp;</td>
        </tr>
    <c:forEach items="${customers}" var="customer">
        <tr>
            <td>${customer.id}</td>
            <td>${customer.name}</td>
            <td>${customer.addressLine1}</td>
            <td>${customer.addressLine2}</td>
            <td>${customer.city}</td>
            <td>${customer.zipCode}</td>
            <td><a href="edit-customer?id=${customer.id}">Edit</a></td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>