<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.time.format.DateTimeFormatter" %>
<%@ page import = "com.twitter.models.Tweet" %>
<!doctype html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="./static/css/main.css" rel="stylesheet">
        <link href="./static/css/custom.css" rel="stylesheet">
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
                <img alt="profile-image" 
                     class="w-32 h-32 rounded-full overflow-hidden object-cover"
                     src=<%=(String)session.getAttribute("profile_image")%>
                     >
                <span class="block">
                    <%=session.getAttribute("username")%>
                </span>
            </div>
            <div class="w-full px-2 py-4">
                <%
                    ArrayList<Tweet> tweets = (ArrayList<Tweet>) request.getAttribute("tweets");
                    if(tweets != null){
                    for(Tweet tweet: tweets){                 
                %>
                <div class="flex flex-col items-left justify-between text-sm  border-b-2">
                    
                    <a href=<%="/twitter/tweet?tweet_id="+tweet.getId()%> >
                        <p class="p-2 text-sm text-left">
                            <%
                                out.print(tweet.getContent());
                            %>
                        </p>
                    </a>
                    
                    <div class="flex flex-row justify-between w-full px-2">
                        <a class="rounded-full w-full cursor-pointer" href=<%="/twitter/tweet/"+tweet.getId()%> >
                            <img src="./static/images/comment.png" alt="comment" class="rounded-full w-8 h-10 object-contain">
                        </a>
                        <time class="block" datetime=<%=tweet.getDate().toString()%> >
                            <%
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm");
                                String formattedDateTime = tweet.getDate().format(formatter);
                                out.print(formattedDateTime);
                            %>
                        </time>
                    </div>

                </div>
                <%}}%>
                
            </div>
        </div>
    </body>

</html>