<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>Cadastro de visitante</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/stylepgvisitante.css">
</head>

<body>

    <div class="main">
        <form action="<%=request.getContextPath()%>/ServletVisitante" class="cad-form" method="post">
            <h3>Cadastro de visitante.</h3>
            <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Login:</label>
                <input type="text" class="form-control" id="none" name="login" aria-describedby="emailHelp" required>
              </div>

              <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Senha:</label>
                <input type="text" class="form-control" id="senha" name="senha" aria-describedby="emailHelp" required>
              </div>
               <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp" required>
              </div>
               <div class="mb-3">
                <label for="exampleInputEmail1" class="form-label">Nome:</label>
                <input type="text" class="form-control" id="nome" name="nome" aria-describedby="emailHelp" required>
              </div>
              <div>${msg}</a></div>
              <button type="submit">Salvar</button>
        </form>

    </div>
      
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>

        <script>

            
        </script>
</body>

</html>