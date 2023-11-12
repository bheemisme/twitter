<%@ page isErrorPage="true" %>

<!doctype html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="./static/css/main.css" rel="stylesheet">
    <title>Home</title>
</head>

<body class="h-screen sm:flex sm:flex-row">
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
    <div class="sm:w-[80%] flex flex-row items-center justify-center h-full">
        <div class="text-center">
            <p class="text-2xl text-blue-400">403</p>
            <p>Error message</p>
        </div>
    </div>
</body>

</html>