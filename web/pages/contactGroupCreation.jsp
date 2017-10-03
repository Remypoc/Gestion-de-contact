<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><bean:message key="contactgroup.creation.page.title"/></title>
</head>

<body>
<h1><bean:message key="contactgroup.creation.page.title" /></h1>

<html:errors/>
<html:form action="/AddContactGroup">
    <table>
        <tr>
            <td><bean:message key="form.contactgroup.groupname" /></td>
            <td><html:text property="groupName" maxlength="30"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" class="btn btn-primary" value="<bean:message key="form.creation.validate"/>" />
                <input type="reset" class="btn btn-primary" value="<bean:message key="form.cancel" />" />
            </td>
        </tr>
    </table>
</html:form>

</body>

</html>