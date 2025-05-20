<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>CoffeeMaker</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
    <%@include file="header.jsp" %>
    <main class="container">

        <div class="mt-5 ms-5 me-5">
            <div class="card mb-3">

                <div class="card-header">
                    LISTA DE PRODUTOS
                </div>

                <div class="card-body">
                    <h5 class="card-title">Gestão de produdos eficiente</h5>
                    <p class="card-text">Mantenha os dados dos seus produtos sempre atualizados e acessíveis.</p>
                    <table class="table table-striped table-bordered">

                        <thead>
                        <tr>
                            <th>Nome</th>
                            <th class="text-end">Quantidade</th>
                            <th class="text-end">Valor</th>
                            <th class="text-center">Data de fabricação</th>
                            <th class="text-center"></th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <td>${product.name}</td>
                                <td class="text-end">
                                    <fmt:formatNumber value="${product.value}"/>
                                </td>
                                <td class="text-end">${product.quantity}</td>
                                <td class="text-center">
                                    <fmt:parseDate
                                        value="${product.dateCreated}"
                                        pattern="yyyy-MM-dd"
                                        var="dateCreatedFmt"/>
                                    <fmt:formatDate
                                        value="${dateCreatedFmt}"
                                        pattern="dd/MM/yyyy"/>
                                </td>
                                <td class="text-center">
                                    <c:url value="product" var="link">
                                        <c:param name="action" value="open-edition-form"/>
                                        <c:param name="id" value="${product.id}"/>
                                    </c:url>
                                    <a href="${link}" class="btn btn-primary">Editar</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                    <a href="register-product.jsp" class="btn btn-primary">Adicionar produto</a>
                </div>

            </div>

        </div>

    </main>
    <%@include file="footer.jsp" %>

    <script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>