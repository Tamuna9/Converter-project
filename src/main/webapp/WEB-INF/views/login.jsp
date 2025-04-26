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
%>

<!DOCTYPE html>
<html lang="<%= language %>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= bundle.getString("title")%></title>
    <link rel="stylesheet" href="css/style.css">
    <script src="js/script.js"></script>
    <link rel="icon" href="data:," />
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
</header>

<main>
    <!-- Popup for login -->
            <form id="loginForm">
                <h2><%= bundle.getString("please_sign_in")%></h2>
                <%
                String username = bundle.getString("username");
                String password = bundle.getString("password");

                %>
                <p>
                    <label for="username">Username</label>
                    <input type="text" id="username" name="username" placeholder="<%= username %>" required autofocus>
                </p>
                <p>
                    <label for="password">Password</label>
                    <input type="password" id="password" name="password" placeholder="<%= password %>" required>
                </p>

                <button type="submit"><%= bundle.getString("sign_in")%></button>
                <a href="/"><%= bundle.getString("log_out")%></a>
            </form>

</main>


<footer>
    <%= bundle.getString("rights") %>
</footer>
</body>
</html>