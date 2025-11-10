<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Tự động chuyển tới action Struts
    response.sendRedirect(request.getContextPath() + "/product/list.action");
%>
