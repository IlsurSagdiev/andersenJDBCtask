<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hello, its main page!


<c:if test="${not empty error}">
    <span style="color: red">${error}</span>
</c:if>
<form action="/main" method="post">
    Name:<input type="text" name="name"><br>
    Password:<input type="password" name="password"><br>
    <button>Submit</button>
</form>

</body>
</html>