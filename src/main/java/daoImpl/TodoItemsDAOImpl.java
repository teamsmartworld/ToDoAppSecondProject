package daoimpl;

import dao.TodoItemsDAO;
import model.Todo;
import model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TodoItemsDAOImpl implements TodoItemsDAO {
    // Database connection details (similar to PeopleDAOImpl)

    private static final String INSERT_TODO =
            "INSERT INTO todos (title, description, deadline, done, assignee_id) VALUES (?, ?, ?, ?, ?)";
    // ... other SQL queries

    @Override
    public Todo create(Todo todo) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_TODO,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, todo.getTitle());
            stmt.setString(2, todo.getDescription());
            stmt.setTimestamp(3, Timestamp.valueOf(todo.getDeadline()));
            stmt.setBoolean(4, todo.isDone());
            stmt.setObject(5, todo.getAssignee() != null ?
                    todo.getAssignee().getId() : null);

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    todo.setId(keys.getInt(1));
                }
            }
            return todo;
        } catch (SQLException e) {
            throw new RuntimeException("Could not create todo", e);
        }
    }

    // Implement other methods similarly...
}
