import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class deleteSingleNote extends HttpServlet {
	 DataBaseConnector dc = new DataBaseConnector();
	
	 public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	 {
		
	
	 
			if(!dc.open())
			{
				System.out.println("Cannot open datasource");
				return;
		
			}
			
			 try {
				 String title = req.getParameter("titler");
				// String desc = req.getParameter("n");
				 
				 int retroValue =dc.DeleteTableNotes(title);
				
				 
				 
				 if(retroValue==1)
				 {
		            PrintWriter out = res.getWriter(); 
		            out.println("<html><body><b>Successfully Deleted the  Record"
		                        + "</b></body></html>"); 
				 }
				 
				 else if (retroValue==-1){
					  PrintWriter mout = res.getWriter(); 
			           mout.println("<html><body><b>Record Not Deleted Some Error occured"
			                        + "</b></body></html>"); 
					 
				 }
			 }
			 
			 catch(Exception e)
			 {
				 e.printStackTrace();
				 
			 }
			 
	 
	 
	 
	 
	 
	 }
	
	
	
}
