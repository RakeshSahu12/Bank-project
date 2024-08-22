<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"   
    import="BankTransaction.BankBean"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
BankBean ba=(BankBean)session.getAttribute("bbean");
out.println("WELCOME USER :"+ba.getAccName()+"&nbsp&nbsp"+"AccNo :"+ba.getAccNo()+"<br>");
%>

<a href="application.html">Applications</a>

</body>
</html>