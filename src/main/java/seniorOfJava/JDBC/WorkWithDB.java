package seniorOfJava.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.logging.Logger;

public class WorkWithDB{

	public static final Logger logger = Logger.getLogger("myLogger");
	static final String userName = "root";
	static final String password = "11111";
	static final String url = "jdbc:mysql://localhost/test";
	static final String driverName = "com.mysql.jdbc.Driver";
	
	public static void main(String[] args) throws SQLException{
		Scanner scanner = new Scanner(System.in);
		logger.info("go");
		
		Connection connection = connectToDatabase(url, userName, password);
		Statement statement = getStatement(connection);
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//createDB(statement);
		//showTablesFromDB(statement);
		
		
		boolean go = true;
		while(go){		
			
		System.out.println("What do you want to do?");
		System.out.println("select and press number");
		System.out.println("1 - Create user");
		System.out.println("2 - Show user");
		System.out.println("3 - Update user");
		System.out.println("4 - Delete user");
		System.out.println("any other - Exit");
		
		
		switch(scanner.nextByte()){
		case 1: addUserToTable(statement);
			break;
		case 2: showUser(statement);
			break;
		case 3: updateUser(statement);
			break;
		case 4: deleteUser(statement);
			break;
		default: go = false;
		}
		}		
		if(!statement.isClosed()){ 
			statement.close();
			logger.info("statement close");
			}
		if(!connection.isClosed()){ 
			connection.close();
			logger.info("connection close");
			}
		logger.info("end");
		}
	
	private static void deleteUser(Statement statement) {
		
		
	}

	private static void updateUser(Statement statement) {
		
		
	}

	private static void showUser(Statement statement) {
		try {
			statement.execute("USE site");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		ResultSet rs = null;
		try {
			 rs = statement.executeQuery("SELECT * FROM users");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while(rs.next()){
				System.out.println(rs.getString("FirstName"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		
		
	}

	private static void addUserToTable(Statement statement){
		User user = createUser();
		try {
			statement.execute("USE site");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	try {
		statement.executeUpdate("INSERT INTO users SET" +
			" FirstName = '" + user.getFirstName() +
			"', Login = '"     + user.getLogin() +
			"', Email = '"     + user.getEmail() +
			"', Password = '"  + user.getPassword() +
			"', PhoneNumber = '" + user.getPhoneNumber() +
			"', Adress = '" + user.getAdress() +
			"', BirthDay = " + user.getBirthDay() +
			", BirthMonth = " + user.getBirthMonth() +
			", BirthYear = " + user.getBirthYear() );
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	logger.info("user  \"" + user.getFirstName() + "\" added to table");
	
}
	
    private static void showTablesFromDB(Statement stmt){
		logger.info("before show tables");
	    try {
			stmt.execute("USE SITE");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			stmt.executeUpdate("SHOW TABLES Users");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(stmt.toString());
	    logger.info("after show tables");
		}
	
	private static Connection connectToDatabase(String urll, String username, String password){
		logger.info("before connection returning");
		Connection con = null;
		try {
			con = DriverManager.getConnection(
			                     urll,
			                     username,
			                     password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    logger.info("connection returning");
        return con;
	    }
	
	private static Statement getStatement(Connection con){
	    logger.info("before stetement returning");
	    Statement stmt = null;
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	    logger.info("statement returning");

		return stmt;
	    
	}
	
    private static void createDB(Statement stmt){
	    logger.info("before using database");
	    try {
			stmt.execute("USE SITE");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
	    try {
			stmt.executeUpdate("Create TABLE Users ("
			        + "FirstName varchar(30) NOT NULL,"
					+ "Login varchar(30) NOT NULL,"
			        + "Email varchar(30) NOT NULL,"
			        + "Password varchar(30) NOT NULL,"
			        + "PhoneNumber varchar(30) NOT NULL,"
			        + "Adress varchar(30) NOT NULL,"
			        + "BirthDay int NOT NULL,"
			        + "BirthMonth int NOT NULL,"
			        + "BirthYear int NOT NULL"
			        + /*"UserID int NOT NULL,"
			        + "PRIMARY KEY (UserID) */    ")");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	    logger.info("after using database");

	   	}

    private static User createUser(){
	Scanner sc = new Scanner(System.in);
	User user = new User();
	
	System.out.println("enter user's name");
	user.setFirstName(sc.nextLine());
	
	System.out.println("enter user's login");
	user.setLogin(sc.nextLine());
	
	System.out.println("enter user's email");
	user.setEmail(sc.nextLine());
	
	System.out.println("enter user's password");
	user.setEmail(sc.nextLine());
	
	System.out.println("enter user's phoneNumber");
	user.setPhoneNumber(sc.nextLine());
	
	System.out.println("enter user's adress");
	user.setAdress(sc.nextLine());
	
	System.out.println("enter user's birthDay");
	user.setBirthDay(sc.nextInt());
	
	System.out.println("enter user's birthMonth");
	user.setBirthDay(sc.nextInt());
	
	System.out.println("enter user's birthYear");
	user.setBirthDay(sc.nextInt());
	
	System.out.println("");
	System.out.println("-----------------------------------------------------");
	System.out.println("User created!----------------------------------------");
	System.out.println("-----------------------------------------------------");
	
	return user;
}

}
