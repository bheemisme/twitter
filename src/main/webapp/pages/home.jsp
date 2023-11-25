<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.time.format.DateTimeFormatter" %>
<%@ page import = "com.twitter.models.Tweet" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>`

<!DOCTYPE html>
<html>

    <%@include file="./head.jsp" %>

    <body class="h-screen sm:flex sm:flex-row">
        <%@include file="nav.jsp" %>
        <div class="sm:w-[80%] flex flex-col items-center pb-8 sm:pb-0 border-t-8">

            <form method="post" 
                  class="flex flex-col justify-between text-sm  border-b-4 w-full p-2 space-y-4"
                  action="/twitter/tweet"
                  >
                <input hidden name="_method" value="post" />
                <div class="flex flex-row h-20 p-4">
                    <img  class="w-10 h-10  object-cover rounded-full" alt="profile-image" src=<%=(String)session.getAttribute("profile_image")%> >
                    <textarea placeholder="Tweet any thing"
                              name="tweet"
                              cols="30"
                              rows="10"
                              class="w-full border-none outline-none border-2 p-2"></textarea>
                </div>
                <div class="w-full text-right">
                    <button type="submit" class="bg-blue-400 text-white rounded-lg px-2 py-1 cursor-pointer">Tweet</button>
                </div>
            </form>


            <!-- error/success/info flags should appear here -->
            <!--            <div class="p-2 bg-slate-300 flex flex-row justify-between">
                                <p class="">errors flags should appear here</p>
                                <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
                            </div>-->
            <div class="flex flex-col w-full">
            <%
                    ArrayList<Tweet> tweets = (ArrayList<Tweet>) request.getAttribute("tweets");
                    if(tweets != null){
                    for(Tweet tweet: tweets){                 
                %>
            <div class="flex flex-col items-center justify-between text-sm  border-b-2 space-y-4 px-4">

                <div class="flex flex-row  mt-4 px-2 items-start w-full space-x-4">
                    <a href="/twitter/profile" >
                        <img src="./static/images/profile-img.jpg" class="w-10 h-10 rounded-full object-cover " alt="">
 
                    </a>
                    <a href=<%="/twitter/tweet?tweet_id="+tweet.getId()%> >
                    <p class=" text-sm">
                        <% 
                            out.println(tweet.getContent());
                        %>
                    </p>
                    </a>
                </div>
                <div class="flex flex-row justify-between w-full px-2">
                    <a class="rounded-full w-full cursor-pointer" href="#" >
                        <img src="./static/images/comment.png" alt="comment" class="rounded-full w-8 h-10 object-contain">
                    </a>
                    <time class="block" datetime=<%=tweet.getDate().toString()%> >
                        <%
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm");
                            String formattedDateTime = tweet.getDate().format(formatter);
                            out.println(formattedDateTime);
                        %>
                    </time>
                </div>
            </div>
            <%}}%>
            </div>
        </div>
    </body>

</html>