<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>${message}</h1>
<form action="deposit" method="get">
	Enter Account Number: <input name="accountNumber"/><br/>
	Enter Amount : <input name="amount"/><br/>
	<input type="submit"/>
	<a href="http://localhost:8092/gettransactions?offset=1&size=3">get transactions</a>
</form>

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
</body>
</html>