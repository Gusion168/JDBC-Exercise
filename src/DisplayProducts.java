import java.sql.*;

public class DisplayProducts {
    // JDBC URL, username, and password of the MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3307/test10"; // adjust port if necessary
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "12345"; // replace with your actual password

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Establish a connection to the database
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Create a statement
            stmt = conn.createStatement();

            // Execute a query to retrieve all products
            rs = stmt.executeQuery("SELECT * FROM Product");

            // Iterate over the result set and print each product
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double pricePerUnit = rs.getDouble("price_per_unit");
                boolean activeForSell = rs.getBoolean("active_for_sell");

                System.out.printf("ID: %d, Name: %s, Price per Unit: %.2f, Active for Sell: %b%n",
                        id, name, pricePerUnit, activeForSell);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
