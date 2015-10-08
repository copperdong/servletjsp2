<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <title>Mail Web UI</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/style.css"/>">
</head>
<body>
    <%@include file="Folders.jspf"%>
    <div style="float:none;padding:10px;width:auto;overflow:hidden">
        <div style="text-align:right;padding:5px;color:teal">
            Messages ${1 + (page-1) * rowsPerPage} - ${(page-1) * rowsPerPage + fn:length(messages)}
            <c:if test="${page > 1}">
                <a class="composeButton" href="main?folder=${folder}&page=${page-1}"><</a>
            </c:if>
            <c:if test="${page < maxPage}">
                <a class="composeButton" href="main?folder=${folder}&page=${page+1}">></a></span>
            </c:if>
        </div>
        <table>
            <tr>
                <th>From</th>
                <th>Subject</th>
                <th>Received</th>
            </tr>
            <c:forEach var="message" items="${messages}">
                <tr>
                    <td><a href="message?id=${message.id}&folder=${param.folder}">${message.from}</a></td>
                    <td><a href="message?id=${message.id}&folder=${param.folder}">${message.subject}</a></td>
                    <c:set value="${message.receivedDate}" var="rd"/>
                    <td>${rd.monthValue}/${rd.dayOfMonth}/${rd.year} 
                        <fmt:formatNumber value="${rd.hour}" type="number" 
                            minIntegerDigits="2"/>:<fmt:formatNumber 
                            value="${rd.minute}" type="number" minIntegerDigits="2"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>