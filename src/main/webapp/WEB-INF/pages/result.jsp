<%-- 
    Document   : result
    Created on : 14-ene-2014, 23:15:50
    Author     : thom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Spring MVC Form Handling</title>
    </head>
    <body>

        <h2>User Information</h2>
        <table>
            <tr>
                <td>Username</td>
                <td>${userBean.username}</td>
            </tr>
            <tr>
                <td>Password</td>
                <td>${userBean.password}</td>
            </tr>
        </table>              
    </body>
</html>