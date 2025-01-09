import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        // Inside RegisterServlet
if (password.equals(confirmPassword)) {
    if (registerUser(name, email, password)) {
        response.sendRedirect("success.html");  // Redirect to success.html after successful registration
    } else {
        request.setAttribute("errorMessage", "Registration failed. Try again.");
        request.getRequestDispatcher("register.html").forward(request, response);
    }
} else {
    request.setAttribute("errorMessage", "Passwords do not match.");
    request.getRequestDispatcher("register.html").forward(request, response);
}

    }

    private boolean registerUser(String name, String email, String password) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fintrack_db", "root", "Nandana@13")) {
            String query = "INSERT INTO Users (username, email, password) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, password);
            int rowsInserted = stmt.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
