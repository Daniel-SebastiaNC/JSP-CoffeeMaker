<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>CoffeeMaker - Home</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
  <%@include file="header.jsp" %>

  <main class="container">
    <c:if test="${empty user}">
      <h1 class="mt-3">Você não está autenticado!</h1>
      <h5>Para acessar o sistema, identifique-se!</h5>
    </c:if>
    <c:if test="${not empty user}">
      <h1 class="mt-3">Bem-vindo a FiapStore!</h1>
      <h5>Seu login foi efetuado com sucesso!</h5>
    </c:if>
  </main>

  <script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>