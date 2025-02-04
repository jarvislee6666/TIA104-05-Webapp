<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AdmService admSvc = new AdmService();
    List<AdmVO> list = admSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>所有管理員資料 - listAllAdm.jsp</title>

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
	width: 800px;
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

<h4>此頁練習採用 EL 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>所有員工資料 - listAllAdm.jsp</h3>
		 <h4><a href="select_adm_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="admVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${admVO.admId}</td>
			<td>${admVO.admName}</td>
			<td>${admVO.admEmail}</td>
			<td>${admVO.admPassword}</td>
			<td>${admVO.admPhone}</td>
			<td>
                <c:choose>
                    <c:when test="${admVO.admSta == true}">在職</c:when>
                    <c:otherwise>離職</c:otherwise>
                </c:choose>
            </td>
			<td>${admVO.hrDate}</td> 
			<td>${admVO.admBday}</td>
			<td>
			    <c:choose>
                    <c:when test="${admVO.supvsr == true}">是</c:when>
                    <c:otherwise>否</c:otherwise>
                </c:choose>
            </td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="admId"  value="${admVO.admId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="admId"  value="${admVO.admId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>