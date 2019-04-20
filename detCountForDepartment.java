import java.sql.*;
public class detCountForDepartment {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection			myConn	=	null;
		CallableStatement	myStmt	=	null;
		String				dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String 				user	=	"student";
		String				pass	=	"student";
		String				theDep	=	"Engineering";
		try {
			//1. Get Connection
			myConn	=	DriverManager.getConnection(dburl,user,pass);
			
			myStmt	=	myConn.prepareCall("{call get_count_for_department(?,?)}");
			myStmt.setString(1, theDep);
			myStmt.registerOutParameter(2, Types.INTEGER);
			
			
			//Calling stored procedure
			System.out.println("Calling stored procedure get_count_for_department('"+theDep+"',?)");
			myStmt.execute();
			System.out.println("Finished calling stored procedure !");
			
			int totemp	=	myStmt.getInt(2);
			System.out.println("Number of employee in "+theDep+" are :"+totemp);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace(); 
		}
	}

}
