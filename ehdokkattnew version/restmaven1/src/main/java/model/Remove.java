package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Remove")
public class Remove extends HttpServlet {
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    response.setContentType("text/html");
//    PrintWriter out = response.getWriter();

    String poistettavaId = request.getParameter("poistettavaId");

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaalikone", "root", "");
        PreparedStatement ps = con.prepareStatement("delete from Ehdokkaat where ehdokas_id=?");
        ps.setString(1, poistettavaId);

        int i = ps.executeUpdate();


    } catch (Exception e) {
        System.out.println(e);
    }
    
    RequestDispatcher rd=request.getRequestDispatcher("lahetajsonn.html");
    rd.forward(request, response);
    
}

}
