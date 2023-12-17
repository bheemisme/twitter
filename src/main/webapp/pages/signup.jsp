<%-- 
    Document   : signup
    Created on : Nov 12, 2023, 10:16:45 AM
    Author     : rohit kumar
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<%@include file="./head.jsp" %>

<body class="h-screen flex flex-col items-center justify-center space-y-4 p-4">
    <h1 class="text-blue-400 text-xl underline cursor-default">Signup</h1>
    <form action="/twitter/signup" method="post" class="flex flex-col items-center justify-center space-y-4">

        <div class="sm:space-x-6 space-x-0">
            <label for="email">Enter Email: </label>
            <input type="email" name="email" id="email" class="outline-none border-2 rounded-lg px-2">
        </div>
        <div class="sm:space-x-6 space-x-0">
            <label for="username">Enter Username: </label>
            <input type="text" name="username" id="username" class="outline-none border-2 rounded-lg px-2">
        </div>
        <div class="">
            <label for="password">Enter Password: </label>
            <input type="password" name="password" id="password" class="outline-none border-2 rounded-lg px-2">
        </div>
        <div class="text-right w-full">
            <button type="submit" class="outline-none rounded-lg px-4 py-1 text-white bg-blue-400">Signup</button>
        </div>
    </form>
</body>

</html>