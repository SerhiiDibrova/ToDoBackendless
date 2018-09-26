package ua.todoitem.todo;

import com.backendless.Backendless;
import com.backendless.servercode.IBackendlessService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService implements IBackendlessService {

    public void addToDoItem(String toDoName, ToDoItem item) {
        ToDoDAO dao = getToDo(toDoName);
        if (dao == null){ dao = new ToDoDAO();}
        dao.addToDoItem(item);
        item.objectId = null;
        Backendless.Cache.put(toDoName, dao);
    }

    public boolean deleteToDoItem(String toDoName, String name) {
        ToDoDAO dao = getToDo(toDoName);
        if (dao == null) {return false;}
        boolean result = dao.deleteToDoItem(name);
        Backendless.Cache.put(toDoName, dao);
        return result;
    }

    public void updateToDoItem(String toDoName, ToDoItem item, String name) {
        ToDoDAO dao = getToDo(toDoName);
        if (dao == null){ dao = new ToDoDAO();}
        dao.updateToDoItem(name, item);
        Backendless.Cache.put(toDoName, dao);

    }

    public List<ToDoItem> getItems(String toDoName) {
        ToDoDAO dao = getToDo(toDoName);
        if (dao == null) {dao = new ToDoDAO();}
        return dao.getAll();
    }

    private ToDoDAO getToDo(String toDoName) {
        if (toDoName == null || toDoName.trim().length() == 0){
            throw new IllegalArgumentException("Missing ToDo name");}
        return Backendless.Cache.get(toDoName, ToDoDAO.class);
    }
}
