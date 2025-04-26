<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.*, java.text.MessageFormat" %>

<%
// get language from session
String language = request.getParameter("language");
if (language == null)language = (String) session.getAttribute("language");
if (language == null)language = "en"; //default language;

session.setAttribute("language", language);
Locale locale = new Locale(language);
ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);

String username = (String)session.getAttribute("username");
Long userId = (Long)session.getAttribute("userId");
%>

<!DOCTYPE html>
<html lang="<%= language %>" xmlns:th="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= bundle.getString("title")%></title>
    <link rel="stylesheet" href="css/style.css">

    <link rel="icon" href="data:," />
    <script>
        const USER_ID = <%= userId != null ? userId : -1 %>;
    </script>

    <link rel="icon" href="data:," />
</head>
<body>
<header>
    <h1><%= bundle.getString("title")%></h1>

    <div class="languagePanel">
        <a href="?language=ru">RU</a>
        <a href="?language=en">EN</a>
        <a href="?language=he">HE</a>

    </div>
<p> <%= MessageFormat.format(bundle.getString("welcome"), username) %> </p>

</header>

<h1><%= bundle.getString("conversion_history")%></h1>
<div id="historyContainer">

</div>
<a href="/"><%= bundle.getString("log_out")%></a>



<footer>
    All rights reserved &copy; 2024
</footer>
<script src="js/script.js" defer></script>
</body>
</html>