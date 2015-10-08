<%!
    public void jspInit() {
        System.out.println("jspInit ...");
    }
    public void jspDestroy() {
        System.out.println("jspDestroy ...");
    }
%>
<!DOCTYPE html>
<html>
<head><title>jspInit and jspDestroy</title></head>
<body>
Overriding jspInit and jspDestroy
</body>
</html>