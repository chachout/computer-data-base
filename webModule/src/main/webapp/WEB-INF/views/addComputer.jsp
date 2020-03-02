<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<title><spring:message code="cdb.title"/></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapStyle" />
<spring:url value="/resources/css/font-awesome.css" var="fontAweSomeStyle" />
<spring:url value="/resources/css/main.css" var="mainCss" />

<link href="${bootstrapStyle}" rel="stylesheet" media="screen">
<link href="${fontAweSomeStyle}" rel="stylesheet" media="screen">
<link href="${mainCss}" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <a class="navbar-brand" href="dashboard"> <spring:message code="cdb.title"/> </a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="Add"/></h1>
                    <form action="addComputer" method="POST" name = "addComputer">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="Computer"/></label>
                                <input type="text" class="form-control" name="name" id="computerName" value="${computerToAdd.name}" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="Introduced"/></label>
                                <input type="date" class="form-control" name="introduced" id="introduced" value="${computerToAdd.introduced}" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="Discontinued"/></label>
                                <input type="date" class="form-control" name="discontinued" id="discontinued" value="${computerToAdd.discontinued}" placeholder="Discontinued date">
                            </div>
                            <div class="form-group">
                                <label for="company"><spring:message code="Company"/></label>
                                <select class="form-control" name="companyDTO.id" id="company" >
                                  <c:forEach var="companyDTO" items="${listCompany}">
                                    <option value="${companyDTO.id}"><c:out value = "${companyDTO.name}"/></option>
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
                            <a href="dashboard" class="btn btn-default"><spring:message code="Cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
   <spring:url value="/resources/js/jquery.min.js" var="jqueryMinJs" />
	<spring:url value="/resources/js/bootstrap.min.js" var="bootsrapJs" />
	<spring:url value="/resources/js/dashboard.js" var="dashboardJs" />
	
	<script src="${jqueryMinJs }"></script>
	<script src="${bootsrapJs }"></script>
	<script src="${dashboardJs }"></script>
</body>
</html>