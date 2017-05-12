<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String userName = null;
  Cookie[] cookies = request.getCookies();
  if(cookies !=null){
    for(Cookie cookie : cookies){
      if(cookie.getName().equals("name")) userName = cookie.getValue();
    }
  }
  if(userName == null) response.sendRedirect("index.jsp");
%>
<html>
  <head>
    <title>TodoApp</title>
    <link rel="stylesheet" type="text/css" href="style.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  </head>
  <div id ="input" class="addTodo"><input type = "text" size="50" name="todo" id="todo" placeholder="What needs to be done?"></div>
  <button id="add" class="myButton buttonadd" onclick="addTodo()">Add to list</button>
  <div id="todoList" class="fadeOnLoad"></div>
  <div class="user">Hello, <%=userName%></div>
  <div id = "buttons">
  <button id = "all" class="myButton buttonall">All</button>
    <button id="active" class="myButton buttonactive">Active</button>
    <button id = "completed" class="myButton buttoncompleted">Completed</button>
    <button id = "delete" class="myButton buttondelete">Delete completed</button>
      <form action="/logout" method="post">
    <input type="submit" id = "logout" class="myButton buttonlogout" value="Logout">
      </form>
  </div>
  <script type="text/javascript" src="script.js"></script>
  </body>
</html>
