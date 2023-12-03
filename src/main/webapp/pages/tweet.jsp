<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import = "java.time.format.DateTimeFormatter" %>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.twitter.models.Tweet" %>
<%@ page import = "com.twitter.models.Comment" %>
<%@ page import = "com.twitter.models.User" %>
<!doctype html>
<html>

    <%@include file="head.jsp" %>

    <body class="h-screen sm:flex sm:flex-row">
        <%@include file="nav.jsp" %>

        <%
            Tweet tweet = (Tweet) request.getAttribute("tweet");
            String email = (String) session.getAttribute("email");
            String profile_image = (String) session.getAttribute("profile_image");
            
            ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
            if(tweet != null && email != null){         
        %>
        <div class="sm:w-[80%] flex flex-col items-left pb-8 sm:pb-0 border-t-8">
            <!-- error/success/info flags should appear here -->
            <div class="p-2 bg-slate-300 flex flex-row justify-between hidden">
                <p class="">errors flags should appear here</p>
                <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
            </div>
            <div class="flex flex-col items-center justify-between text-sm  border-b-2 p-4">

                <div class="flex flex-row  mt-4 px-2 items-start w-full space-x-4">
                    <img class="w-10 h-10 rounded-full object-cover " alt="tweet-owner" src=<%=tweet.getUserProfileImage()%> >
                    <div class="flex flex-col space-y-4 w-full">

                        <p class=" text-sm">
                            <% 
                                out.println(tweet.getContent());
                            %>
                        </p>
                        <div class="flex flex-row justify-between w-full">
                            <%
                                if(email.equals(tweet.getEmail())){
                            %>
                            <form action="/twitter/tweet" method="post" class="text-right">
                                <input hidden name="_method" value="delete" />
                                <input hidden type="text" name="tweet_id" value=<%=tweet.getId()%> />
                                <button type="submit" class="text-white bg-red-500 px-2 py-1 rounded-xl">Delete</button>
                            </form>
                            <%
                                }
                            %>
                            <div class="text-right w-full">
                                <time class="block" datetime=<%=tweet.getDate().toString()%> >
                                    <%
                                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm");
                                        String formattedDateTime = tweet.getDate().format(formatter);
                                        out.println(formattedDateTime);
                                    %>
                                </time>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <form method="post" action="/twitter/comment" class="flex flex-col justify-between text-sm  border-b-4 w-full p-2 space-y-4">
                <input hidden name="_method" value="post" />
                <div class="flex flex-row h-20 p-4">
                    <img class="w-10 h-10  object-cover rounded-full" alt="" src=<%=profile_image%> >
                    <textarea placeholder="Please comment"
                              name="comment"
                              cols="30"
                              rows="10"
                              class="w-full border-none outline-none border-2 p-2"></textarea>
                    <input hidden type="text" name="tweet_id" value=<%=tweet.getId()%> />
                </div>
                <div class="w-full text-right">
                    <button type="submit" class="bg-blue-400 text-white rounded-lg px-2 py-1 cursor-pointer">
                        Comment
                    </button>
                </div>
            </form>
            <div class="flex flex-col space-y-4 items-start px-4">
                <%
                    for(Comment comment: comments){
                %>
                <div class="flex flex-row  mt-4 items-center w-full border-b-2">
                    <a class="" href=<%="/twitter/profile?user_email="+comment.getEmail()%> >
                        <img  class="w-10 h-10 rounded-full object-cover " alt="comment-profile-image" src=<%=comment.getProfileImage()%>
                    </a>

                    <div class="flex flex-row justify-between w-full space-x-4">
                        <p class=" text-sm">
                            <%
                                out.println(comment.getContent());
                            %>
                        </p>
                        <%
                            if(comment.getEmail().equals(email)){
                        %>
                        <form action="/twitter/comment" method="post" class="text-right">
                            <input hidden name="_method" value="delete" />
                            <input hidden type="text" name="tweet_id" value=<%=tweet.getId()%> />
                            <input hidden type="text" name="comment_id" value=<%=comment.getId()%> />
                            <button type="submit" class="bg-red-500 text-white px-2 py-1 rounded-xl text-sm">Delete</button>
                        </form>
                        <%
                            }
                        %>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
        <%
            }
        %>

    </body>

</html>