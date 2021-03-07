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

public class insertDataServlet extends HttpServlet {
	
	
	 DataBaseConnector dc = new DataBaseConnector();
		
		
	
	
	 public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException//public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	 {
			
			
			if(!dc.open())
			{
				System.out.println("Cannot open datasource");
				return;
		
			}
			
		 
			 try {
				
				
				 
				// PreparedStatement st = con.prepareStatement("insert into notes values(?, ?)"); 
				 String title = req.getParameter("title");
				 String description = req.getParameter("description");
				 dc.CreateTableNotes();
				 dc.InsertIntoTableNotes(title, description);
				/* 
				 ResultSet rs = dc.DisplayAllRecords();
				 JSONArray array = new JSONArray();
				 while(rs.next()) {
			         JSONObject record = new JSONObject();
			         //Inserting key-value pairs into the json object
			         record.put("ID", rs.getInt("id"));
			         record.put("Note_title", rs.getString("title"));
			         record.put("Description", rs.getString("description"));
			        
			         array.put(record);
			      }
				 */
				/* res.setContentType("application/json");
			        res.getWriter().write(array.toString()); */
				 /*
				 st.setString(1, req.getParameter("title")); 
				  
		            // Same for second parameter 
		            st.setString(2, req.getParameter("description")); 
		  
		            // Execute the insert command using executeUpdate() 
		            // to make changes in database 
		            st.executeUpdate(); 
				 
		            
		            st.close(); 
		            con.close(); 
		           */ 
				 
			     dc.close();
			 
		            PrintWriter out = res.getWriter(); 
		            out.println("<html><body><b>Successfully Inserted"
		                        + "</b></body></html>"); 
				 
			 }
			 catch (Exception e) { 
		            e.printStackTrace(); 
		        } 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
		
		 
		 
		 
	   /*	 int i =  Integer.parseInt(req.getParameter("num1"));
		 int j = Integer.parseInt(req.getParameter("num2"));
		 
		 int k = i+j;
		 
		 PrintWriter out = res.getWriter(); 
		out.println("result is "+k);
	   */
		 
		 
		 
	 }
	 
	 
	
	
	
	
	
	

}
