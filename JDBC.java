import java.sql.*;

public class PatientInfo {
    // JDBC URL, username, and password of Oracle database
    static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:XE";
    static final String USERNAME = "your_username";
    static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establishing connection to the database
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Creating statement
            statement = connection.createStatement();

            // Executing query to fetch patient information
            resultSet = statement.executeQuery("SELECT * FROM Patients");

            // Displaying patient information
            System.out.println("Patient Information:");
            System.out.println("ID\tName\tProblem\tBill");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String problem = resultSet.getString("Problem");
                double bill = resultSet.getDouble("Bill");
                System.out.println(id + "\t" + name + "\t" + problem + "\t" + bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
