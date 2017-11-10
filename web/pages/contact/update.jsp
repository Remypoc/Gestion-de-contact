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
<html:errors/>
<html:form action="/UpdateContact">
    <table>
        <tbody>
        <tr>
            <td><bean:message key="form.contact.creation.lastName"/></td>
            <td><html:text name="DisplayContactValidationForm" property="lastName" maxlength="200"/></td>
        </tr>
        <tr>
            <td><bean:message key="form.contact.creation.firstName"/></td>
            <td><html:text name="contact" property="firstName" maxlength="200"/></td>
        </tr>
        <tr>
            <td><bean:message key="form.contact.creation.email"/></td>
            <td><html:text name="contact" property="email" maxlength="200"/></td>
        </tr>
        <logic:present name="contact" property="address">
            <tr>
                <td><bean:message key="form.address.street"/></td>
                <td><html:text name="contact" property="address.street"/></td>
            </tr>
            <tr>
                <td><bean:message key="form.address.city"/></td>
                <td><html:text name="contact" property="address.city"/></td>
            </tr>
            <tr>
                <td><bean:message key="form.address.zip"/></td>
                <td><html:text name="contact" property="address.zip"/></td>
            </tr>
            <tr>
                <td><bean:message key="form.address.country"/></td>
                <td><html:text name="contact" property="address.country"/></td>
            </tr>
        </logic:present>
        <logic:notPresent name="contact" property="address">
            <tr>
                <td><bean:message key="form.address.street"/></td>
                <td><html:text property="street" maxlength="200"/></td>
            </tr>
            <tr>
                <td><bean:message key="form.address.city"/></td>
                <td><html:text property="city" maxlength="60"/></td>
            </tr>
            <tr>
                <td><bean:message key="form.address.zip"/></td>
                <td><html:text property="zip" maxlength="30"/></td>
            </tr>
            <tr>
                <td><bean:message key="form.address.country"/></td>
                <td><html:text property="country" maxlength="200"/></td>
            </tr>
        </logic:notPresent>
        </tbody>
    </table>
    <input type="submit" class="btn btn-primary"
           value=" <bean:message key="form.contact.creation.validate" />"/>
</html:form>
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
            <td><html:text name="phone" property="phoneNumber"/></td>
            <td><html:text name="phone" property="phoneKind"/></td>
        </tr>
    </logic:iterate>
    </tbody>
</table>
</body>
</html>
