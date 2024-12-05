<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.admin.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
AdmVO admVO = (AdmVO) request.getAttribute("admVO"); //AdmServlet.java(Concroller), 存入req的admVO物件
%>

<html>
<head>
<title>管理員資料 - listOneAdm.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁暫練習採用 Script 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>管理員資料 - listOneAdm.jsp</h3>
				<h4>
					<a href="select_adm_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>管理員編號</th>
			<th>管理員姓名</th>
			<th>Email</th>
			<th>密碼</th>
			<th>電話</th>
			<th>管理員狀態</th>
			<th>雇用日</th>
			<th>生日</th>
			<th>是否為主管</th>
		</tr>
		<tr>
			<td><%=admVO.getAdmId()%></td>
			<td><%=admVO.getAdmName()%></td>
			<td><%=admVO.getAdmEmail()%></td>
			<td><%=admVO.getAdmPassword()%></td>
			<td><%=admVO.getAdmPhone()%></td>
			<td><%=(admVO.getAdmSta() == true) ? "在職" : "離職"%></td>
			<td><%=admVO.getHrDate()%></td>
			<td><%=admVO.getAdmBday()%></td>
			<td><%=(admVO.getSupvsr() == true) ? "是" : "否"%></td>
		</tr>
	</table>


</body>
</html>