package ToDo.Servlet;


import ToDo.Model.ToDoDAO;
import ToDo.Model.ToDoDb;
import ToDo.Model.Todo;
import ToDo.Util.ConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class ToDoServlet extends javax.servlet.http.HttpServlet {
    //ToDoDAO todoDao = ToDoMem.INSTANCE;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String name = buffer.toString();

        try (Connection conn = ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.TodoApp)) {
            ToDoDAO dao = new ToDoDb(conn);
            Todo newTodo = dao.addTodo(name);

            ObjectMapper getList = new ObjectMapper();
            try {
                getList.writer().writeValue(response.getOutputStream(), dao.getTodo(newTodo.getID()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

        /*Todo newTodo = null;
        try {
            newTodo = todoDao.addTodo(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            todoDao.getTodos().add(newTodo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int sizeOfTodos = 0;
        try {
            sizeOfTodos = todoDao.getTodos().size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ObjectMapper getList = new ObjectMapper();
        try {
            getList.writer().writeValue(response.getOutputStream(), todoDao.getTodo(sizeOfTodos));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        try (Connection conn = ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.TodoApp)) {
            ToDoDAO dao = new ToDoDb(conn);
            ObjectMapper getList = new ObjectMapper();
            getList.writer().writeValue(response.getOutputStream(), dao.getTodos());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
