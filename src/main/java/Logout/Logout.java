package Logout;

import ToDo.Model.ToDoDAO;
import ToDo.Model.ToDoDb;
import ToDo.Util.ConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            try (Connection conn = ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.TodoApp)) {
                ToDoDAO dao = new ToDoDb(conn);
                Statement statement = conn.createStatement();
                String sql = String.format("DELETE * FROM todos;");
                statement.executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            response.sendRedirect("index.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
