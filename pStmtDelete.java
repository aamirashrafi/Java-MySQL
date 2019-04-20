import java.sql.*;
public class pStmtDelete {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection			myConn	=	null;
		PreparedStatement 	myStmt	=	null;
		ResultSet			myRs	=	null;
		String 				dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String				user	=	"student";
		String				pass	=	"student";
		try {
			//1. Get Connection
			myConn	=	DriverManager.getConnection(dburl,user,pass);
			//2. PreparedStatement
			myStmt	=	myConn.prepareStatement("delete from employees "
					+ "where salary >? and department=?");
			//3. Set the parameters
			myStmt.setDouble(1, 80000);
			myStmt.setString(2, "HR");
			//
			System.out.println("Display record before delete");
			displayRecord(myConn);
			
			//4. Execute Query
			int ro	=	myStmt.executeUpdate();
			System.out.println("Deleted "+ro+" rows");
			
			//5. Display all data
			System.out.println("Display record after delete");
			displayRecord(myConn);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error1");
			e.printStackTrace();
		}
	}
	public static void displayRecord(Connection conn) {
		Statement	stmt	=	null;
		ResultSet	Rs		=	null;
		try {
			stmt	=	conn.createStatement();
			Rs		=	stmt.executeQuery("Select * from employees");
			while(Rs.next()) {
				int		ID			=	Rs.getInt("id");
				String	Last_Name	=	Rs.getString("last_name");
				String	First_Name	=	Rs.getString("first_name");
				String	Email		=	Rs.getString("email");
				String	Department	=	Rs.getString("Department");
				String	Salary		=	Rs.getString("salary");
				
				System.out.format("%d\t%s\t%s\t%s\t%s\t%s\n",ID,Last_Name,First_Name,Email,Department,Salary);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
