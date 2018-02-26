<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer List</title>
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>

		<div id="container">
			<div id="content">
			<!-- search bar -->
				<form:form action="search" method="POST">
					Search Customer: <input type="text" name="theSearchName" />
					<input type="button" value="Search" class="add-button" />
				</form:form>
				<!-- Add customer button -->
				<input type="button" value="Add Customer"
					onclick="window.location.href='showFormForAdd'; return false;"
					class="add-button" />
				<table>
					<tr>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Action</th>
					</tr>
					<c:forEach var="customer" items="${customers}">

						<!-- construct an 'update' link with customer id -->
						<c:url var="updateLink" value="/customer/showFormForUpdate">
							<c:param name="customerId" value="${customer.id}"></c:param>
						</c:url>
						<!-- Construct a 'delete' link -->
						<c:url var="deleteLink" value="/customer/delete">
							<c:param name="customerId" value="${customer.id}"></c:param>
						</c:url>
						<tr>
							<td>${customer.firstName}</td>
							<td>${customer.lastName}</td>
							<td>${customer.email}</td>
							<td>
								<a href="${updateLink}">Update</a>
								<a href="${deleteLink}" 
									onclick="if(!(confirm('Are you sure about delete?'))) return false">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>

</body>
</html>