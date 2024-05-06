<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.css" rel="stylesheet" >
    <title>Add todo</title>
</head>
<body>
<%@include file="common/navigation.jspf"%>
<div class="container">
    <h1>This is Add to JSP</h1>
    <%--@elvariable id="todo" type="com.hasith.todomanagementbyme.controllers.todo.Todo"--%>
    <form:form method="post" modelAttribute="todo">

        <fieldset class="mb-3">
            <form:label path="description">Description</form:label>
            <form:input class="form-control" name="description" id="description" path="description" type="text"
                        required="required"/>
            <form:errors path="description" cssClass="text-danger"/>
        </fieldset>

        <fieldset class="mb-3">
            <form:label path="targetDate">Target Date</form:label>
            <form:input class="form-control" name="description" id="targetDate" path="targetDate" type="text"
                        required="required"/>
            <form:errors path="targetDate" cssClass="text-danger"/>
        </fieldset>

        <form:input class="form-control" name="id" id="id" path="id" type="hidden"/>
        <form:input class="form-control" name="done" id="done" path="done" type="hidden"/>
        <input type="submit" value="submit" class="btn btn-primary">
    </form:form>
</div>


<script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
<script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
<script>
    $('#targetDate').datepicker({
        format: 'yyyy-mm-dd',
        startDate: '-3d'
    });
</script>
</body>
</html>