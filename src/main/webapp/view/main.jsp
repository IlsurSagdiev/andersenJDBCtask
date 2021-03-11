<%--
  Created by IntelliJ IDEA.
  User: IlsurEnergy
  Date: 09.03.2021
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<h2>Hello Visitor!</h2>
<form method="post" action="/login">
    <p>
        Please Enter your name <br>
        <input type="text" name="name" placeholder="name">
    </p>
    <p>
        password <br>
        <input type="password" name="password" placeholder="password">
    </p>
    <button type="submit">Login</button>
</form>
<form action="/create">
    <button type="submit">Create</button>
</form>
<span style="color:red">
                <%=(request.getAttribute("error") == null)
                        ? "" : request.getAttribute("error")%>
</span>
<span style="color:darkgreen">
                <%=(request.getAttribute("success") == null)
                        ? "" : request.getAttribute("success")%>
</span>
</body>
</html>
