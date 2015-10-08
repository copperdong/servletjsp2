<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<!DOCTYPE html>
<html>
<head><title>Today's date</title></head>
<body>
<%
    DateFormat dateFormat = 
            DateFormat.getDateInstance(DateFormat.LONG);
    String s = dateFormat.format(new Date());
    out.println("Today is " + s);
%>
</body>
</html>