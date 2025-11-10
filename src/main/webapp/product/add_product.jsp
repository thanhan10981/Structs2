<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><s:text name="title.addProduct"/></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style_add.css">
</head>
<body>
<div class="container">
    <h2><s:text name="title.addProduct"/></h2>

    <!-- URL form -->
    <s:url var="addUrl" action="add" namespace="/product"/>

    <form action="${addUrl}" method="post">
        <fieldset>
            <legend>🇻🇳 <s:text name="legend.vi"/></legend>
            <label><s:text name="label.productName.vi"/>:</label>
            <input type="text" name="product.name_vi" required>
            <label><s:text name="label.productDesc.vi"/>:</label>
            <textarea name="product.description_vi" required></textarea>
        </fieldset>

        <fieldset>
            <legend>🇺🇸 <s:text name="legend.en"/></legend>
            <label><s:text name="label.productName.en"/>:</label>
            <input type="text" name="product.name_en" required>
            <label><s:text name="label.productDesc.en"/>:</label>
            <textarea name="product.description_en" required></textarea>
        </fieldset>

        <fieldset>
            <legend>💰 <s:text name="legend.common"/></legend>
            <label><s:text name="label.price"/>:</label>
            <input type="number" name="product.price" step="0.01" min="0" required>
            <label><s:text name="label.weight"/> (kg):</label>
            <input type="number" name="product.weight" step="0.1" min="0" required>
            <label><s:text name="label.category"/>:</label>
            <select name="product.categoryID" required>
                <option value="">-- <s:text name="label.selectCategory"/> --</option>
                <option value="1"><s:text name="category.1"/></option>
                <option value="2"><s:text name="category.2"/></option>
            </select>
        </fieldset>

        <button type="submit" class="btn-primary">➕ <s:text name="btn.add"/></button>
    </form>

    <a href="<s:url action='list' namespace='/product'/>" class="back-link">← <s:text name="link.back"/></a>
</div>
</body>
</html>
