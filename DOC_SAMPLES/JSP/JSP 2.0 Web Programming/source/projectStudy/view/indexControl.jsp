<%@ page language="java" pageEncoding="EUC-KR" %>
<!DOCTYPE HTML PUBLIC "-//w3c//dtd html 4.0 transitional//en">
<%
  String CONTROL=null;
  String PAGENUM=null;
  try{
  CONTROL = request.getParameter("CONTROL");
  PAGENUM = request.getParameter("PAGENUM");
  
  
  if(CONTROL.equals(null)){
	  CONTROL="intro";
  }
  if(PAGENUM.equals(null)){
	  PAGENUM="01";
  }
  }catch(Exception e){
	  e.printStackTrace();}
%>
<jsp:forward page="/template/template.jsp">
  <jsp:param name="CONTROL" value="<%=CONTROL%>"/>
  <jsp:param name="PAGENUM" value="<%=PAGENUM%>"/>
</jsp:forward>