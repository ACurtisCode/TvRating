<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Welcome ${user.getUserName()}</h1>
		<a href="/logout">log out</a>
		<h3>TV Shows</h3>
		<table class="table table-striped">
		
			<thead>
				<tr>
					<td>Show</td>
					<td>Network</td>
					<td>Rating</td>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="show" items="${ shows }">
					<tr>
						<td><a href="/shows/${ show.getId() }">${ show.getTitle() }</a></td>
						<td>${show.getNetwork() }</td>
						<td>${ show.getAverageRating() }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<form:form action="/shows/new" method="get">
			<input type="submit" value="Add a Show" />
		</form:form>
	</div>

</body>
</html>