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
public class selectSingleDataServlet extends HttpServlet {

	 DataBaseConnector dc = new DataBaseConnector();

	 public void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException,JSONException
	 {
		 
		 if(!dc.open())
			{
				System.out.println("Cannot open datasource");
				return;
		
			}
		 
		 
		 try {
			 String titler = req.getParameter("titler");
			 ResultSet rs = dc.displayARecord(titler);
					 JSONArray single_array = new JSONArray();
			 
			// if(rs.next()) {
			
		         JSONObject single_record = new JSONObject();
		         
		        
		         try {//for ordering the JSON Object
		             Field changeMap = single_record.getClass().getDeclaredField("map");
		             changeMap.setAccessible(true);
		             changeMap.set(single_record, new LinkedHashMap<>());
		             changeMap.setAccessible(false);
		           } catch (IllegalAccessException | NoSuchFieldException e) {
		               System.out.println(e.getMessage());
		           }
		         
		                
		         //Inserting key-value pairs into the json object
		      
		         single_record.put("id", rs.getInt(1));
		         single_record.put("title", rs.getString(2));
		         single_record.put("description", rs.getString(3));
		        
		         single_array.put(single_record);
		     //   }
		      
			 res.setContentType("application/json");
		        res.getWriter().print(single_array.toString());
		     //  System.out.println(array.toString()); 
		       dc.close();
			
			 
			 
			 
			 
		       System.out.println(single_array.toString(4));
			 
			 
		 }
		 catch(Exception e) {
			 
			 e.printStackTrace(); 
		 }
	 }



}
