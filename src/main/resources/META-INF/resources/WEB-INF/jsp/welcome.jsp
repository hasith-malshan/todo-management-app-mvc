<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <%@include file="common/bootstraoStyles.jspf"%>
    <title>Welcome</title>
</head>

<style>
    html,body{
        height: 100%;
    }
</style>

<body>
<%@include file="common/navigation.jspf"%>
    <div class="d-flex align-items-center justify-content-center">
        <h1 class="text-center">Welcome to Todo Management Application </h1><span class="text-capitalize h1">&nbsp;${name}</span>
    </div>
</body>
</html>