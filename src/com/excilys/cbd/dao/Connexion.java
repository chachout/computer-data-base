package com.excilys.cbd.dao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
public class Connexion 
{
	static String url = "jdbc:mysql://localhost:3306/computer-database-db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	static String user = "root";
	static String password = "qwerty1234";
	static Connection connexion;
	private static Connection connexionOpen()
	{
		try
		{
			connexion = DriverManager.getConnection(url,user,password);
			System.out.println("La connexion est établi");
			return connexion;
		}
		catch (SQLException e)
		{
			System.out.print("La connexion à échouer");
			e.printStackTrace();
		}
		return null;
	}
	public Connection connec()
	{
		Connection preparation = Connexion.connexionOpen();
		return preparation;
	}
	public void connexionClose()
	{
		if ( connexion != null )
		{
			try 
			{
				connexion.close();
				System.out.print("La connexion est fermée");
			}
			catch ( SQLException ignore ) 
			{ 
			}
		}
		else
		{
			System.out.print("La connexion n'est pas ouverte");
		}
	}
}