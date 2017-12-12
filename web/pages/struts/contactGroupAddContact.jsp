<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><bean:message key="contactgroup.addcontact.page.title"/></title>
</head>

<body>
<h1><bean:message key="contactgroup.addcontact.page.title" /></h1>

<html:errors/>
<html:form action="/AddContactToGroup">
    <table>
        <tr>
            <td><bean:message key="form.contactgroup.group" /></td>
            <td>
                <html:select property="groupId">
                    <html:option value="0">Select group</html:option>
                    <logic:iterate name="groups" id="group">
                        <html:option value="${group.groupId}">${group.groupName}</html:option>
                    </logic:iterate>
                </html:select>
            </td>
        </tr>
        <tr>
            <td><bean:message key="form.contactgroup.contact" /></td>
            <td>
                <html:select property="contactId">
                    <html:option value="0">Select contact</html:option>
                    <logic:iterate name="contacts" id="contact">
                        <html:option value="${contact.id}">${contact.firstName}</html:option>
                    </logic:iterate>
                </html:select>
            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" class="btn btn-primary" value="<bean:message key="form.contactgroup.addcontact"/>" />
                <input type="reset" class="btn btn-primary" value="<bean:message key="form.cancel" />" />
            </td>
        </tr>
    </table>
</html:form>

</body>

</html>