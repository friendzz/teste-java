import java.sql.*;

public class UserService {
    private final String dbUrl = "jdbc:mysql://localhost:3306/mydb";
    private final String dbUser = "user";
    private final String dbPassword = "password";

    public String getUserById(int userId) throws SQLException {
        String userInfo = "";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String query = "SELECT * FROM users WHERE id = " + userId;
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userInfo = "User ID: " + resultSet.getInt("id") + ", ";
                userInfo += "Name: " + resultSet.getString("name") + ", ";
                userInfo += "Email: " + resultSet.getString("email");
            }
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return userInfo;
    }

    public void createUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            String query = "INSERT INTO users (name, email) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();
        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }
}