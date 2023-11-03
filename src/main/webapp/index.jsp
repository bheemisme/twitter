<%-- 
    Document   : index
    Created on : Nov 3, 2023, 8:59:43 PM
    Author     : sudarshan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link  href="css/style.css" type="stylesheet" content="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div>
            <c:out value = "${'hello , &'}"/>
        </div>
    </body>
</html>
