<%-- 
    Document   : notifications
    Created on : Nov 12, 2023, 10:16:45 AM
    Author     : sudarshan
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./static/css/main.css" rel="stylesheet">
    <title>Notification</title>
</head>

<body class="h-screen sm:flex sm:flex-row">
    <nav
        class="flex flex-row justify-between sm:justify-normal sm:flex-col sm:w-[20%] sm:border-r-2 sm:border-blue-400 sm:h-full sm:space-y-6 px-2 py-2 fixed bottom-0 bg-gray-400 w-full sm:bg-white sm:items-center sm:static">

        <a class="inline sm:block cursor-pointer">
            <img src="./static/images/logo.png" alt="twitter-logo"
                class="w-8 h-8 rounded-full overflow-hidden object-cover sm:block">
        </a>
        <a class="inline cursor-pointer">
            <img src="./static/images/profile.png" alt="twitter-logo"
                class="inline w-8  overflow-hidden object-cover ">
        </a>
        <a class="inline cursor-pointer  ">
            <img src="./static/images/share.png" alt="twitter-logo" class="inline w-8  overflow-hidden object-cover ">
        </a>
        <a class="inline cursor-pointer ">
            <img src="./static/images/settings.png" alt="twitter-logo"
                class="inline w-8  overflow-hidden object-cover ">
        </a>

        <a class="inline cursor-pointer ">
            <img src="./static/images/notify.png" alt="twitter-logo" class="inline w-8  overflow-hidden object-cover ">
        </a>


    </nav>
    <div class="sm:w-[80%] flex flex-col items-left pb-8 sm:pb-0 border-t-8">
        <!-- error/success/info flags should appear here -->
        <!-- <div class="p-2 bg-slate-300 flex flex-row justify-between">
            <p class="">errors flags should appear here</p>
            <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
        </div> -->
        <div class="border-b-2 p-2 hover:text-gray-500 flex flex-row space-x-4">
            <a href="#" class="rounded-full">
                <img src="./static/images/profile-img.jpg" class="inline w-10 h-10 rounded-full object-cover" alt="">
            </a>
            <a class="px-2 hover:text-gray-500 flex flex-col" href="#">
                <span>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Esse, doloribus?</span>
                <time datetime="2023-08-04T12:00:00Z" class="text-gray-400 text-sm text-right">August 4, 2023 at 12:00
                    PM</time>
            </a>
        </div>

        <div class="border-b-2 p-2 hover:text-gray-500 flex flex-row space-x-4">
            <a href="#" class="rounded-full">
                <img src="../static/images/profile-img.jpg" class="inline w-10 h-10 rounded-full object-cover" alt="">
            </a>
            <a class="px-2 hover:text-gray-500 flex flex-col" href="#">
                <span>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Esse, doloribus?</span>
                <time datetime="2023-08-04T12:00:00Z" class="text-gray-400 text-sm text-right">August 4, 2023 at 12:00
                    PM</time>
            </a>
        </div>
        
        <div class="border-b-2 p-2 hover:text-gray-500 flex flex-row space-x-4">
            <a href="#" class="rounded-full">
                <img src="../static/images/profile-img.jpg" class="inline w-10 h-10 rounded-full object-cover" alt="">
            </a>
            <a class="px-2 hover:text-gray-500 flex flex-col" href="#">
                <span>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Esse, doloribus?</span>
                <time datetime="2023-08-04T12:00:00Z" class="text-gray-400 text-sm text-right">August 4, 2023 at 12:00
                    PM</time>
            </a>
        </div>

    </div>
</body>

</html>