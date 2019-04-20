import java.io.File;
import java.io.FileInputStream;
import	java.sql.*;
public class WriteBlobDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection			myConn	=	null;
		PreparedStatement	myStmt	=	null;
		String				dburl	=	"jdbc:mysql://localhost:3306/demo?useSSL=false";
		String				user	=	"student";
		String				pass	=	"student";
		FileInputStream 	input	=	null;
		try {
			// 1.	Get Connection
			myConn	=	DriverManager.getConnection(dburl,user,pass);
			
			//2.	Prepare Statement
			String sql	=	"update employees set resume=? where email='john.doe@foo.com'";
			myStmt		=myConn.prepareStatement(sql);
			
			//3.	Set parameter for resume file name
			File theFile=new File("C:/Users/maami/jdbc-test/src/Ali_M_Aamir_CV.pdf");
			input	= new FileInputStream(theFile);
			myStmt.setBinaryStream(1, input);
			
			System.out.println("Reading file from : "+theFile.getAbsolutePath());
			
			//4.	Execute Statement
			System.out.println("Storing esume in database : "+theFile);
			System.out.println(sql);
			myStmt.executeUpdate();
			System.out.println("Successfully Completed !");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
