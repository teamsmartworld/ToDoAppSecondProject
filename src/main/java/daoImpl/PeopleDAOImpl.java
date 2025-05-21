package daoimpl;

import dao.PeopleDAO;
import model.Person;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PeopleDAOImpl implements PeopleDAO {
    private static final String DB_URL = System.getenv("DB_URL");
    private static final String DB_USER = System.getenv("DB_USERNAME");
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    // SQL queries
    private static final String INSERT_PERSON =
            "INSERT INTO persons (first_name, last_name, email) VALUES (?, ?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM persons";
    private static final String SELECT_BY_ID = "SELECT * FROM persons WHERE id = ?";
    private static final String SELECT_BY_NAME =
            "SELECT * FROM persons WHERE first_name LIKE ? OR last_name LIKE ?";
    private static final String UPDATE_PERSON =
            "UPDATE persons SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
    private static final String DELETE_PERSON = "DELETE FROM persons WHERE id = ?";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public Person create(Person person) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_PERSON,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, person.getFirstName());
            stmt.setString(2, person.getLastName());
            stmt.setString(3, person.getEmail());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    person.setId(keys.getInt(1));
                }
            }
            return person;
        } catch (SQLException e) {
            throw new RuntimeException("Could not create person", e);
        }
    }

    // Implement other methods similarly...
}

