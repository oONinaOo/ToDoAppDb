package ToDo.Model;

public class Todo {
    private int ID;
    private String name;
    private boolean isActive;

    public Todo(int ID, String name, boolean isActive) {
        this.ID = ID;
        this.name = name;
        this.isActive = isActive;
    }
    public Todo(String ID, String name, boolean isActive){
        this.ID = Integer.valueOf(ID);
        this.name = name;
        this.isActive = isActive;
    }

    public Todo() {
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

