<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Index</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<!-- <link rel="stylesheet" type="text/css" href="/css/style.css"> -->
	</head>

	<body>
		<div class="container">
			<h3>Add License</h3>
			<form:form method="POST" action="/create/license" modelAttribute="license">
				<table>
					<tr>
						<td>
							<form:label path="person">person:</form:label>
						</td>
						<td>
							<form:select path="person">
								<c:forEach items="${persons}" var="person">
									<option value="${person.id}">${person.firstName} ${person.lastName}</option>
								</c:forEach>
							</form:select>
							<form:errors path="person" />
						</td>
					</tr>
					<tr>
						<td>
							<form:label path="state">State:</form:label>
						</td>
						<td>
							<form:input path="state" />
							<form:errors path="state" />
						</td>
					</tr>
					<tr>
						<td>
							<form:label path="expiration_date">Expiration Date:</form:label>
						</td>
						<td>
							<form:input type="date" path="expiration_date" />
							<form:errors path="expiration_date" />
						</td>
					</tr>
					<tr>
						<td>
							<input type="submit" value="Create" />
						</td>
					</tr>
				</table>
			</form:form>
		</div>
	</body>
</html>