

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import security_protocol.hashing;

@WebServlet(name = "login_register_handler", urlPatterns = {"/login_register_handler"})
public class login_register_handler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            //Checks if request if of post type
            if (request.getMethod().equalsIgnoreCase("post")) {
                //Storing client data in variables
                String uemail = request.getParameter("uemail");
                String upass = request.getParameter("upassword");
                
                //Checks if its admin login
                if (uemail.equals("admin@gmail.com") && upass.equals("admin123")) {
                    RequestDispatcher dispatch = request.getRequestDispatcher("customer_details.jsp");
                    dispatch.forward(request, response);
                }

                String check_flag = request.getParameter("submit");
                //Hash the validated password
                if (!upass.equals(" ")) {
                    upass = hashing.hashMD5(upass);
                } else {
                    upass = "error";
                }
                //SQL to get and set data in database
                String set_data = "INSERT INTO user_details (user_first_name, user_last_name, user_email, user_password) VALUES (?, ?, ?, ?);";
                String get_data = "SELECT * FROM user_details;";

                //Database connection process
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sxc_GG", "root", "");

                    //Handle request if its for register or signUp
                    if (check_flag.equals("signUp")) {
                        //storing client name in variables 
                        String fname = request.getParameter("fname");
                        String lname = request.getParameter("lname");
                        //Create statement to execute sql query
                        PreparedStatement signUp_statement = con.prepareCall(set_data);
                        //setting data to send through sq;
                        signUp_statement.setString(1, fname);
                        signUp_statement.setString(2, lname);
                        signUp_statement.setString(3, uemail);
                        signUp_statement.setString(4, upass);
                        int update_flag = signUp_statement.executeUpdate();
                        //checking if row is updated by data or not
                        if (update_flag != 0) {
                            out.println("<script>alert('You can login now !!');</script>");
                            RequestDispatcher dispatch = request.getRequestDispatcher("index.html");
                            dispatch.include(request, response);
                        } else {
                            out.println("<script>alert('Error while register');</script>");
                            RequestDispatcher dispatch = request.getRequestDispatcher("index.html");
                            dispatch.include(request, response);
                        }
                    } //Handle request if its for login or signIn
                    else {
                        //Preparing statement to retrive data in database
                        PreparedStatement signIn_statement = con.prepareCall(get_data);
                        //Storing retrive data from database
                        ResultSet user_logs_data = signIn_statement.executeQuery();
                        //Initilizing flags and name for validation
                        boolean user_validate_flag = false;
                        String user_name = null;
                        //Verifying user with data in database
                        while (user_logs_data.next()) {
                            String gEmail = user_logs_data.getString("user_email"),
                                    gPass = user_logs_data.getString("user_password");
                            user_name = user_logs_data.getString("user_first_name") + " " + user_logs_data.getString("user_last_name");
                            //Execute and break loop when user is verified
                            if (gEmail.equals(uemail) && gPass.equals(upass)) {
                                user_validate_flag = true;
                                break;
                            }
                            //Or else it dont verify user
                        }
                        if (user_validate_flag) {
                            request.setAttribute("user_name", user_name);
                            //Session to activate user logged status
                            HttpSession is_logged = request.getSession();
                            is_logged.setAttribute("logged", true);
                            //Session to know user id throughout page
                            HttpSession user_id = request.getSession();
                            is_logged.setAttribute("user_id", user_logs_data.getString("user_id"));
                            
                            out.println("<script>alert('Welcome "+user_name+"!!');</script>");
                            RequestDispatcher dispatch = request.getRequestDispatcher("index.html");
                            dispatch.include(request, response);
                        } else {
                            out.println("<script>alert('Wrong Email or Password');</script>");
                            RequestDispatcher dispatch = request.getRequestDispatcher("index.html");
                            dispatch.include(request, response);
                        }
                    }
                    con.close();
                } catch (Exception e) {
                    out.println("Error occured: " + e.getMessage());
                }
            }
        }
    }
}