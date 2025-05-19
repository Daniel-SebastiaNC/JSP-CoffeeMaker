<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Mouse wireless</td>
                            <td class="text-end">100</td>
                            <td class="text-end">30.99</td>
                            <td class="text-center">15-09-2024</td>
                        </tr>
                        <tr>
                            <td>Monitor LED 23P</td>
                            <td class="text-end">16</td>
                            <td class="text-end">700.00</td>
                            <td class="text-center">21-11-2023</td>
                        </tr>
                        <tr>
                            <td>Impressora HP</td>
                            <td class="text-end">6</td>
                            <td class="text-end">768.99</td>
                            <td class="text-center">09-04-2024 </td>
                        </tr>
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