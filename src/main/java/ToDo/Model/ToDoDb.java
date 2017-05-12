package ToDo.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoDb implements ToDoDAO{

    private final Connection conn;

    public ToDoDb(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Todo> getTodos() throws SQLException{
        List<Todo> toDos = new ArrayList<>();
        Statement statement = conn.createStatement();
        String sql = String.format("SELECT * FROM todos;");
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = Integer.valueOf(resultSet.getString("ID"));
            String name = resultSet.getString("Name");
            boolean isActive = Boolean.valueOf(resultSet.getString("isActive"));

            Todo getTodos = new Todo(id, name, isActive);
            toDos.add(getTodos);
        }
        return toDos;
    }

    @Override
    public Todo getTodo(int ID) throws SQLException{
        int id;
        String name;
        boolean isActive;
        Statement statement = conn.createStatement();
        String sql = String.format("SELECT * FROM todos WHERE ID = " + ID + ";");
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            id = Integer.valueOf(resultSet.getString("ID"));
            name = resultSet.getString("Name");
            isActive = Boolean.valueOf(resultSet.getString("isActive"));

            return new Todo(id, name, isActive);
        }
        return null;
    }

    @Override
    public Todo addTodo(String name) throws SQLException {
        getTodos();
        int newID = 0;
        Statement statement = conn.createStatement();
        String sql = String.format("INSERT into todos (Name, isActive) VALUES ('" + name + "', 'true');");
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);){

            int affectedRows = preparedStatement.executeUpdate();
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    newID = generatedKeys.getInt(1);
                }
            }
        }
        return new Todo(newID, name, true);
    }

    @Override
    public void toggleStatus(int ID) throws Exception {
        boolean isActive;

        Statement statement = conn.createStatement();
        String sql = String.format("SELECT * FROM todos WHERE ID = " + ID + ";");
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            isActive = Boolean.valueOf(resultSet.getString("isActive"));

           if(isActive == true){
                String toggle = String.format("UPDATE todos SET isActive = 'false' WHERE ID = " + ID + ";");
                statement.executeUpdate(toggle);

            } else {
                String toggle = String.format("UPDATE todos SET isActive = 'true' WHERE ID = " + ID + ";");
                statement.executeUpdate(toggle);
            }
        }

    }

    @Override
    public void deleteTodo(int ID) throws Exception {
        Statement statement = conn.createStatement();
        String delete = String.format("DELETE FROM todos WHERE ID = " + ID + ";");
        statement.executeUpdate(delete);

        }
}
