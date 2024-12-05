<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.admin.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
    AdmService admSvc = new AdmService();
    List<AdmVO> list = admSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ��޲z����� - listAllAdm.jsp</title>

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

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllAdm.jsp</h3>
		 <h4><a href="select_adm_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�޲z���s��</th>
		<th>�޲z���m�W</th>
		<th>Email</th>
		<th>�K�X</th>
		<th>�q��</th>
		<th>�޲z�����A</th>
		<th>���Τ�</th>
		<th>�ͤ�</th>
		<th>�O�_���D��</th>
		<th>�ק�</th>
		<th>�R��</th>
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
                    <c:when test="${admVO.admSta == true}">�b¾</c:when>
                    <c:otherwise>��¾</c:otherwise>
                </c:choose>
            </td>
			<td>${admVO.hrDate}</td> 
			<td>${admVO.admBday}</td>
			<td>
			    <c:choose>
                    <c:when test="${admVO.supvsr == true}">�O</c:when>
                    <c:otherwise>�_</c:otherwise>
                </c:choose>
            </td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="admId"  value="${admVO.admId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="admId"  value="${admVO.admId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>