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

    <a href="./login"><%= bundle.getString("sign_in")%></a>
    <a href="./register"><%= bundle.getString("register")%></a>

</header>

<main>

    <form>
        <%
        String enterAmount = bundle.getString("enter_amount");
        %>
    <h1><%= bundle.getString("converter")%></h1>
    <label for="amount"><%= bundle.getString("amount")%></label>
    <input type="text" id="amount" name="amount" placeholder="<%= enterAmount %>"><br>
        <label for="from_currency"><%= bundle.getString("from_currency")%></label>
        <input list="from_currency_list" id="from_currency" name="from_currency" required oninput=" this.value = this.value.toUpperCase(); ">
        <datalist id="from_currency_list"></datalist><br>
        <label for="to_currency"><%= bundle.getString("to_currency")%></label>
        <input list="to_currency_list" id="to_currency" name="to_currency" required oninput=" this.value = this.value.toUpperCase(); ">
        <datalist id="to_currency_list"></datalist><br>
    <%
    String  convertLabel = bundle.getString("convert");
    String resetLabel = bundle.getString("reset");
    %>
        <button type="button" id="convertButton" value="<%= convertLabel %>"><%=bundle.getString("convert")%></button>
    <input type="reset" value="<%= resetLabel %>" >
    <div id="result"></div>
    <div id="error" style="color:red;"></div>
</form>
</main>


<footer>
    <%= bundle.getString("rights") %>
</footer>
<script src="js/script.js"></script>
</body>
</html>