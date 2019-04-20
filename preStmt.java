import java.sql.*;
public class preStmt {
public static void main(String[] args) {
	Connection	myConn	=	null;
	PreparedStatement	myStmt	=	null;
	ResultSet	myRs	=	null;
	String	dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
	String	user	=	"student";
	String	pass	=	"student";
	
	try {
		
		//1.   Get connection
		myConn=DriverManager.getConnection(dburl,user,pass);
		
		//2.   PreparedStatement
		myStmt	=	myConn.prepareStatement("select * from employees where salary > ? and department=?");
		
		//3.   set the parameters
		myStmt.setDouble(1, 80000);
		myStmt.setString(2, "Legal");
		
		//4. Execute Query
		myRs	=	myStmt.executeQuery();
		
		//5. Display result
		display(myRs);
		
		
		//Reuse
		System.out.println("Reuse prepared statement for department : HR and Salary>25000");
		//6.   set the parameters
		myStmt.setDouble(1, 25000);
		myStmt.setString(2, "HR");
		
		//7. Execute Query
		myRs	=	myStmt.executeQuery();
		
		//8. Display result
		display(myRs);
		
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	finally {
		if(myRs != null) {
			//myRs.close();
			}
	}
}

	public static void display(ResultSet Rs) {
		try {
			while(Rs.next()) {
				int		ID			=	Rs.getInt("id");
				String	Last_Name	=	Rs.getString("last_name");
				String	First_Name	=	Rs.getString("first_name");
				String	Email		=	Rs.getString("email");
				String	Department	=	Rs.getString("department");
				float	Salary		=	Rs.getFloat("salary");
				System.out.format("\n%d,%s,%s,%s.%s,%.2f", ID,Last_Name,First_Name,Email,Department,Salary);
				
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}
}
