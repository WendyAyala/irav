<%
	session.invalidate();
	response.sendRedirect(request.getContextPath()
			+ "/paginas/main.jsf");
%>