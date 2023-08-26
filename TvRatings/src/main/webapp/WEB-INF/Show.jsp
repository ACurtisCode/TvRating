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
	<h1>${ show.getTitle() }</h1>
	<a href="/shows">Back to Dashboard</a>
	<table>
		<tbody>
			<tr id="author">
				<td>Posted By:</td>
				<td>${ show.getShowCreator().getUserName() }</td>
			</tr>
			<tr>
				<td>Network:</td>
				<td>${ show.getNetwork() }</td>
			</tr>
			<tr>
				<td>Description:</td>
				<td>${show.getDescription()}</td>
			</tr>
		</tbody>
	</table>
	<form:form action="/shows/edit/${show.getId() }" method="get">
		<input type="submit" value="Edit" />
	</form:form>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Name</th>
				<th>Rating</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="rating" items="${ show.getRatings() }">
		<tr>
		<td>${ rating.getReviewCreator().getUserName() } </td>
		<td>${ rating.getShowRating() } </td>
		</tr>
		</c:forEach>
		</tbody>
	</table>
	<form:form action="/shows/rating/${ show.getId() }" method="post"
		modelAttribute="newRating">
		<form:label path="showRating">Leave a rating:</form:label>
		<form:errors path="showRating" class="text-danger" />
		<form:input type="number" step="0.1" path="showRating" />
		<input type = "submit" value="Submit"/>
	</form:form>
</body>
</html>