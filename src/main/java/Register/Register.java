package Register;

import ToDo.Model.ToDoDAO;
import ToDo.Model.ToDoDb;
import ToDo.Util.ConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.TodoApp)) {
            HashMap<String, String> loginData = new HashMap<>();
            String getUsername = request.getParameter("regusername");
            String getPassword = request.getParameter("regpw");

            ToDoDAO dao = new ToDoDb(conn);
            Statement statement = conn.createStatement();
            String register = String.format("INSERT into users (Username, Password) VALUES ('" + getUsername + "', '" +getPassword + "');");
            statement.executeUpdate(register);

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<script type=\"text/javascript\">");
            out.println("alert('Registered Successfully');");
            out.println("location='index.jsp';");
            out.println("</script>");

        } catch (Exception e) {
            throw new ServletException();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
