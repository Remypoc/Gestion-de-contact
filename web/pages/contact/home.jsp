<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><bean:message key="contacthome.home.page.title"/></title>
</head>
<html:errors/>
<html:form action="/ResearchContact">
    <table>
        <tr>
            <td><bean:message key="form.contact.research"/></td>
            <td><html:text property="research" maxlength="200"/></td>
        </tr>
    </table>
</html:form>
<body>
<table>
    <thead>
    <tr>
        <th><bean:message key="contact.display.lastname"/></th>
        <th><bean:message key="contact.display.firstname"/></th>
        <th><bean:message key="contact.display.delete"/></th>
    </tr>
    </thead>
    <tbody>
    <logic:iterate name="contacts" id="contact">
        <tr>
            <td><bean:write name="contact" property="firstName"/></td>
            <td><bean:write name="contact" property="lastName"/></td>
            <td><bean:message key="contact.display.delete"/></td>
        </tr>
    </logic:iterate>
    </tbody>
</table>
</body>
</html>