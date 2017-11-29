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
<html:form action="/UpdateContact">
    <html:errors/>
    <table>
        <tbody>
        <tr>
            <td><bean:message key="form.contact.creation.lastName"/></td>
            <td><html:text name="contact" property="lastName" maxlength="200"/></td>
        </tr>
        <tr>
            <td><bean:message key="form.contact.creation.firstName"/></td>
            <td><html:text name="contact" property="firstName" maxlength="200"/></td>
        </tr>
        <tr>
            <td><bean:message key="form.contact.creation.email"/></td>
            <td><html:text name="contact" property="email" maxlength="200"/></td>
        </tr>
        <tr>
            <td><html:radio property="hasAddress" value="false">
                <bean:message key="form.contact.address.disable"/>
            </html:radio>
            </td>
            <td><html:radio property="hasAddress" value="true">
                <bean:message key="form.contact.address.enable"/>
            </html:radio>
            </td>
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
        <html:hidden name="contact" property="id"/>
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
        <tr>
            <td><html:radio property="hasPhone" value="false">
                <bean:message key="form.contact.phone.disable"/>
            </html:radio>
            </td>
            <td><html:radio property="hasPhone" value="true">
                <bean:message key="form.contact.phone.enable"/>
            </html:radio>
            </td>
        </tr>
        <logic:iterate name="contact" id="phone" property="phones">
            <tr>
                <td><html:text name="phone" property="phoneNumber"/></td>
                <td><html:text name="phone" property="phoneKind"/></td>
            </tr>
        </logic:iterate>
        </tbody>
    </table>
    <table>
        <tr>
            <td>
                <input type="submit" class="btn btn-primary"
                       value=" <bean:message key="form.contact.creation.validate" />"/>
                <input type="reset" class="btn btn-primary"
                       value="<bean:message key="form.cancel" />"/>
            </td>
        </tr>
    </table>
</html:form>
</body>
</html>
