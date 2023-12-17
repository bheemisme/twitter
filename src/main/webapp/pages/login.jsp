<%-- 
    Document   : login
    Created on : Nov 12, 2023, 10:16:45 AM
    Author     : bharathi
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./static/css/main.css" rel="stylesheet">
    <title>Login</title>
</head>

<body class="h-screen flex flex-col items-center justify-center space-y-4 p-4">
    <h1 class="text-blue-400 text-xl underline cursor-default">Login</h1>
    <form action="#" method="post" class="flex flex-col items-center justify-center space-y-4">

        <div class="sm:space-x-6 space-x-0">
            <label for="email">Enter Email: </label>
            <input type="email" name="email" id="" class="outline-none border-2 rounded-lg px-2">
        </div>
        <div class="">
            <label for="email">Enter Password: </label>
            <input type="password" name="password" id="" class="outline-none border-2 rounded-lg px-2">
        </div>
        <div class="text-right w-full">
            <button type="submit" class="outline-none rounded-lg px-4 py-1 text-white bg-blue-400">Login</button>
        </div>
    </form>
    <a href="/twitter/signup" class="border-2 p-2 rounded">Signup</a>
</body>

</html>