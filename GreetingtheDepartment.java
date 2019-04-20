import java.sql.*;
public class GreetingtheDepartment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection			myConn	=	null;
		CallableStatement	myStmt	=	null;
		String 				dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String 				user	=	"student";
		String				pass	=	"student";
		try {
			//1. Get Connection
			myConn	=	DriverManager.getConnection(dburl,user,pass);
			String	dep	=	"Engineering";
			myStmt	=	myConn.prepareCall("{call greet_the_department(?)}");
			
			
			myStmt.registerOutParameter(1,Types.VARCHAR);
			myStmt.setString(1, dep);
			
			//Call stored procedure
			System.out.println("Calling stored procedure great_the_departmen('"+dep+"')");
			myStmt.execute();
			System.out.println("Finish calling stored procedure !");
			
			//Getting values of result
			String res	=	myStmt.getString(1);
			
			System.out.println("The result is :"+res);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
