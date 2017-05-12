package ToDo.Model;

import java.util.List;

public interface ToDoDAO {
    List<Todo> getTodos() throws Exception;
    Todo getTodo(int ID) throws Exception;
    Todo addTodo (String name) throws Exception;
    void toggleStatus(int ID) throws Exception;
    void deleteTodo(int ID) throws Exception;

}
