<!DOCTYPE HTML>
<!--${cp=pageContext.request.contextPath}-->
${session.setMaxInactiveInterval(60)}
<html>
   <head>
       <title>Important page</title>
   </head>
   <body>
        <p>This page contains confidential information that must be erased when
           the user session on the server expires</p>
        <script type="text/javascript">
            if ("WebSocket" in window) {
                var uri = "ws://" + document.location.host 
                        + "${cp=="/"? "" : cp}" + "/config";
                var ws = new WebSocket(uri);
                ws.onmessage = function (event) {
                    if (event.data == "expired") {
                        window.location.href = "expired.html";
                    }
                };
            }
        </script>
   </body>
</html>