<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>
<%@include file="head.jsp" %>

<body class="h-screen sm:flex sm:flex-row">
    <%@include file="nav.jsp" %>
    <div class="sm:w-[80%] flex flex-col items-left pb-8 sm:pb-0 border-t-8">
        <!-- error/success/info flags should appear here -->
        <div class="p-2 bg-slate-300 flex flex-row justify-between hidden">
            <p class="">errors flags should appear here</p>
            <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
        </div>
        <a href="/twitter/edit" class="border-b-2 p-2 hover:text-gray-500">Edit Profile</a>
        <a href="/twitter/password" class="border-b-2 p-2 hover:text-gray-500">Change Password</a>
        <a href="/twitter/delete" class="border-b-2 p-2 hover:text-gray-500">Delete Account</a>
        <a href="/twitter/logout" class="border-b-2 p-2 hover:text-gray-500">Logout</a>

    </div>
</body>

</html>