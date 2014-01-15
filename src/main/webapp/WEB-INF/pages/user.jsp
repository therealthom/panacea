<%-- 
    Document   : user
    Created on : 14-ene-2014, 23:15:46
    Author     : thom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User</title>
    </head>
    <body>

        <h2>User</h2>
        <form:form method="POST" action="addUser">
            <table>
                <tr>
                    <td><form:label path="username">Username</form:label></td>
                    <td><form:input path="username" /></td>
                </tr>
                <tr>
                    <td><form:label path="password">Password</form:label></td>
                    <td><form:input path="password" /></td>
                </tr>     
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit"/>
                    </td>
                </tr>
            </table>  
        </form:form>
    </body>
</html>