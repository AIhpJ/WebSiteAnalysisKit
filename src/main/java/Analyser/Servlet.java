package Analyser;

import java.sql.*;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(name = "Servlet", urlPatterns = {"postdata"}, loadOnStartup = 1)
public class Servlet extends HttpServlet
{
    Connection conn;
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/fuwuwaibao?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    String user = "root";
    String password = "JHP691009jhp";
    Statement stmt = null;

    public void init()
    {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
    	String baseURI = request.getParameter("baseURI");
    	String date = request.getParameter("date");
    	String device = request.getParameter("device");
    	String element = request.getParameter("element");
        response.getWriter().print(baseURI+"\n");
        response.getWriter().print(date+"\n");
        response.getWriter().print(device+"\n");
        response.getWriter().print(element+"\n");
        Connection conn;
        Statement stmt = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        
            conn = DriverManager.getConnection(url,user,password);
        
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM fangwenjilu";
            ResultSet rs = stmt.executeQuery(sql);
            
    		
            while(rs.next()){
                int page  = rs.getInt("page");
    
                System.out.print(page);
                System.out.print("\n");
            }
            String sql1 = "INSERT INTO `fangwenjilu` (`date`, `IPaddress`, `device`, `SESSION`, `page`, `element`) VALUES ('"+date+"', '"+baseURI+"', '"+device+"', '1', '1', '"+element+"')";
			stmt.executeUpdate(sql1);
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        }
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        response.getWriter().print(request.getParameter("baseURI"));
    }
}
