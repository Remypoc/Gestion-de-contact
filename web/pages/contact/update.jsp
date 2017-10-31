<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<html>
<head>
    <title><bean:message key="contacthome.display.page.title"/></title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th><bean:message key="form.contact.creation.lastName"/></th>
        <th><bean:message key="form.contact.creation.firstName"/></th>
        <th><bean:message key="form.contact.creation.email"/></th>
        <th><bean:message key="form.address.street"/></th>
        <th><bean:message key="form.address.city"/></th>
        <th><bean:message key="form.address.zip"/></th>
        <th><bean:message key="form.address.country"/></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><bean:write name="contact" property="lastName"/></td>
        <td><bean:write name="contact" property="firstName"/></td>
        <td><bean:write name="contact" property="email"/></td>
        <logic:present name="contact" property="address">
            <td><bean:write name="contact" property="address.street"/></td>
            <td><bean:write name="contact" property="address.city"/></td>
            <td><bean:write name="contact" property="address.zip"/></td>
            <td><bean:write name="contact" property="address.country"/></td>
        </logic:present>
    </tr>
    </tbody>
</table>
<table>
    <thead>
    <tr>
        <th><bean:message key="form.phonenumber.phonenumber"/></th>
        <th><bean:message key="form.phonenumber.phonekind"/></th>
    </tr>
    </thead>
    <tbody>
    <logic:iterate name="contact" id="phone" property="phones">
        <tr>
            <td><bean:write name="phone" property="phoneNumber"/></td>
            <td><bean:write name="phone" property="phoneKind"/></td>
        </tr>
    </logic:iterate>
    </tbody>
</table>
</body>
</html>
