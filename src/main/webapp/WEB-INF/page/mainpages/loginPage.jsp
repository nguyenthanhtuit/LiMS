<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../includes/_header.jsp"></jsp:include>
	<jsp:include page="../includes/_menu.jsp"></jsp:include>
	<h3>Login Page</h3>

	<p style="color: red;">thong bao: ${erroString}</p>

	<form action="doLogin" method="POST">
		<table border="0">
          <tr>
             <td>User Name</td>
             <td><input type="text" name="userName" value= "${user.userName}" /> </td>
          </tr>
          <tr>
             <td>Password</td>
             <td><input type="text" name="password" value= "${user.password}" /> </td>
          </tr>
          <tr>
             <td>Remember me</td>
             <td><input type="checkbox" name="rememberMe" value= "Y" /> </td>
          </tr>
          <tr>
             <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
             </td>
          </tr>
       </table>
	</form>

	<jsp:include page="../includes/_footer.jsp"></jsp:include>

</body>
</html>