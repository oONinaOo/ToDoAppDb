package ToDo.Model;

import java.util.ArrayList;
import java.util.List;

public class ToDoMem implements ToDoDAO {


    public static final ToDoDAO INSTANCE = new ToDoMem();
    public static final List<Todo> toDos = new ArrayList<>();


    @Override
    public List<Todo> getTodos(){

        return toDos;
    }

    @Override
    public Todo getTodo(int ID) {

        for (int i=0; i<toDos.size(); i++){
          if(toDos.get(i).getID() == ID){
                return toDos.get(i);
            }
        } return null;
    }

    @Override
    public Todo addTodo(String name) throws Exception {
        return new Todo(toDos.size()+1, name, true);

    }


    @Override
    public void toggleStatus(int ID) {
        for (int i=0; i<toDos.size(); i++){
            if(toDos.get(i).getID() == ID){
                if(toDos.get(i).isActive() == true){
                    toDos.get(i).setActive(false);
                } else{
                    toDos.get(i).setActive(true);
                }
            }
        }
    }

    @Override
    public void deleteTodo(int ID) {
        for (int i=0; i<toDos.size(); i++) {
            if (toDos.get(i).getID() == ID) {
                toDos.remove(toDos.get(i));
            }
        }
    }
}
