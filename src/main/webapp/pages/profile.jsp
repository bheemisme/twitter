<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./static/css/main.css" rel="stylesheet">
    <title>Profile</title>
</head>

<body class="h-screen py-4 sm:flex sm:flex-row">
    
    <%@include file="nav.jsp" %>
    <div class="sm:w-[80%] flex flex-col items-center pb-8 sm:pb-0">
        <!-- error/success/info flags should appear here -->
        <div class="p-2 bg-slate-300 hidden">
            <p class="">errors flags should appear here</p>
            <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
        </div>
        <div class="flex flex-col items-center justify-center w-full border-b-4 py-2 space-y-2">
            <img src="./static/images/profile-img.jpg" alt="" class="w-32 h-32 rounded-full overflow-hidden object-cover">
            <span class="block">Name</span>
        </div>
        <div class="w-full px-2 py-4">
            <div class="flex flex-col items-center justify-between text-sm  border-b-2">
                <p class="p-2 text-sm">
                    Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                    tenetur
                    quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic perferendis
                    atque
                    dignissimos!
                </p>
                <div class="flex flex-row justify-between w-full px-2">
                    <a class="rounded-full w-full cursor-pointer">
                        <img src="./static/images/comment.png" alt="comment" class="rounded-full w-8 h-10 object-contain">
                    </a>
                    <time datetime="2023-11-11" class="block">November 11, 2023</time>
                </div>

            </div>

            <div class="flex flex-col items-center justify-between text-sm  border-b-2">
                <p class="p-2 text-sm">
                    Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                    tenetur
                    quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic perferendis
                    atque
                    dignissimos!
                </p>
                <div class="flex flex-row justify-between w-full px-2">
                    <a class="rounded-full w-full cursor-pointer">
                        <img src="./static/images/comment.png" alt="" class="rounded-full w-8 h-10 object-contain">
                    </a>
                    <time datetime="2023-11-11" class="block">November 11, 2023</time>
                </div>

            </div>

            <div class="flex flex-col items-center justify-between text-sm  border-b-2">
                <p class="p-2 text-sm">
                    Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                    tenetur
                    quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic perferendis
                    atque
                    dignissimos!
                </p>
                <div class="flex flex-row justify-between w-full px-2">
                    <a class="rounded-full w-full cursor-pointer">
                        <img src="./static/images/comment.png" alt="" class="rounded-full w-8 h-10 object-contain">
                    </a>
                    <time datetime="2023-11-11" class="block">November 11, 2023</time>
                </div>

            </div>
        </div>
    </div>
</body>

</html>