<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<table>
<tr>
<th>transactionId</th>
<th>accountNumber</th>
<th>amount</th>
<th>transactionType</th>
<th>transactionDate</th>
<th>transactionDetails</th>
<th>currentBalance</th>
</tr>
<jstl:forEach var="thisis" items="${currentDataSet.transaction}">
<tr>

<td>${thisis.transactionId}</td>
<td>${ thisis.accountNumber}</td>
<td>${ thisis.amount}</td>
<td>${ thisis.transactionType}</td>
<td>${ thisis.transactionDate}</td>
<td>${ thisis.transactionDetails}</td>
<td>${ thisis.currentBalance}</td></tr>
</jstl:forEach>
</table>
<div>
		<a href="${currentDataSet.prevLink.href}">Previous</a> |
		<a href="${currentDataSet.nextLink.href}">Next</a>
	</div>
</html>