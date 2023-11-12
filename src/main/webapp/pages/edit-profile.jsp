<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./static/css/main.css" rel="stylesheet">
    <title>Edit Profile</title>
</head>

<body class="h-screen py-4 sm:flex sm:flex-row">
    <nav
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

    </nav>
    <div class="sm:w-[80%] flex flex-col items-center pb-8 sm:pb-0">

        <form method="post" class="flex flex-row space-x-4 w-full border-b-4 space-y-2 p-2">
            <img src="./static/images/profile-img.jpg" alt="profile-img"
                class="w-16 h-16 rounded-full overflow-hidden object-cover">
            <div class="flex flex-col space-y-4">
                <input type="file" class="" name="" id="">
                <div class="text-left  cursor-pointer">
                    <button type="submit" class="border-2  rounded-lg px-2 py-1 hover:text-gray-400">Change
                        Picture</button>
                </div>
            </div>
        </form>
        <form method="post" class="w-full px-8 py-4 flex flex-col space-y-2">
            <div class="space-x-2">
                <label for="">Change Name</label>
                <input type="text" name="" id="" class="border-2 outline-none rounded-lg px-2"
                    placeholder="enter your name">
            </div>
            <div class=" text-right">
                <button type="submit" class="border-2 px-2 py-1 rounded-lg">Submit</button>
            </div>
        </form>
    </div>
</body>

</html>