<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <%@include file="./head.jsp" %>
    
    <body class="h-screen sm:flex sm:flex-row">
<!--            <nav
                class="flex flex-row justify-between sm:justify-normal sm:flex-col sm:w-[20%] sm:border-r-2 sm:border-blue-400 sm:h-full sm:space-y-6 px-2 py-2 fixed bottom-0 bg-gray-400 w-full sm:bg-white sm:items-center sm:static">
        
                <a class="inline sm:block cursor-pointer">
                    <img src="./static/images/logo.png" alt="twitter-logo"
                        class="w-8 h-8 rounded-full overflow-hidden object-cover sm:block">
                </a>
                <a class="inline cursor-pointer">
                    <img src="./static/images/profile.png" alt="twitter-logo" class="inline w-8  overflow-hidden object-cover ">
                </a>
                <a class="inline cursor-pointer  ">
                    <img src="./static/images/share.png" alt="twitter-logo" class="inline w-8  overflow-hidden object-cover ">
                </a>
                <a class="inline cursor-pointer ">
                    <img src="./static/images/settings.png" alt="twitter-logo" class="inline w-8  overflow-hidden object-cover ">
                </a>
        
            </nav>-->
        <%@include file="nav.jsp" %>
        <div class="sm:w-[80%] flex flex-col items-center pb-8 sm:pb-0 border-t-8">

            <form method="post" class="flex flex-col justify-between text-sm  border-b-4 w-full p-2 space-y-4">
                <div class="flex flex-row h-20 p-4">
                    <img src="./static/images/profile-img.jpg" class="w-10 h-10  object-cover rounded-full" alt="">
                    <textarea placeholder="Tweet any thing" name="" cols="30" rows="10"
                              class="w-full border-none outline-none border-2 p-2"></textarea>
                </div>
                <div class="w-full text-right">
                    <button type="submit" class="bg-blue-400 text-white rounded-lg px-2 py-1 cursor-pointer">Tweet</button>
                </div>

            </form>

            <div class="flex flex-col items-center justify-between text-sm  border-b-2 space-y-4 px-4">
                <!-- error/success/info flags should appear here -->
<!--                <div class="p-2 bg-slate-300 flex flex-row justify-between">
                    <p class="">errors flags should appear here</p>
                    <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
                </div>-->
                   <%
                       out.println(session.getAttribute("username") + "email" );
                   %>
                <div class="flex flex-row  mt-4 px-2 items-start w-full space-x-4">
                    <img src="./static/images/profile-img.jpg" class="w-10 h-10 rounded-full object-cover " alt="">

                    <p class=" text-sm">
                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                        tenetur
                        quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic perferendis
                        atque
                        dignissimos!
                    </p>
                </div>
                <div class="flex flex-row justify-between w-full px-2">
                    <a class="rounded-full w-full cursor-pointer">
                        <img src="./static/images/comment.png" alt="comment" class="rounded-full w-8 h-10 object-contain">
                    </a>
                    <time datetime="2023-11-11" class="block">November 11, 2023</time>
                </div>
            </div>

            <div class="flex flex-col items-center justify-between text-sm  border-b-2 space-y-4 px-4">
                <div class="flex flex-row  mt-4 px-2 items-start w-full space-x-4">
                    <img src="./static/images/profile-img.jpg" class="w-10 h-10 rounded-full object-cover " alt="">

                    <p class=" text-sm">
                        Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio placeat beatae dolorem officiis
                        tenetur
                        quam repellat magni. Quae saepe, quis excepturi voluptatum facilis quos unde porro hic perferendis
                        atque
                        dignissimos!
                    </p>
                </div>
                <div class="flex flex-row justify-between w-full px-2">
                    <a class="rounded-full w-full cursor-pointer">
                        <img src="./static/images/comment.png" alt="comment" class="rounded-full w-8 h-10 object-contain">
                    </a>
                    <time datetime="2023-11-11" class="block">November 11, 2023</time>
                </div>
            </div>
        </div>
    </body>

</html>