import	java.sql.*;
import java.util.Scanner;
public class TransactionDemo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection			myConn	=	null;
		Statement			myStmt	=	null;
		String				dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String				user	=	"student";
		String				pass	=	"student";
		try {
			//1. Get Connection
			myConn	=	DriverManager.getConnection(dburl,user,pass);
			//Turn off auto commit
			myConn.setAutoCommit(false);
			// 2 Show salaries before
			System.out.println("Salaries before");
			showSalaries(myConn,"HR");
			showSalaries(myConn,"Engineering");
			
			// Transaction  Step 1 delete all HR employees
			myStmt	=	myConn.createStatement();
			myStmt.executeUpdate("delete from employees where department='HR'");
			
			//Transaction step 2 set salaries to 300000 to all engineering
			myStmt.executeUpdate("update employees "+
									"set salary=300000 "+
									"where department='Engineering'");
			
			System.out.println("Now ask user to confirm");
			
			boolean	ok	=	askUserToSave();
			if(ok) {
				myConn.commit();
				System.out.println("Transaction Successful !");
			}
			else {
				myConn.rollback();
				System.out.println("Transaction aborted !");
			}
			//Show salaries after
			System.out.println("Salaries after\n");
			showSalaries(myConn, "HR");
			showSalaries(myConn, "Engineering");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void showSalaries(Connection con,	String Dep) throws SQLException {
		Statement	stmt	=	null;
		ResultSet	Rs		=	null;
		stmt = con.createStatement();
		Rs = stmt.executeQuery("select * from employees where department='"+Dep+"'");
		while(Rs.next()) {
			int		ID			=	Rs.getInt("id");
			String	Last_Name	=	Rs.getString("last_name");
			String	First_Name	=	Rs.getString("first_name");
			String	Salary		=	Rs.getString("salary");
			String	Dept		=	Rs.getString("department");
			System.out.format("%d , %s , %s , %s , %s\n", ID, Last_Name, First_Name, Salary, Dept);
		}
		
		
	}
	public static boolean askUserToSave() {
		boolean ret=false;
		System.out.println("Do you want to save changes(y/n) ?");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		char res	= input.next().toLowerCase().charAt(0);
		if(res=='y' || res=='n') {
			if(res=='y')
				ret	= true;
			else
				ret	= false;
		}
		else {
			
			System.out.println("please enter y/n only");}
		return ret;
	}

}
