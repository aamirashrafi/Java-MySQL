import	java.sql.*;
public class IncreaseSalariesForDepartment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection			myConn	=	null;
		CallableStatement	myStmt	=	null;
		String				dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String				user	=	"student";
		String				pass	=	"student";
		try {
			//1.	Get Connection
			myConn	=	DriverManager.getConnection(dburl,user,pass);
			String theDepartment	=	"Engineering";
			int theIncreaseAmount	=	10000;
			
			
			//Show salaries before
			System.out.println("Salaries Before");
			showSalaries(myConn,theDepartment);
			
			// Prepare the stored procedure call
			myStmt=myConn.prepareCall("{call increase_salaries_for_department(?,?)}");
			
			//set the parameters
			
			myStmt.setString(1, theDepartment);
			myStmt.setDouble(2, theIncreaseAmount);
			
			
			//Calling stores procedure
			System.out.println("Calling the stores procedure for "+theDepartment);
			myStmt.executeQuery();
			System.out.println("Finished calling stores procedure");
			
			
			//Show salaries after
			System.out.println("Salaries after");
			showSalaries(myConn,theDepartment);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void showSalaries(Connection conn, String Dept) {
		Statement	stmt	=	null;
		ResultSet	Rs		=	null;
		try {
			stmt	=	conn.createStatement();
			Rs		=	stmt.executeQuery("select * from employees");	
			while(Rs.next()) {
				int	ID				=	Rs.getInt("id");
				String	Last_Name	=	Rs.getString("last_name");
				String	First_Name	=	Rs.getString("first_name");
				String	Department	=	Rs.getString("department");
				String	Salary		=	Rs.getString("salary");
				System.out.format("\n%d , %s, %s, %s, %s\n", ID, Last_Name, First_Name, Department, Salary);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
