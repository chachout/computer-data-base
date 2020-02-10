package com.excilys.cbd.dao;



import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.excilys.cbd.Logger.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Component
public class ConnecHikari 
{
	public static Connection connect;
	public static HikariConfig hikariConfig; 
	public static HikariDataSource dataSource;
	static 
	{
  	hikariConfig=new HikariConfig("/datasource.properties");
  	dataSource= new HikariDataSource(hikariConfig);
  	}

	public ConnecHikari() { }

	public Connection getConnection() 
	{
		try 
		{
			connect = dataSource.getConnection();
		} 
		catch (SQLException sqlException) 
		{
			for (Throwable e : sqlException) 
			{
				Logging.afficher("Problèmes rencontrés: " + e);
			}

		}
		return connect;
	}

	public Connection disconnect() 
	{
		if (connect != null) 
		{
			try 
			{
				connect.close();
			} catch (SQLException sqlException) 
			{
				for (Throwable e : sqlException) 
				{
				Logging.afficher("Problèmes rencontrés: " + e);
				}
			}
		}
		return connect;
	}
}