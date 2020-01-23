package com.excilys.cbd.dao;
import java.sql.*;
import java.util.ArrayList;

import com.excilys.cbd.model.Company;

public class CompanyDAO 
{
	public static String TROUVERCOMPAID = "SELECT * FROM company WHERE id = ?";
	public static String TROUVERCOMPANOM = "SELECT * FROM company WHERE name = ?";
	public static String TOUTCOMPA = "SELECT * FROM company";
	public static ArrayList<String> toutCompany()
	{
		Connection preparation =ComputerDAO.conneBaseH2();
		ArrayList<String> listCompany= new ArrayList<String>();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(TOUTCOMPA) ;
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				System.out.println(name);
				listCompany.add(name);
			}
			ComputerDAO.fermerConnection();
			return listCompany;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ComputerDAO.fermerConnection();
		return null;
	}
	public static Company trouverCompany(Long id)
	{
		Connection preparation =ComputerDAO.conneBaseH2();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(TROUVERCOMPAID) ;
			prepare.setLong(1, id);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				Long company_id = resultat.getLong("id");
				String company_name = resultat.getString("name");

				Company compa = new Company(company_id,company_name);


				System.out.println(compa.getCompany_id() + " " + compa.getName() + " ");
				return compa;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public static Company trouverCompany(String name)

	{
		Connection preparation =ComputerDAO.conneBaseH2();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(TROUVERCOMPANOM) ;
			prepare.setString(1, name);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				Long company_id = resultat.getLong("id");
				String company_name = resultat.getString("name");

				Company compa = new Company(company_id,company_name);


				System.out.println(compa.getCompany_id() + " " + compa.getName() + " ");
				ComputerDAO.fermerConnection();
				return compa;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ComputerDAO.fermerConnection();
		return null;
	}
}