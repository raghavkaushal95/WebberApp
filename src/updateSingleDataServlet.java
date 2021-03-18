
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import java.lang.reflect.Field;



public class updateSingleDataServlet extends HttpServlet
{
	DataBaseConnector dc = new DataBaseConnector();
   
	 public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException{
		 
			if(!dc.open())
			{
				System.out.println("Cannot open datasource");
				return;
		
			}
		 
			
		 try {
			 String titler = req.getParameter("titler");
			 String desc = req.getParameter("n");
			 
			 int retroValue =dc.UpdateTableNotes(titler, desc);
			 
			 
			 
			 if(retroValue==1)
			 {
	            PrintWriter out = res.getWriter(); 
	            out.println("<html><body><b>Successfully Updated Record"
	                        + "</b></body></html>"); 
			 }
			 
			 else if (retroValue==-1){
				  PrintWriter mout = res.getWriter(); 
		           mout.println("<html><body><b>Record Not Updated Some Error occured"
		                        + "</b></body></html>"); 
				 
			 }
		 }
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 
		 }
		 
		 
	 }
	
	
}
