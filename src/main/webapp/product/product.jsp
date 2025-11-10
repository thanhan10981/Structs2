<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><s:text name="title.productList"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<h2><s:text name="title.productList"/> - <s:text name="label.language"/>: <s:property value="lang"/></h2>

<div class="toolbar">
    <div class="left">
        <a class="btn-add" href="<s:url action='addForm' namespace='/product'/>">+ <s:text name="btn.add"/></a>
    </div>

    <div class="right">
        <form action="<s:url action='list' namespace='/product'/>" method="get">
            <label><s:text name="label.selectLanguage"/>:</label>
            <select name="lang">
                <option value="vi" <s:if test="%{#parameters.lang[0] == 'vi' || lang == 'vi'}">selected</s:if>>Tiếng Việt</option>
                <option value="en" <s:if test="%{#parameters.lang[0] == 'en' || lang == 'en'}">selected</s:if>>English</option>
            </select>
            <input type="submit" value="<s:text name='btn.change'/>"/>
        </form>
    </div>
</div>

<hr/>

<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th><s:text name="label.productName"/></th>
        <th><s:text name="label.productDesc"/></th>
        <th><s:text name="label.price"/></th>
        <th><s:text name="label.weight"/></th>
        <th><s:text name="label.category"/></th>
        <th><s:text name="label.action"/></th>
    </tr>

    <s:iterator value="products">
        <tr>
            <td><s:property value="id"/></td>

            <s:if test="%{lang == 'vi'}">
                <td><s:property value="name_vi"/></td>
                <td><s:property value="description_vi"/></td>
            </s:if>
            <s:else>
                <td><s:property value="name_en"/></td>
                <td><s:property value="description_en"/></td>
            </s:else>

            <td><s:property value="price"/></td>
            <td><s:property value="weight"/></td>
            <td><s:property value="categoryName"/></td>
            <td>
                <!-- URL edit -->
                <s:url var="editUrl" action="edit" namespace="/product">
                    <s:param name="id" value="%{id}"/>
                </s:url>
                <a href="${editUrl}"><s:text name="btn.edit"/></a>

                &nbsp;|&nbsp;
                <!-- URL delete -->
                <s:url var="delBase" action="delete" namespace="/product"/>
                <a href="javascript:void(0)" onclick="confirmDelete('${delBase}', <s:property value='id'/>)">
                    <s:text name="btn.delete"/>
                </a>
            </td>
        </tr>
    </s:iterator>

</table>

<script>
    function confirmDelete(baseUrl, id) {
        const msg = "<s:text name='msg.confirmDelete'/>";
        if (confirm(msg + " " + id + "?")) {
            window.location.href = baseUrl + "?id=" + id;
        }
    }
</script>

</body>
</html>
