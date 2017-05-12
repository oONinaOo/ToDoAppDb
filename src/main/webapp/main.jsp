<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>TodoApp</title>
    <link rel="stylesheet" type="text/css" href="style.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  </head>
  <div id ="input" class="addTodo"><input type = "text" size="50" name="todo" id="todo" placeholder="What needs to be done?"></div>
  <button id="add" class="myButton buttonadd" onclick="addTodo()">Add to list</button>
  <div id="todoList" class="fadeOnLoad"></div>
  <div id = "buttons">
  <button id = "all" class="myButton buttonall">All</button>
    <button id="active" class="myButton buttonactive">Active</button>
    <button id = "completed" class="myButton buttoncompleted">Completed</button>
    <button id = "delete" class="myButton buttondelete">Delete completed</button>
  </div>
  <script type="text/javascript" src="script.js"></script>
  </body>
</html>
