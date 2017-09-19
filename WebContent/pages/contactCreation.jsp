<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><bean:message key="contact.creation.page.title"/></title>
</head>
<body>
	<h1><bean:message key="form.contact.creation.title" /></h1>
	<html:errors/>
		<html:form action="/AddContact">
			<table>
				<tr>
					<td><bean:message key="form.contact.creation.lastName" /></td>
					<td><html:text property="lastName" maxlength="200" /></td>
				</tr>
				<tr>
					<td><bean:message key="form.contact.creation.firstName" />
					<td><html:text property="firstName" maxlength="200" /></td>
				</tr>
				<tr>
					<td><bean:message key="form.contact.creation.email" /></td>
					<td><html:text property="email" maxlength="200" /></td>
				</tr>
				<tr>
				<td>
					<input type="submit" class="btn btn-primary" value=" <bean:message key="form.contact.creation.validate" />" />
					<input type="reset" class="btn btn-primary" value="<bean:message key="form.contact.creation.cancel" />" />
				</td>
				</tr>
			</table>
		</html:form>
	</body>
</body>
</html>