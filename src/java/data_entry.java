import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "data_entry", urlPatterns = {"/data_entry"})
public class data_entry extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            // Retrieve customer information
            String customerName = request.getParameter("customer_name");
            String phoneNumber = request.getParameter("phone_number");
            String email = request.getParameter("email");
            int dogAmount = Integer.parseInt(request.getParameter("dog_amount"));

            // Retrieve dog information
            String[] dogNames = request.getParameterValues("dog_name[]");
            String[] dogBreeds = request.getParameterValues("dog_breed[]");
            String[] dogAges = request.getParameterValues("dog_age[]");
            String[] dogWeights = request.getParameterValues("dog_weight[]");

            //Get unique number to use as OwnerID
            UniqueNumbersGenerator gen_owner = new UniqueNumbersGenerator();
            int owner_id = gen_owner.generateUniqueNumber("pawsunited", "owners", "OwnerID");

            // SQL query to insert customer information into the owners table
            String owners_sql = "INSERT INTO owners (OwnerID, Name, Phone, Email) VALUES (?, ?, ?, ?)";

            // SQL query to insert dog information into the dogs table
            String dogs_sql = "INSERT INTO dogs (DogID, Name, Breed, Age, Weight, OwnerID, Status) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/pawsunited", "root", "");
                // Create a PreparedStatement
                PreparedStatement owners_data_entry = con.prepareStatement(owners_sql); // Set parameters
                owners_data_entry.setInt(1, owner_id); // Assuming id is the unique OwnerID
                owners_data_entry.setString(2, customerName);
                owners_data_entry.setString(3, phoneNumber);
                owners_data_entry.setString(4, email);
                // Execute the query
                int owners_rowsAffected = owners_data_entry.executeUpdate();
                int dogs_rowsAffected = 0;
                for (int i = 0; dogAmount > i; i++) {
                    //Get unique number to use as DogID each time
                    UniqueNumbersGenerator gen_dogs = new UniqueNumbersGenerator();
                    int dog_id = gen_dogs.generateUniqueNumber("pawsunited", "dogs", "DogID");

                    // Loop through each dog entry
                    String dogName = dogNames[i];
                    String dogBreed = dogBreeds[i];
                    int dogAge = Integer.parseInt(dogAges[i]);
                    double dogWeight = Double.parseDouble(dogWeights[i]);

                    // Create a PreparedStatement
                    PreparedStatement dogs_data_entry = con.prepareStatement(dogs_sql);

                    // Set parameters
                    dogs_data_entry.setInt(1, dog_id);
                    dogs_data_entry.setString(2, dogName);
                    dogs_data_entry.setString(3, dogBreed);
                    dogs_data_entry.setInt(4, dogAge);
                    dogs_data_entry.setDouble(5, dogWeight);
                    dogs_data_entry.setInt(6, owner_id);
                    dogs_data_entry.setString(7, "In Daycare");

                    dogs_rowsAffected += dogs_data_entry.executeUpdate();
                }
                if (owners_rowsAffected > 0 && dogs_rowsAffected > 0) {
                    
                    RequestDispatcher rd = request.getRequestDispatcher("appointment.jsp");
                    rd.forward(request, response);
                } else {
                   
                    request.setAttribute("submitFlag", "false");
                    RequestDispatcher rd = request.getRequestDispatcher("appointment.jsp");
                    rd.forward(request, response);
                }
                con.close();
            } catch (Exception e) {
                out.print("Error occur:" + e.getMessage());
            }
        }
    }
}
