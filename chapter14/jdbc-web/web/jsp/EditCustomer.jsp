<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
    <form action="update-customer" method="post">
        <input type="hidden" name="id" value="${customer.id}"/>
        <h1>Edit Customer
            <span>Please update the fields below</span>
        </h1>
        <label>
            <span>Name :</span>
            <input type="text" name="name" 
                value="${customer.name}" required/>
        </label>
        <label>
            <span>Address :</span>
            <input type="text" name="addressLine1" 
                value="${customer.addressLine1}"/>
        </label>
        <label>
            <span>&nbsp;</span>
            <input type="text" name="addressLine2" 
                value="${customer.addressLine2}"/>
        </label>
        <label>
            <span>City :</span>
            <input type="text" name="city" 
                value="${customer.city}"/>
        </label>
        <label>
            <span>Zip Code :</span>
            <input type="text" name="zipCode" 
                value="${customer.zipCode}"/>
        </label>
        <label>
            <span>&nbsp;</span> 
            <input type="submit" value="Update"/> 
        </label>    
    </form>
</body>
</html>