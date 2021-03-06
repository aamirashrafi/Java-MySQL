import java.sql.*;
public class jdbcTest {

	public static void main(String[] args) throws SQLException{
		Connection myConn	=	null;
		Statement myStmt	=	null;
		ResultSet myRs 		= 	null;
		try {
			//1.  Get a connection to database
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","student","student");
			System.out.println("Database connection successful!\n");
			
			//2. Create a Statement object
			myStmt=myConn.createStatement();
			
			
			//3. Execute SQL query
			myRs=myStmt.executeQuery("select * from employees");
			
			//4.  Process the result set
			while(myRs.next()) {
				System.out.println(myRs.getString("last_name")+", "+myRs.getString("first_name"));
			}
			
		} catch (Exception exe) {
			// TODO: handle exception
			exe.printStackTrace();
		}
	}
}
