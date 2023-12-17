<%-- 
    Document   : change password
    Created on : Nov 12, 2023, 10:16:45 AM
    Author     : sudarshan
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<%@include file="head.jsp" %>
<body class="h-screen py-4 sm:flex sm:flex-row">
    <%@include file="nav.jsp" %>
    <div class="sm:w-[80%] flex flex-col items-center pb-8 sm:pb-0">

        <!-- error/success/info flags should appear here -->
        <div class="p-2 bg-slate-300 flex flex-row justify-between hidden">
            <p class="">errors flags should appear here</p>
            <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
        </div>
        <form method="post" action="/twitter/password" class="w-full px-8 py-4 flex flex-col space-y-2">
            <div class="space-x-4">
                <label for="oldPassword">Enter Old Password</label>
                <input type="text" name="oldPassword"  class="border-2 outline-none rounded-lg px-2"
                    placeholder="enter old password">
            </div>
            <div class="space-x-2">
                <label for="newPassword">Enter New Password</label>
                <input type="text" name="newPassword"  class="border-2 outline-none rounded-lg px-2"
                    placeholder="enter new password">
            </div>
            <div class=" text-right">
                <button type="submit" class="border-2 px-2 py-1 rounded-lg hover:text-gray-400">Submit</button>
            </div>
        </form>
    </div>
</body>

</html>