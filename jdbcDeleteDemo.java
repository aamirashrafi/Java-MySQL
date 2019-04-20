import java.sql.*;

public class jdbcDeleteDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection	myConn	=	null;
		Statement	myStmt	=	null;
		ResultSet	myRs	=	null;
		String	jdburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String	user	=	"student";
		String	pass	=	"student";
		try {
			//1.	Get Connection
			myConn	=	DriverManager.getConnection(jdburl,user,pass);
			System.out.println("Connection Successful !");
			
			//2. Create Statement
			myStmt	=	myConn.createStatement();
			
			
			//Before Delete
			System.out.println("Before Delete...");
			displayEmployee(myConn,"John", "Doe");
			
			
			//3. Execute SQL delete query
			int	rowsAffected	=	myStmt.executeUpdate(
					"Delete from employees " + 
							"where last_name ='Doe' and first_name='John'");
			
			//After Delete
			System.out.println("After Delete...");
			displayEmployee(myConn,"John", "Doe");
			System.out.println("Total deleted row(s) : "+rowsAffected);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//close(myConn,myStmt,myRs);
			
		}
		
	}
	
	public static void displayEmployee(Connection conn, String first_name, String last_name) {
		Statement	selStmt	=	null;
		ResultSet	selRs	=	null;
		try {
			selStmt	=	conn.createStatement();
			selRs	=	selStmt.executeQuery(
					"Select * from employees " +
					"where last_name =" + " '" + last_name + "' " +
					"and first_name =" + " '" + first_name + "' "
					);
			
			while(selRs.next()) {
				int		ID			=	selRs.getInt("id");
				String	Last_Name	=	selRs.getString("last_name");
				String	First_Name	=	selRs.getString("first_name");
				String	Email		=	selRs.getString("email");
				String	Department	=	selRs.getString("Department");
				String	Salary		=	selRs.getString("salary");
				
				System.out.format("%d\t%s\t%s\t%s\t%s\t%s\n",ID,Last_Name,First_Name,Email,Department,Salary);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}


}
