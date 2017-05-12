package ToDo.Servlet;

import ToDo.Model.ToDoDAO;
import ToDo.Model.ToDoDb;
import ToDo.Model.Todo;
import ToDo.Util.ConnectionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;


@WebServlet(name = "DeleteToDo")
public class DeleteToDo extends HttpServlet {
    //ToDoDAO todoDao = ToDoMem.INSTANCE;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String data = buffer.toString();

        JsonParser parser = new JsonParser();
        Object object = parser.parse(data);
        JsonArray array = (JsonArray) object;

        for (int i = 0; i < array.size(); i++) {
            ObjectMapper readDelete = new ObjectMapper();
            Todo delete = readDelete.readValue(array.get(i).toString(), Todo.class);
            try {
                //todoDao.deleteTodo(delete.getID());
                try (Connection conn = ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.TodoApp)) {
                    ToDoDAO dao = new ToDoDb(conn);
                    dao.deleteTodo(delete.getID());

                } catch (Exception e) {
                    throw new ServletException(e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
