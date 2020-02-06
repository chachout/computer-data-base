package com.excilys.cbd.dao;



import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.cbd.Logger.*;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnecHikari 
{

	private static Connection connect;
	private static ConnecHikari instance;

	private static HikariConfig hikariConfig; 
	private static HikariDataSource dataSource;
	static 
	{
  	hikariConfig=new HikariConfig("/datasource.properties");
  	dataSource= new HikariDataSource(hikariConfig);
  	}

	private ConnecHikari() { }

	public Connection getConnection() {
		try {
			connect = dataSource.getConnection();
		} catch (SQLException sqlException) {
			for (Throwable e : sqlException) {
				//Logging.afficherMessageError("Problèmes rencontrés: " + e);
			}

		}
		return connect;
	}

	public static ConnecHikari getInstance() {
		if (instance == null) {
			instance = new ConnecHikari();
		}
		return instance;
	}

	public Connection disconnect() {
		if (connect != null) {
			try {
				connect.close();
			} catch (SQLException sqlException) {
				for (Throwable e : sqlException) {
					//Logging.afficherMessageError("Problèmes rencontrés: " + e);
				}
			}
		}
		return connect;
	}
}