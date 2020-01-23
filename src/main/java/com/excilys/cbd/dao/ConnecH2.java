package com.excilys.cbd.dao;
import java.sql.*;
public class ConnecH2 
{
	String urlh2 ="jdbc:h2:mem:computer-database-db;INIT=RUNSCRIPT FROM 'E:/Excilys/Projet début stage/schema.sql';DB_CLOSE_DELAY=-1";
	String userh2 = "pi";
	String passwordh2 = "";
	String driverh2 = "org.h2.Driver";
	String url = "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String user = "root";
	String password = "qwerty1234";
	String driver = "com.mysql.jdbc.Driver";
	private static ConnecH2 instance;
	private static Connection connexion;
	
	
	
	private ConnecH2() throws ClassNotFoundException
	{
		try
		{
			if(isBlank(System.getProperty("test"))) {
				Class.forName(driverh2);
				connexion= DriverManager.getConnection(urlh2,userh2,passwordh2);
			}
			else
			{
				Class.forName(driver);
				connexion= DriverManager.getConnection(url,user,password);
			}
			
		}
		catch (SQLException e)
		{
			System.out.print("La connexion à échouer");
			e.printStackTrace();
		}
	}
	public static void connexionClose()
	{
		if ( connexion != null )
		{
			try 
			{
				connexion.close();
			}
			catch ( SQLException ignore ) 
			{ 
			}
		}
	}
	private static ConnecH2 getConnec() throws ClassNotFoundException
	{
		if (instance ==null)
		{
			instance=new ConnecH2();
			return instance;
		}
		else 
		{
			return instance;
		}
	}
	public static Connection getConnexion() throws ClassNotFoundException
	{
		instance=getConnec();
		return connexion;
	}
	
	public static boolean isBlank(String str) {
		return str == null ? false : str.equals("true") ? true: false ;
	}
}