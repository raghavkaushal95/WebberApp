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
import java.util.LinkedHashMap;

public class selectDataServlet extends HttpServlet{
	 DataBaseConnector dc = new DataBaseConnector();
		
	 
	 
	 

	 public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException,JSONException//public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	 {
			
			
			if(!dc.open())
			{
				System.out.println("Cannot open datasource");
				return;
		
			}
			
		 
			 try {
				
				
				 
				// PreparedStatement st = con.prepareStatement("insert into notes values(?, ?)"); 
			//	 String title = req.getParameter("title");
				// String description = req.getParameter("description");
				// dc.CreateTableNotes();
				// dc.InsertIntoTableNotes(title, description);
				 
				 ResultSet rs = dc.DisplayAllRecords();
				 JSONArray array = new JSONArray();
				 while(rs.next()) {
			         JSONObject record = new JSONObject();
			         
			         try {//for ordering the JSON Object
			             Field changeMap = record.getClass().getDeclaredField("map");
			             changeMap.setAccessible(true);
			             changeMap.set(record, new LinkedHashMap<>());
			             changeMap.setAccessible(false);
			           } catch (IllegalAccessException | NoSuchFieldException e) {
			               System.out.println(e.getMessage());
			           }
			         
			         
			         
			         //Inserting key-value pairs into the json object
			         System.out.println("Note ID =  "+rs.getInt(1)+ " Note Name =  "+rs.getString(2)+" Note Description ="+rs.getString(3)+"\n");
			         record.put("id", rs.getInt(1));
			         record.put("title", rs.getString(2));
			         record.put("description", rs.getString(3));
			        
			         array.put(record);
			      }
				
				 res.setContentType("application/json");
			        res.getWriter().write(array.toString());
			     //  System.out.println(array.toString()); 
			       dc.close();
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
				 
			   
			 /*
		            PrintWriter out = res.getWriter(); 
		            out.println("<html><body><b>Successfully Inserted"
		                        + "</b></body></html>"); 
				*/ 
			        System.out.println(array.toString(4));//Pretty Print JSON using Indent Factor
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
