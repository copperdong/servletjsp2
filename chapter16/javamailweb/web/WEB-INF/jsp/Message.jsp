<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Message Details</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
    <%@include file="Folders.jspf"%>
    <div style="float:none;padding:10px;width:auto;overflow:hidden">
        <p>From: ${message.from}</p>
        <p>Subject: ${message.subject}</p>
        <c:if test="${message.attachments != null}">
            Attachments:
            <c:forEach var="attachment" items="${message.attachments}" varStatus="status">
                <a href="attachment?folder=${folder}&mid=${message.id}&file=${attachment}">
                    ${attachment}</a><c:if test="${!status.last}">,</c:if>
            </c:forEach>
        </c:if>
        <p>${message.text}</p>
    
    </div>

</body>
</html>