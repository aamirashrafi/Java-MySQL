import	java.sql.*;
public class GetEmployeesForDepartment {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection			myConn	=	null;
		CallableStatement	myStmt	=	null;
		ResultSet			myRs	=	null;
		String				dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String				user	=	"student";
		String				pass	=	"student";
		try {
			//Get Connection
			myConn	=	DriverManager.getConnection(dburl,user, pass);
			String	theDep	=	"Engineering";
			
			myStmt	=	myConn.prepareCall("{call get_employees_for_department(?)}");
			
			myStmt.setString(1, theDep);
			
			System.out.println("Start calling stored procedure : get_employees_for_department('"+theDep+"')\n");
			myStmt.execute();
			System.out.println("Finished calling stored procedure !");
			
			myRs	=	myStmt.getResultSet();
			
			//Getting output
			display(myRs);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void display(ResultSet Rs) throws SQLException{
		// TODO Auto-generated method stub
		while(Rs.next()) {
			int			ID			=	Rs.getInt("id");
			String		Last_Name	=	Rs.getString("last_name");
			String		First_Name	=	Rs.getString("first_name");
			String		Email		=	Rs.getString("email");
			String		Depart		=	Rs.getString("department");
			String		Salary		=	Rs.getString("salary");
			System.out.format("%d , %s , %s , %s , %s , %s \n", ID,Last_Name,First_Name,Email,Depart,Salary);
			
		}

	}

}
