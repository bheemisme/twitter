<%-- 
    Document   : notifications
    Created on : Nov 12, 2023, 10:16:45 AM
    Author     : rohitt kumar
--%>

<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList" %>
<%@ page import = "java.time.format.DateTimeFormatter" %>
<%@ page import = "com.twitter.models.Notification" %>

<!DOCTYPE html>
<html>

    <%@include file="./head.jsp" %>
    <body class="h-screen sm:flex sm:flex-row">
        <%@include file="nav.jsp" %>
        <div class="sm:w-[80%] flex flex-col items-left pb-8 sm:pb-0 border-t-8">
            <!-- error/success/info flags should appear here -->
            <!-- <div class="p-2 bg-slate-300 flex flex-row justify-between">
                <p class="">errors flags should appear here</p>
                <button class="border-2 rounded-full px-2 cursor-pointer">x</button>
            </div> -->
            <%
                ArrayList<Notification> notifications = (ArrayList<Notification>) request.getAttribute("notifications");
                for(Notification not: notifications){
            %>
            <div class="border-b-2 p-2 hover:text-gray-500 flex flex-row space-x-4 justify-between">

                <a class="px-2 hover:text-gray-500 flex flex-col" href=<%="/twitter/tweet?tweet_id=" + not.getTweetId()%> >
                    <span>
                        <%=not.getContent()%>
                    </span>

                </a>
                <time class="block" class="" datetime=<%=not.getCreationTime().toString()%> >
                    <%
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy HH:mm");
                        String formattedDateTime = not.getCreationTime().format(formatter);
                        out.print(formattedDateTime);
                    %>

                </time>

            </div>


            <%}%>
        </div>
    </body>

</html>