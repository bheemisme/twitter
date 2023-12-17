<%-- 
    Document   : 404
    Created on : Nov 12, 2023, 10:16:45 AM
    Author     : sudarshan
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./static/css/main.css" rel="stylesheet">
        <link href="./static/css/custom.css" rel="stylesheet">
        <title>403</title>
    </head>

    <body class="h-screen sm:flex sm:flex-row">
        <%@include file="nav.jsp" %>

        <div class="sm:w-[80%] flex flex-row items-center justify-center h-full">
            <div class="text-center">
                <p class="text-2xl text-blue-400">404</p>
                <p>
                    ${message}
                </p>

            </div>
        </div>
    </body>

</html>