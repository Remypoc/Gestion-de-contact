<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><bean:message key="group.home.title"/></title>
</head>

<body>

    <h1>Groups</h1>

    <html:form action="/SearchGroup">
        <table>
            <tr>
                <td>
                    <html:text property="groupName" maxlength="100"/>
                    <input type="submit" class="btn btn-primary" value=" <bean:message key="label.search" />" />
                </td>
            </tr>
        </table>
    </html:form>
    <html:errors/>

    <table>
        <logic:present name="groups">
            <logic:empty name="groups">
                <tr>
                    <td>Aucun groupe de contact ne correspond à votre recherche.</td>
                </tr>
            </logic:empty>
            <logic:iterate name="groups" id="group">
                <tr>
                    <td><bean:write name="group" property="groupName"/></td>
                </tr>
            </logic:iterate>
        </logic:present>
    </table>

</body>

</html>