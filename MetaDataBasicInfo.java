import java.sql.*;
public class MetaDataBasicInfo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection	myConn	=	null;
		String		dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String 		user	=	"student";
		String		pass	=	"student";
		try {
			myConn	=	DriverManager.getConnection(dburl,user,pass);
			DatabaseMetaData databaseMetaData	=	myConn.getMetaData();
			//display about database
			System.out.println("Database Name : "+databaseMetaData.getDatabaseProductName());
			System.out.println("Product Version : "+databaseMetaData.getDatabaseProductVersion());
			System.out.println("\n");
			System.out.println("Driver Name : "+databaseMetaData.getDriverName());
			System.out.println("Driver Version : "+databaseMetaData.getDriverVersion());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			myConn.close();
		}
		
}

}
