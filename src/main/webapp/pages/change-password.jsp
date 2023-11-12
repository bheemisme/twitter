<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./static/css/main.css" rel="stylesheet">
    <title>Change Password</title>
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

        <!-- error/success/info flags should appear here -->
        <div class="p-2 bg-slate-300 flex flex-row justify-between hidden">
            <p class="">errors flags should appear here</p>
            <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
        </div>
        <form method="post" class="w-full px-8 py-4 flex flex-col space-y-2">
            <div class="space-x-4">
                <label for="">Enter Old Password</label>
                <input type="text" name="" id="" class="border-2 outline-none rounded-lg px-2"
                    placeholder="enter old password">
            </div>
            <div class="space-x-2">
                <label for="">Enter New Password</label>
                <input type="text" name="" id="" class="border-2 outline-none rounded-lg px-2"
                    placeholder="enter new password">
            </div>
            <div class=" text-right">
                <button type="submit" class="border-2 px-2 py-1 rounded-lg hover:text-gray-400">Submit</button>
            </div>
        </form>
    </div>
</body>

</html>