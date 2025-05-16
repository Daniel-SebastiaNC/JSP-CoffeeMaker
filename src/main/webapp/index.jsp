<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <h1><%= "Hello World!" %></h1>
    <br/>
    <a href="hello-servlet">Hello Servlet</a>
    <c:forEach var="i" begin="1" step="1" end="10">
       <p>test ${i}</p>
    </c:forEach>
</body>
</html>