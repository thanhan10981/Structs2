<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><s:text name="title.editProduct"/> - ID: <s:property value="product.id"/></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style_add.css">
</head>
<body>
<div class="container">
    <h2>✏️ <s:text name="title.editProduct"/> ID: <s:property value="product.id"/></h2>

    <s:url var="updateUrl" action="update" namespace="/product"/>
    <form action="${updateUrl}" method="post">
        <input type="hidden" name="product.id" value="<s:property value='product.id'/>"/>

        <fieldset>
            <legend>🇻🇳 <s:text name="legend.vi"/></legend>
            <label><s:text name="label.productName.vi"/>:</label>
            <input type="text" name="product.name_vi" value="<s:property value='product.name_vi'/>"/>
            <label><s:text name="label.productDesc.vi"/>:</label>
            <textarea name="product.description_vi"><s:property value='product.description_vi'/></textarea>
        </fieldset>

        <fieldset>
            <legend>🇺🇸 <s:text name="legend.en"/></legend>
            <label><s:text name="label.productName.en"/>:</label>
            <input type="text" name="product.name_en" value="<s:property value='product.name_en'/>"/>
            <label><s:text name="label.productDesc.en"/>:</label>
            <textarea name="product.description_en"><s:property value='product.description_en'/></textarea>
        </fieldset>

        <fieldset>
            <legend>💰 <s:text name="legend.common"/></legend>
            <label><s:text name="label.price"/>:</label>
            <input type="text" name="product.price" value="<s:property value='product.price'/>"/>
            <label><s:text name="label.weight"/> (kg):</label>
            <input type="text" name="product.weight" value="<s:property value='product.weight'/>"/>
            <label><s:text name="label.category"/>:</label>
            <select name="product.categoryID">
                <option value="1" <s:if test="product.categoryID == 1">selected</s:if>><s:text name="category.1"/></option>
                <option value="2" <s:if test="product.categoryID == 2">selected</s:if>><s:text name="category.2"/></option>
            </select>
        </fieldset>

        <button type="submit" class="btn-primary">💾 <s:text name="btn.save"/></button>
        <a href="<s:url action='list' namespace='/product'/>" class="back-link">← <s:text name="link.back"/></a>
    </form>
</div>
</body>
</html>
