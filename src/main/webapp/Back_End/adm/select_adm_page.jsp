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

<p>首頁</p>

<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllAdm.jsp'>所有員工</a><br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" >
        <b>輸入管理員編號:</b>
        <input type="text" name="admId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="admSvc" scope="page" class="com.admin.model.AdmService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" >
       <b>選擇管理員編號:</b>
       
       <select size="1" name="admId">
         <c:forEach var="admVO" items="${admSvc.all}" > 
          <option value="${admVO.admId}">${admVO.admId}
         </c:forEach>   
       </select>
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Back_End/adm/adm.do" >
       <b>選擇管理員姓名:</b>
         
       <select size="1" name="admId">
         <c:forEach var="admVO" items="${admSvc.all}" > 
          <option value="${admVO.admId}">${admVO.admName}
         </c:forEach>   
       </select>
       
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addAdm.jsp'>新增管理員</a></li>
</ul>

</body>
</html>