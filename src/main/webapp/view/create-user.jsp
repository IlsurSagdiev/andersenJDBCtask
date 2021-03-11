<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Creating New User</title>
</head>
<body>

<form method="post">
    <p>
    <h2>Enter your name</h2>
    <br>
        <input type="text" name="email"/>
    </p>
    <p>
    <h2>Enter your password</h2>
    <br>
        <input type="password" name="password"/>
    </p>
    <input type="submit" value="create"/>
</form>
<span>
                <%=(request.getAttribute("error") == null)
                        ? "" : request.getAttribute("error")%>
</span>
</body>
</html>