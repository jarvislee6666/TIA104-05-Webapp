<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Adm: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Adm: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>����</p>

<h3>��Ƭd��:</h3>
	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllAdm.jsp'>�Ҧ����u</a><br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" >
        <b>��J�޲z���s��:</b>
        <input type="text" name="admId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="admSvc" scope="page" class="com.admin.model.AdmService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" >
       <b>��ܺ޲z���s��:</b>
       
       <select size="1" name="admId">
         <c:forEach var="admVO" items="${admSvc.all}" > 
          <option value="${admVO.admId}">${admVO.admId}
         </c:forEach>   
       </select>
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" >
       <b>��ܺ޲z���m�W:</b>
         
       <select size="1" name="admId">
         <c:forEach var="admVO" items="${admSvc.all}" > 
          <option value="${admVO.admId}">${admVO.admName}
         </c:forEach>   
       </select>
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addAdm.jsp'>�s�W�޲z��</a></li>
</ul>

</body>
</html>