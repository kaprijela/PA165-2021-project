<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>eSports competitions</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <!-- Bootstrap icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <!-- Extra fonts from Google Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Rajdhani:wght@700&display=swap" rel="stylesheet">
    <!-- JQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- Angular JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-resource.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular-route.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
            crossorigin="anonymous"></script>
    <!-- Custom Angular routing controllers -->
    <script src="${pageContext.request.contextPath}/angular_app.js"></script>
    <!-- Custom CSS -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
<!-- navigation bar -->
<nav class="navbar navbar-expand-md navbar-dark mb-1" id="main-navbar">
    <div class="container">
        <a class="navbar-brand esports-title" href="#!/home">eSports<i class="bi bi-joystick ms-2"></i></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown"
                aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto">
                <!-- TODO change active item based on current page -->
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#!/home">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#!/players">Players</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#!/teams">Teams</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#!/competitions">Competitions</a>
                </li>
            </ul>

            <!-- TODO change when user is logged in -->
            <ul class="navbar-nav navbar-right">
                <li class="nav-item">
                    <a class="btn btn-outline-light" href="#!/login" id="login-button">Log in</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">

    <div ng-app="pa165esportspApp"><!-- AngularJS takes care of this element -->

        <!-- Bootstrap-styled alerts, visible when $rootScope.xxxAlert is defined -->
        <div ng-show="warningAlert" class="alert alert-warning alert-dismissible" role="alert">
            <button type="button" class="close" aria-label="Close" ng-click="hideWarningAlert()"><span
                    aria-hidden="true">&times;</span></button>
            <strong>Warning!</strong> <span>{{warningAlert}}</span>
        </div>
        <div ng-show="errorAlert" class="alert alert-danger alert-dismissible" role="alert">
            <button type="button" class="close" aria-label="Close" ng-click="hideErrorAlert()"><span aria-hidden="true">&times;</span>
            </button>
            <strong>Error!</strong> <span>{{errorAlert}}</span>
        </div>
        <div ng-show="successAlert" class="alert alert-success alert-dismissible" role="alert">
            <button type="button" class="close" aria-label="Close" ng-click="hideSuccessAlert()"><span
                    aria-hidden="true">&times;</span></button>
            <strong>Success !</strong> <span>{{successAlert}}</span>
        </div>

        <!-- the place where HTML templates are replaced by AngularJS routing -->
        <div ng-view></div>
    </div>

</div>
<!-- Footer -->
<!-- uncomment when it is properly sticky
<footer class="footer bg-dark"  style="position: relative; bottom: 0; width: 100%; padding-bottom: 0;">
    <div class="container">
        <span class="text-light">&copy;&nbsp;Masaryk University</span>
    </div>
</footer>
-->
</body>

</html>
