import java.sql.*;


public class jdbcInsertDemo {
public static void main(String[] args) throws SQLException {
	Connection	myConn	=	null;
	Statement	myStmt	=	null;
	ResultSet	myRs	=	null;
	String	dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
	String	user	=	"student";
	String	pass	=	"student";
	try {
		
		//1. Get Connection
		myConn			=	DriverManager.getConnection(dburl, user, pass);
		System.out.println("Connection Successful ! ");
		
		//2. Create Statement
		myStmt=myConn.createStatement();
		
		//3. insert a new employee
		int	rowsAffected	=	myStmt.executeUpdate("insert into employees" + "(last_name, first_name, email, department, salary)" +"values " + "('Wright','Eric','eric.wright@foo,com','HR Department',33000.00)");
		
		//4.  Now verify this by selecting *
		myRs=myStmt.executeQuery("Select * from employees order by last_name");
		
		
		//5.  Process the result
		while(myRs.next()) {
		System.out.println(myRs.getString("last_name") + " , " +myRs.getString("first_name"));
		
		
		}
	} catch (Exception exc) {
		// TODO: handle exception
		exc.printStackTrace();
	}
	finally {
		if(myRs != null) {
				myRs.close();
				}
	}
}
}
