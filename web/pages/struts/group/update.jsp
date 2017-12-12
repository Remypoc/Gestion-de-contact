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

<logic:notPresent name="group">
    <h1>GROUP</h1>
    <p>Error couldn't find group</p>
</logic:notPresent>

<logic:present name="group">

    <h1> <a href="Group.do"><-</a> <bean:write name="group" property="groupName"/></h1>

    <h2>Contacts</h2>

    <html:form action="/SearchContactInGroup">
        <table>
            <tr>
                <td>
                    <html:text property="name" maxlength="100"/>
                    <html:hidden property="groupId" name="group"/>

                    <input type="submit" class="btn btn-primary" value=" <bean:message key="label.search" />" />
                    <html:link href="UpdateGroup.do">
                        <html:param name="groupId"><bean:write name="group" property="groupId"/></html:param>
                        <bean:message key="form.cancel" />
                    </html:link> <!-- TODO Replace by a button -->
                </td>
            </tr>
        </table>
    </html:form>
    <html:errors/>

    <table>
        <logic:present name="contacts">
            <logic:empty name="contacts">
                <tr>
                    <td><bean:message key="contact.search.empty"/></td>
                </tr>
            </logic:empty>
            <logic:iterate name="contacts" id="contact">
                <tr>
                    <td>
                        <!-- TODO Link to contact informations -->
                        <%--<html:link href="UpdateGroup.do">--%>
                            <%--<html:param name="groupId"><bean:write name="group" property="groupId"/></html:param>--%>
                            <bean:write name="contact" property="firstName"/> <bean:write name="contact" property="lastName"/>
                        <%--</html:link>--%>
                    </td>
                    <td>
                        <html:form action="/DeleteContactFromGroup">
                            <html:hidden property="groupId" name="group"/>
                            <html:hidden property="contactId" value="${contact.id}"/>
                            <input type="submit" value=" <bean:message key="label.delete" />" />
                        </html:form>
                    </td>
                </tr>
            </logic:iterate>
        </logic:present>
        <tr>
            <td>
                <html:form action="/AddContactToGroup">
                    <html:hidden property="groupId" name="group"/>
                    <html:select property="contactId">
                        <html:option value="0">Select contact</html:option>
                        <logic:iterate name="contactsNotInGroup" id="contact">
                            <html:option value="${contact.id}">${contact.firstName} ${contact.lastName}</html:option>
                        </logic:iterate>
                    </html:select>
                    <input type="submit" class="btn btn-primary" value="<bean:message key="form.contactgroup.addcontact"/>" />
                </html:form>
            </td>
        </tr>
    </table>

</logic:present>


</body>

</html>