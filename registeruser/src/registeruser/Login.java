package registeruser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.http.*;
import javax.servlet.*;
public class Login extends HttpServlet{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String user=req.getParameter("userName");
		String pwd=req.getParameter("userPass");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/userdb?characterEncoding=latin1",name="root",pass="Goodluck123$";
			Connection con=DriverManager.getConnection(url,name,pass);
			Statement st=con.createStatement();
			String query="select * from registeruser where userName='"+user+"' and userPass='"+pwd+"'";
			ResultSet rs=st.executeQuery(query);
			//out.print(rs.getString("userEmail"));
			if(rs.next())
			{
				out.print("Login Successful<br>");
				out.print("The email is: "+rs.getString("userEmail"));
			}
			else
			{
				out.print("Wrong Password! Enter correct username and password!");
				RequestDispatcher rd=req.getRequestDispatcher("register.html");
				rd.include(req, res);
			}
		}
		catch(Exception e) {System.out.print(e);}
		//int result1=stmt.executeUpdate("DELETE FROM invited WHERE invited.player_id = '"+playerId+"' AND invited.contact = '"+contact+"'");  
	}
}
