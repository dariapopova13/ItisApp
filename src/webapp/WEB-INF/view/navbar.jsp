<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Главная</title>
    <!-- Bootstrap core CSS-->
    <link href="../../resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="../../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Custom styles for this template-->
    <link href="../../resources/css/sb-admin.css" rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<!-- Navigation-->


<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">

    <a class="navbar-brand" href="/user/main">
        Высшая школа ИТИС</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse"
            data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
            aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">

            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="News">
                <a class="nav-link" href="/user/main/news">
                    <i class="fa fa-fw fa-newspaper-o"></i>
                    <span class="nav-link-text">Новости</span>
                </a>
            </li>

            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Courses">
                <a class="nav-link" href="/user/main/courses">
                    <i class="fa fa-fw fa-graduation-cap"></i>
                    <span class="nav-link-text">Курсы</span>
                </a>
            </li>

            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="Professors">
                <a class="nav-link" href="/user/main/professors">
                    <i class="fa fa-fw fa-id-card"></i>
                    <span class="nav-link-text">Преподаватели</span>
                </a>
            </li>

            <li class="nav-item" data-toggle="tooltip" data-placement="right" title="User">
                <a class="nav-link" href="/user/main/me">
                    <i class="fa fa-fw fa-user"></i>
                    <span class="nav-link-text">Личный кабинет</span>
                </a>
            </li>


        </ul>
        <ul class="navbar-nav sidenav-toggler">
            <li class="nav-item">
                <a class="nav-link text-center" id="sidenavToggler">
                    <i class="fa fa-fw fa-angle-left"></i>
                </a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <form class="form-inline my-2 my-lg-0 mr-lg-2">
                    <div class="input-group">
                        <input class="form-control" type="text" placeholder="Поиск">
                        <span class="input-group-append">
                <button class="btn btn-primary" type="button">
                  <i class="fa fa-search"></i>
                </button>
              </span>
                    </div>
                </form>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" data-target="#exampleModal">
                    <i class="fa fa-fw fa-sign-out"></i>Выход</a>
            </li>
        </ul>
    </div>
</nav>

<div class="content-wrapper">
    <div class="container-fluid">


    </div>

    <footer class="sticky-footer">
        <div class="container">
            <div class="text-center">
                <small>Copyright © ItisApp 2018</small>
            </div>
        </div>
    </footer>

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fa fa-angle-up"></i>
    </a>
    <!-- Logout Modal-->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="/user/main">Logout</a>
                </div>
            </div>
        </div>
    </div>


</div>
</body>
<!-- Bootstrap core JavaScript-->
<script src="../../resources/vendor/jquery/jquery.min.js"></script>
<script src="../../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="../../resources/vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="../../resources/js/sb-admin.min.js"></script>
<!-- Custom scripts for this page-->
<!-- Toggle between fixed and static navbar-->
<script>
    $('#toggleNavPosition').click(function () {
        $('body').toggleClass('fixed-nav');
        $('nav').toggleClass('fixed-top static-top');
    });

</script>
<!-- Toggle between dark and light navbar-->
<script>
    $('#toggleNavColor').click(function () {
        $('nav').toggleClass('navbar-dark navbar-light');
        $('nav').toggleClass('bg-dark bg-light');
        $('body').toggleClass('bg-dark bg-light');
    });

</script>
</html>
