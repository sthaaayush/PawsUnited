



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class UniqueNumbersGenerator {

    // Method to generate a unique 3-digit number not present in the specified column of the table
    public static int generateUniqueNumber(String databaseName, String tableName, String columnName) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, "root", "");
            statement = connection.createStatement();

            // Generate a random 3-digit number
            Random random = new Random();
            int randomNumber = random.nextInt(900) + 100; // Generate a random number between 100 and 999

            // Check if the generated number already exists in the specified column of the table
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = " + randomNumber);
            resultSet.next();
            int count = resultSet.getInt(1);

            // If the number already exists, generate another number
            while (count > 0) {
                randomNumber = random.nextInt(900) + 100;
                resultSet = statement.executeQuery("SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = " + randomNumber);
                resultSet.next();
                count = resultSet.getInt(1);
            }

            return randomNumber;
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
            return -1; // Return -1 in case of an error
        } finally {
            // Close the database resources
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
