<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<%@include file="./head.jsp" %>
<body class="h-screen sm:flex sm:flex-row">
    <%@include file="nav.jsp" %>
    <div class="sm:w-[80%] flex flex-col items-center pb-8 sm:pb-0 border-t-8">
        <!-- error/success/info flags should appear here -->
        <div class="p-2 bg-slate-300 flex flex-row justify-between">
            <p class="">errors flags should appear here</p>
            <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
        </div>
        <div class="w-full flex flex-col border-b-4 px-4">
            <h2 class="text-gray-600 text-lg mb-4">People You May Know</h2>
            <form method="post" class="flex flex-row border-b-2 p-2 justify-between w-full">
                <div class="space-x-2 text-gray-600">
                    <img src="./static/images/profile-img.jpg" alt="follower"
                        class="w-10 h-10 inline rounded-full object-cover">
                    <span>user</span>
                </div>
                <button type="submit" class="border-2 text-white bg-blue-500 rounded-lg px-2 text-sm">Follow</button>
            </form>
            <form method="post" class="flex flex-row border-b-2 p-2 justify-between w-full">
                <div class="space-x-2 text-gray-600">
                    <img src="./static/images/profile-img.jpg" alt="follower"
                        class="w-10 h-10 inline rounded-full object-cover">
                    <span>user</span>
                </div>
                <button type="submit" class="border-2 text-white bg-blue-500 rounded-lg px-2 text-sm">Follow</button>
            </form>
            <form method="post" class="flex flex-row border-b-2 p-2 justify-between w-full">
                <div class="space-x-2 text-gray-600">
                    <img src="./static/images/profile-img.jpg" alt="follower"
                        class="w-10 h-10 inline rounded-full object-cover">
                    <span>user</span>
                </div>
                <button type="submit" class="border-2 text-white bg-blue-500 rounded-lg px-2 text-sm">Follow</button>
            </form>
        </div>
        <div class="w-full flex flex-col border-b-4 px-4">
            <h2 class="text-gray-600 text-lg mb-4">Your followers</h2>
            <form method="post" class="flex flex-row border-b-2 p-2 justify-between w-full">
                <div class="space-x-2 text-gray-600">
                    <img src="./static/images/profile-img.jpg" alt="follower"
                        class="w-10 h-10 inline rounded-full object-cover">
                    <span>user</span>
                </div>
                <button type="submit"
                    class="border-2 text-black bg-white-500 rounded-lg px-2 text-sm">Following</button>
            </form>
            <form method="post" class="flex flex-row border-b-2 p-2 justify-between w-full">
                <div class="space-x-2 text-gray-600">
                    <img src="./static/images/profile-img.jpg" alt="follower"
                        class="w-10 h-10 inline rounded-full object-cover">
                    <span>user</span>
                </div>
                <button type="submit"
                    class="border-2 text-black bg-white-500 rounded-lg px-2 text-sm">Following</button>
            </form>
            <form method="post" class="flex flex-row border-b-2 p-2 justify-between w-full">
                <div class="space-x-2 text-gray-600">
                    <img src="./static/images/profile-img.jpg" alt="follower"
                        class="w-10 h-10 inline rounded-full object-cover">
                    <span>user</span>
                </div>
                <button type="submit"
                    class="border-2 text-black bg-white-500 rounded-lg px-2 text-sm">Following</button>
            </form>
        </div>
    </div>
</body>

</html>