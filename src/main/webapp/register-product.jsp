<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Cadastro de produtos</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./resources/css/bootstrap.css">
</head>
<body>
  <%@include file="header.jsp" %>

  <main class="container">

    <div class="mt-5 ms-5 me-5">
      <div class="card mb-3">

        <div class="card-header">
          CADASTRO DE PRODUTO
        </div>

        <div class="card-body">

          <form action="produto" method="post">

            <div class="form-group">
              <label for="id-nome">Nome</label>
              <input type="text" name="name" id="id-nome" class="form-control">
            </div>

            <div class="form-group">
              <label for="id-valor">Valor</label>
              <input type="number" name="value" id="id-valor" class="form-control">
            </div>

            <div class="form-group">
              <label for="id-quantidade">Quantidade</label>
              <input type="number" name="quantity" id="id-quantidade" class="form-control">
            </div>

            <div class="form-group">
              <label for="id-fabricacao">Data de Fabricação</label>
              <input type="date" name="dateCreated" id="id-fabricacao" class="form-control">
            </div>

            <input type="submit" value="Salvar" class="btn btn-primary mt-3">

          </form>
        </div>

      </div>
    </div>

  </main>

  <%@include file="footer.jsp" %>
  <script src="resources/js/bootstrap.bundle.js"></script>
</body>
</html>