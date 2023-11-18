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
        <div class="flex flex-col items-center justify-between text-sm  border-b-2 p-4">
            <div class="flex flex-row  mt-4 px-2 items-start w-full space-x-4">
                <img src="./static/images/profile-img.jpg" class="w-10 h-10 rounded-full object-cover " alt="">
                <div class="space-y-2">

                    <p class=" text-sm">
                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                        tenetur
                        quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic
                        perferendis
                        atque
                        dignissimos!
                    </p>
                    <div class="flex flex-row justify-between w-full">
                        <form action="" method="post" class="text-right">
                            <button type="submit" class="text-white bg-red-500 px-2 py-1 rounded-xl">Delete</button>
                        </form>
                        <div class="text-right w-full">
                            <time datetime="2023-11-11" class="block text-sm text-gray-400 text-right">November 11,
                                2023</time>
                        </div>

                    </div>
                </div>
            </div>

        </div>
        <form method="post" class="flex flex-col justify-between text-sm  border-b-4 w-full p-2 space-y-4">

            <div class="flex flex-row h-20 p-4">
                <img src="./static/images/profile-img.jpg" class="w-10 h-10  object-cover rounded-full" alt="">
                <textarea placeholder="Please comment" name="" cols="30" rows="10"
                    class="w-full border-none outline-none border-2 p-2">
                </textarea>
            </div>
            <div class="w-full text-right">
                <button type="submit" class="bg-blue-400 text-white rounded-lg px-2 py-1 cursor-pointer">
                    Comment
                </button>
            </div>
        </form>
        <div class="flex flex-col space-y-4 px-4 pb-6">
            <div class="flex flex-row  mt-4 px-2 items-start w-full space-x-4">
                <img src="./static/images/profile-img.jpg" class="w-10 h-10 rounded-full object-cover " alt="">

                <div class="space-y-2">
                    <p class=" text-sm">
                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                        tenetur
                        quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic
                        perferendis
                        atque
                        dignissimos!
                    </p>
                    <form action="" method="post" class="text-right">
                        <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded-xl text-sm">Delete</button>
                    </form>
                </div>
            </div>
            <div class="flex flex-row  mt-4 px-2 items-start w-full space-x-4">
                <img src="./static/images/profile-img.jpg" class="w-10 h-10 rounded-full object-cover " alt="">

                <div class="space-y-2">
                    <p class=" text-sm">
                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                        tenetur
                        quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic
                        perferendis
                        atque
                        dignissimos!
                    </p>
                    <form action="" method="post" class="text-right">
                        <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded-xl text-sm">Delete</button>
                    </form>
                </div>
            </div>
            <div class="flex flex-row  mt-4 px-2 items-start w-full space-x-4">
                <img src="./static/images/profile-img.jpg" class="w-10 h-10 rounded-full object-cover " alt="">

                <div class="space-y-2">
                    <p class=" text-sm">
                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                        tenetur
                        quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic
                        perferendis
                        atque
                        dignissimos!
                    </p>
                    <form action="" method="post" class="text-right">
                        <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded-xl text-sm">Delete</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
</body>

</html>