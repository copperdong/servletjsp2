<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head><title>Testing variable</title></head>
<body>
Today's date:
<br/>
<tags:varDemo>
In long format: ${longDate}
<br/>
In short format: ${shortDate}
</tags:varDemo>
</body>
</html>