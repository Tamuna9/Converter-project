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

    <!-- Popup for register -->

            <form id="registerForm">
                <h2><%= bundle.getString("please_register")%></h2>
                <%
                String username = bundle.getString("username");
                String password = bundle.getString("password");
                String email = bundle.getString("email");
                %>
                <p>
                    <label for="registerUsername">Username</label>
                    <input type="text" id="registerUsername" name="username" placeholder="<%= username %>" required autofocus>
                </p>
                <p>
                    <label for="registerPassword">Password</label>
                    <input type="password" id="registerPassword" name="password" placeholder="<%= password %>" required>
                </p>
                <p>
                    <label for="registerMail">Email</label>
                    <input type="email" id="registerMail" name="email" placeholder="<%= email %>" required>
                </p>


                <button type="submit"><%= bundle.getString("register")%></button>
                <a href="/"><%= bundle.getString("log_out")%></a>
            </form>

</main>


<footer>
    <%= bundle.getString("rights") %>
</footer>
</body>
</html>