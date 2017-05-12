package Login;

import ToDo.Model.ToDoDAO;
import ToDo.Model.ToDoDb;
import ToDo.Util.ConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;


public class Login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection conn = ConnectionUtil.getConnection(ConnectionUtil.DatabaseName.TodoApp)) {
            HashMap<String, String> loginData = new HashMap<>();
            String getUsername = request.getParameter("logusername");
            String getPassword = request.getParameter("logpw");

            ToDoDAO dao = new ToDoDb(conn);
            Statement statement = conn.createStatement();
            String login = String.format("Select * FROM users;");

            ResultSet resultSet = statement.executeQuery(login);
            while (resultSet.next()) {
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");

                loginData.put(username,password);
            }

            if(loginData.containsKey(getUsername)){
                if(loginData.containsValue(getPassword)){
                    Cookie loginCookie = new Cookie("name", getUsername);
                    loginCookie.setMaxAge(60*60);
                    response.addCookie(loginCookie);
                    response.sendRedirect("main.jsp");
                }
            }else{
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Incorrect username or password, try it again!');");
                out.println("location='index.jsp';");
                out.println("</script>");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
