package lesson4.repository;

import java.sql.*;

public class DbWorker {
    private Connection connection;

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String user = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:5432/testdb";
        connection = DriverManager.getConnection(url, user, password);
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void execute(String query, Object... args) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();
        }
    }

    public ResultSet select(String query, Object... args) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeQuery();
        }
    }
}
