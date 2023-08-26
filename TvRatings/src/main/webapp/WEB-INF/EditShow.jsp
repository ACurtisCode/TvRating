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
		<h1>Edit Show</h1>


		<table>
			<tbody>
				<form:form action="/shows/edit/${show.getId() }" method="post"
					modelAttribute="show">
					<input type="hidden" name="_method" value="put">
					<tr>
						<td><form:label path="title">Title:</form:label></td>
						<td><form:errors path="title" class="text-danger" /></td>
						<td><form:input path="title" /></td>
					</tr>
					<tr>
						<td><form:label path="network">Network:</form:label></td>
						<td><form:errors path="network" class="text-danger" /></td>
						<td><form:input path="network" /></td>
					</tr>
					<tr>
						<td><form:label path="description">Description:</form:label></td>
						<td><form:errors path="description" class="text-danger" /></td>
						<td><form:textarea rows="5" path="description" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Submit" /></td>
				</form:form>
				<td><form:form action="/shows/${ show.getId() }" method="get">
						<input type="submit" value="Cancel" />
					</form:form></td>
				</tr>
			</tbody>

		</table>
		<form:form action="/shows/delete/${ show.getId() }" method="get">
			<input type="submit" value="Delete" />
		</form:form>
	</div>
</body>
</html>