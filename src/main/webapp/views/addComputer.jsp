<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="views/dashboard.html"> Application - Computer Database </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1>Add Computer</h1>
                    <form action="AddServlet" method="POST" name = "addComputer">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" name="computerName" id="computerName" value="${computerToAdd.name}" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" name="introduced" id="introduced" value="${computerToAdd.introduced}" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" name="discontinued" id="discontinued" value="${computerToAdd.discontinued}" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="company">Company</label>
                                <select class="form-control" name="company" id="company" value="${computerToAdd.company.name}" >
                                  <c:forEach var="company" items="${listCompany}">
                                    <option value="${company.id}"><c:out value = "${company.name}"/></option>
                                  </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div  class="form-group">
                        <c:out value ="${erreur}"></c:out>
                        </div>
                        <div class="actions pull-right">
                            <input type="submit" value="Add" class="btn btn-primary">
                           
                            or
                            <a href="dashboard.jsp" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
    <script src="js/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/jquery.validation/1.15.1/jquery.validate.min.js"></script>
    <script src="js/frontValidation.js"></script>
</body>
</html>