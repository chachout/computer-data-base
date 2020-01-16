package com.excilys.cbd.dao;
import java.sql.*;
import java.util.ArrayList;

import com.excilys.cbd.bdd.Company;

public class CompanyDAO 
{
	public static String TROUVERCOMPAID = "SELECT * FROM company WHERE id = ?";
	public static String TROUVERCOMPANOM = "SELECT * FROM company WHERE name = ?";
	public static String TOUTCOMPA = "SELECT * FROM company";
	public static ArrayList<String> toutCompany()
	{
		Connexion connexion = new Connexion();
		Connection preparation=connexion.connexionOpen();
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
			connexion.connexionClose();
			return listCompany;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		connexion.connexionClose();
		return null;
	}
	public static Company trouverCompany(Long id)
	{
		Connexion connexion = new Connexion();
		Connection preparation= connexion.connexionOpen();
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

		Connexion connexion = new Connexion();
		Connection preparation=connexion.connexionOpen();
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
				connexion.connexionClose();
				return compa;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		connexion.connexionClose();
		return null;
	}
}