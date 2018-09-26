package ua.todoitem.todo;

import java.util.ArrayList;
import java.util.List;

public class ToDoDAO {

    private List<ToDoItem> items = new ArrayList<>();
    public List<ToDoItem> getAll(){
        return items;
    }
    public void addToDoItem(ToDoItem item) {
        items.add(item);
    }
    public boolean deleteToDoItem(String name) {
        return items.removeIf(t->t.name.equals(name));
    }

    public void updateToDoItem(String name,ToDoItem item) {
        for(int i=0;i<items.size();i++) {
            if(items.get(i).name.equals(name)) {
                items.set(i, item);
                return;
            }
        }
    }
}
