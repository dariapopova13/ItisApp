<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html >
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../resources/image/itis_logo_small.png">
    <title>Вход</title>
    <!-- Bootstrap core CSS-->
    <link href="../../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../../resources/css/sb-admin.css" rel="stylesheet">
</head>

<body class="bg-dark">
<div class="container">
    <div class="card card-login mx-auto mt-5">
        <div class="card-header">Вход</div>
        <div class="card-body">
            <form>
                <div class="form-group">
                    <label for="login">Логин</label>
                    <input class="form-control" id="login" type="text"
                           placeholder="Введите логин">
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label>
                    <input class="form-control" id="password" type="password" placeholder="Введите пароль">
                </div>
                <div class="form-group">
                    <div class="form-check">
                        <label class="form-check-label">
                            <input class="form-check-input" type="checkbox">Запомнить пароль</label>
                    </div>
                </div>
                <a class="btn btn-primary btn-block" href="../../resources/index.html">Войти</a>
            </form>
            <div class="text-center">
                <%--<a class="d-block small mt-3" href="../../resources/register.html">Register an Account</a>--%>
                <%--<a class="d-block small" href="forgot-password.html">Forgot Password?</a>--%>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="../../resources/vendor/jquery/jquery.min.js"></script>
<script src="../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
</body>

</html>
