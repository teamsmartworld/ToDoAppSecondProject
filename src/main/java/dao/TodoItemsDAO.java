package dao;

import model.Todo;
import model.Person;
import java.util.Collection;

public interface TodoItemsDAO {
    Todo create(Todo todo);
    Collection<Todo> findAll();
    Todo findById(int id);
    Collection<Todo> findByDoneStatus(boolean done);
    Collection<Todo> findByAssignee(int assigneeId);
    Collection<Todo> findByAssignee(Person assignee);
    Collection<Todo> findUnassignedTodoItems();
    Todo update(Todo todo);
    boolean deleteById(int id);
}

