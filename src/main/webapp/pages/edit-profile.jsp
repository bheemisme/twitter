<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html>

<%@include file="head.jsp" %>
<body class="h-screen py-4 sm:flex sm:flex-row">
    
    <%@include file="nav.jsp" %>
    <div class="sm:w-[80%] flex flex-col items-center pb-8 sm:pb-0">

        <form method="post" action="/twitter/edit" 
              enctype="multipart/form-data"
              class="flex flex-row space-x-4 w-full border-b-4 space-y-2 p-2">
            <input hidden name="method" value="image" />
            <img  alt="profile-image" class="w-16 h-16 rounded-full overflow-hidden object-cover"
                  src=<%=(String)session.getAttribute("profile_image")%>
                  >
            <div class="flex flex-col space-y-4">
                <input type="file" class="" name="image">
                <div class="text-left  cursor-pointer">
                    <button type="submit"  class="border-2  rounded-lg px-2 py-1 hover:text-gray-400">
                        Change Picture
                    </button>
                </div>
            </div>
        </form>
        <form method="post" action="/twitter/edit" class="w-full px-8 py-4 flex flex-col space-y-2">
            <input hidden name="method" value="username" />
            <div class="space-x-2">
                <label for="">Change Name</label>
                <input type="text" name="username" value=<%=(String)session.getAttribute("username")%>  class="border-2 outline-none rounded-lg px-2"
                    placeholder="enter your name">
            </div>
            <div class=" text-right">
                <button type="submit" class="border-2 px-2 py-1 rounded-lg">Submit</button>
            </div>
        </form>
    </div>
</body>

</html>