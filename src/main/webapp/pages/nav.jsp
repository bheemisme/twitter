<%-- 
    Document   : nav.jsp
    Created on : Nov 12, 2023, 10:19:31?AM
    Author     : sudarshan
--%>

<nav
    class="flex flex-row justify-between sm:justify-normal sm:flex-col sm:w-[20%] sm:border-r-2 sm:border-blue-400 sm:h-full sm:space-y-6 px-2 py-2 fixed bottom-0 bg-gray-400 w-full sm:bg-white sm:items-center sm:static">

    <a class="inline sm:block cursor-pointer" href="/twitter/home">
        <img src="./static/images/logo.png" alt="twitter-logo"
             class="w-8 h-8 rounded-full overflow-hidden object-cover sm:block">
    </a>
    <a class="inline cursor-pointer" href="/twitter/profile">
        <img src="./static/images/profile.png" alt="twitter-logo"
             class="inline w-8  overflow-hidden object-cover ">
    </a>
    <a class="inline cursor-pointer" href="/twitter/followers">
        <img src="./static/images/share.png" alt="twitter-logo" class="inline w-8  overflow-hidden object-cover"/>
    </a>
    <a class="inline cursor-pointer" href="/twitter/settings">
        <img src="./static/images/settings.png" alt="twitter-logo"
             class="inline w-8  overflow-hidden object-cover ">
    </a>

    <a class="inline cursor-pointer" href="/twitter/notifications">
        <img src="./static/images/notify.png" alt="notification" class="inline w-8  overflow-hidden object-cover ">
    </a>


</nav>
