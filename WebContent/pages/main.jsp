<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><bean:message key="main.page.title"/></title>
  </head>
  
  <body>
  	
  	<h2>Create</h2>
 	<ul>
 		<li><a href="ContactCreation.do"><bean:message key="main.page.contact.creation"/></a></li>
 		<li><a href="AddressCreation.do"><bean:message key="main.page.address.creation"/></a></li>
 		<li><a href="PhoneNumberCreation.do"><bean:message key="main.page.phonenumber.creation"/></a></li>
 	</ul>
 	
 	<h1>Delete</h1>
 	<ul>
 		<li><a href="AddressDelete.do"><bean:message key="main.page.address.delete"/></a></li>
 		<li><a href="PhoneNumberDelete.do"><bean:message key="main.page.phonenumber.delete"/></a></li>
 	</ul>
 	
 	<h1>Update</h1>
 	<ul>
 		<li><a href="AddressUpdate.do"><bean:message key="main.page.address.update"/></a></li>
 		<li><a href="PhoneNumberUpdate.do"><bean:message key="main.page.phonenumber.update"/></a></li>
 	</ul>
 	
 	<h1>Search</h1>
 	<ul>
 		<li><a href="AddressSearch.do"><bean:message key="main.page.address.search"/></a></li>
 		<li><a href="PhoneNumberSearch.do"><bean:message key="main.page.phonenumber.search"/></a></li>
 	</ul>
 	
  </body>
  
</html>