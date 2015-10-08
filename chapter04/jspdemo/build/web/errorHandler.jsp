<%@page isErrorPage="true"%>
<!DOCTYPE html><html>
<head><title>Error</title></head>
<body>
An error has occurred. <br/>
Error message: 
<%
    out.println(exception.toString());
%>
</body>
</html>