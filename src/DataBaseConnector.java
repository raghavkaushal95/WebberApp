import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


public class DataBaseConnector {
	
	public String COLUMN_NOTE_ID ="id";
	public String COLUMN_NOTE_NAME ="title";
	public String COLUMN_NOTE_DESC ="description";
	public static final String DB_NAME="pascal.db";//C:\Users\super\ServletS\WebberApp
	public static final String Connection_String ="jdbc:sqlite:C:\\Users\\super\\ServletS\\WebberApp\\"+ DB_NAME;

	public String TABLE_NOTES = "notes";
	public PreparedStatement insert_record; 
	public Connection conn;
	public PreparedStatement query_by_name;
	public String INSERTION_INTO_NOTES = "INSERT INTO "+ TABLE_NOTES+"("+COLUMN_NOTE_NAME+", "+COLUMN_NOTE_DESC+ ") VALUES(?,?)";
	public String QUERY_NOTES_NAME="SELECT "+COLUMN_NOTE_ID+" FROM "+ TABLE_NOTES+" WHERE "+COLUMN_NOTE_NAME +" = ?";
	public String insertioners= "INSERT INTO "+ TABLE_NOTES+"("+COLUMN_NOTE_NAME+", "+COLUMN_NOTE_DESC+ ") VALUES(?,?)";
	public String Query_All_Records = "SELECT * FROM "+TABLE_NOTES;
	
	public String QUERY_ONE_RECORD= "SELECT "+COLUMN_NOTE_ID+", "+COLUMN_NOTE_NAME+", "+ COLUMN_NOTE_DESC+" FROM "+ TABLE_NOTES+" WHERE "+COLUMN_NOTE_NAME +" = ?";
	
	public String UPDATE_DESC = "UPDATE "+TABLE_NOTES+" SET "+COLUMN_NOTE_DESC +" = ? "+"WHERE "+COLUMN_NOTE_NAME+" = ?";
	
	public PreparedStatement view_all_records;
	public PreparedStatement query_single_record;
	public PreparedStatement count_records;
	public PreparedStatement update_record;
	
	public String COUNT_ALL_RECORDS ="SELECT COUNT(*) FROM "+ TABLE_NOTES;
	public boolean open() 
	{
		try {
		
			Connection conn = DriverManager.getConnection(Connection_String);
			query_by_name = conn.prepareStatement(QUERY_NOTES_NAME);
			insert_record = conn.prepareStatement(insertioners);
			count_records = conn.prepareStatement(COUNT_ALL_RECORDS);
			view_all_records = conn.prepareStatement(Query_All_Records);
			query_single_record = conn.prepareStatement(QUERY_ONE_RECORD);//, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			update_record = conn.prepareStatement(UPDATE_DESC);
			System.out.println("Connection established");
			return true;
		    }
		catch(SQLException e)
		{
			
			System.out.println("Could not connect to the database: "+ e.getMessage());
			System.out.println("Connection could be opened"+ e.getMessage());
			return false;
			
			
		}

		
	}
	
	public void close()
	{
		
		try {
			if(query_by_name!=null)
			  {
				  query_by_name.close();
				  
			  }
			  
			  if(insert_record!=null)
			  {
				  insert_record.close();
				  
			  }
			 if(count_records!=null)
			 {
				 count_records.close();
				 
				 
			 }
			 if(view_all_records!=null)
			 {
				 view_all_records.close();
				 
			 }
			 if(query_single_record !=null)
			 {
				 query_single_record.close();
				 
			 }
			  
			
		    if(conn!=null)
			  {
				  
				  conn.close();
				  
				  
				  
			  }
			  
		}
		
		catch(SQLException e) 
		{
			System.out.println("Could not close the connection: "+e.getMessage());
		}
		
		
		
		
	}

	
	public void CreateTableNotes()
	{
		String CreateNotesTable = "CREATE TABLE IF NOT EXISTS "+ TABLE_NOTES+
	            "("+COLUMN_NOTE_ID+" INTEGER PRIMARY KEY,"+COLUMN_NOTE_NAME+" TEXT,"+COLUMN_NOTE_DESC+" TEXT)";
	
		System.out.println("In Create Table Notes function");
		//" INTEGER PRIMARY KEY,"
		
		 try (Connection conn = DriverManager.getConnection(Connection_String);
          Statement stmt = conn.createStatement()) 
		  {
            // create a new table
			// stmt.execute("DROP TABLE CustomerDetails");
             stmt.execute(CreateNotesTable);
           }
	     catch (SQLException e) 
		    {
             System.out.println(e.getMessage() +"Pinging from this method");
            }
		
	}
	
	
	
	public int  InsertIntoTableNotes(String note_name,String desc) throws Exception
	{
	
		
		 //public String QUERY_NOTES_NAME="SELECT "+COLUMN_NOTE_ID+" FROM "+ TABLE_NOTES+" WHERE "+COLUMN_NOTE_NAME +" = ?";
  
		    query_by_name.setString(1, note_name);
		    ResultSet results = query_by_name.executeQuery();			 
		            
		     if(results.next())
		     {
		    	 System.out.println("Sorry!, The record is already present !!Insertion is not allowed");  
		    	 return -1;
		     }
		     
		     else 
		     {
		 // public String INSERTION_INTO_NOTES = "INSERT INTO "+ TABLE_NOTES+"("+COLUMN_NOTE_NAME+", "+COLUMN_NOTE_DESC+ ") VALUES(?,?)";
		    	
		    	 
		    	 insert_record.setString(1,note_name);
		    	 insert_record.setString(2, desc);
		    	 
		    	 
		    	 
		    	 int affectedRows = insert_record.executeUpdate();
		    	 
		    	 if(affectedRows!=1)
				   {
					   
					   
					   throw new SQLException("Couldn't insert the record Error occurred!");
				   }
		    	 
		    	 else if(affectedRows==1)
		    	 {
		    		 System.out.println("New Record Successfully Inserted ");
		    	 }
		    	
		     }
		     results.close();
		     return 1;
		
	
	}
	public int UpdateTableNotes(String name, String desc) throws Exception
	{
		//public String UPDATE_DESC = "UPDATE "+TABLE_NOTES+" SET "+COLUMN_NOTE_DESC +" = ? "+"WHERE "+COLUMN_NOTE_NAME+" = ?";
		int affect =-1;
		try {
		update_record.setString(1, desc);
		update_record.setString(2, name);
		
		 int affectedRows = update_record.executeUpdate();
    	 
    	 if(affectedRows!=1)
		   {
			   
			   
			   throw new SQLException("Couldn't update the record Error occurred!");
		   }
    	 
    	 else if(affectedRows==1)
    	 {
    		 System.out.println(" Record Updated Successfully");
    		 affect=1;
    	 }
    	 
		}
		catch(Exception e)
		{
			
			 System.out.println(e.getMessage());
  		   e.printStackTrace();
		}
		
		return affect;
		
		
	}

	
	
	public ResultSet DisplayAllRecords() throws Exception
		{

		
				//ResultSet rs2;
				int count = count_records.executeQuery().getInt(1);
				
				if(count>0)
			      {
					ResultSet rs1  = view_all_records.executeQuery();
			        ResultSet rs2 = rs1;
					/*while(rs1.next())
					{
						
						System.out.println("Note ID =  "+rs1.getInt(1)+ "Note Name =  "+rs1.getString(2)+" Note Description ="+rs1.getInt(3)+"\n");
				
					}*/
			        
			       
					return rs1;
					
			     }
				else
					{
					  System.out.println("The Table is empty");
			           return null;
					}
			     // return rs2;
		
				
			}
	
	
	public ResultSet displayARecord(String title) throws Exception
		{
		
		 //public String QUERY_CustomerDetails= "SELECT "+COLUMN_CUSTOMER_ID+", "+COLUMN_CUSTOMER_NAME+", "+ COLUMN_CUSTOMER_BALANCE+", "+COLUMN_PREVIOUS_TRANSACTION+" FROM "+ TABLE_CUSTOMERDETAILS+" WHERE "+COLUMN_CUSTOMER_NAME +" = ?";
		query_single_record.setString(1,title);  
	   	
	   	ResultSet results = query_single_record.executeQuery();
	  
	   		    if(results.next())
	   			  {
	   			 System.out.println("Note ID =  "+results.getInt(1)+ " Note Name =  "+results.getString(2)+" Note Description ="+results.getString(3)+"\n");
	   			     
	   			     
	   			 
	   			     return results;
	   		      }
	   			
	   		else
	   			{
	   				System.out.println("The record by the name "+ title+" does not exist" );
	   				
	   				return null;
	   				
	   			}
	   	  
	   	
			
			
			
			
			
			
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
			
		}
	
	
	
	
	
	

