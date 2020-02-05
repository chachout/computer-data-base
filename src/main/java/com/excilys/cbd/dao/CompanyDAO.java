package com.excilys.cbd.dao;
import java.sql.*;
import java.util.ArrayList;

import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;

public class CompanyDAO 
{
	public static String TROUVERCOMPAID = "SELECT * FROM company WHERE id = ?";
	public static String TROUVERCOMPANOM = "SELECT * FROM company WHERE name = ?";
	public static String TOUTCOMPA = "SELECT * FROM company";
	public static String EFFACER = "DELETE FROM company WHERE id = ?";
	private static CompanyDAO instance;
	private CompanyDAO()
	{
	}
	public static CompanyDAO getInstance()
	{
		if (instance == null)
		{
			instance= new CompanyDAO();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	private static Connection connexionOpen() throws ClassNotFoundException
	{
		Connection preparation = ConnecH2.getConnec().seConnecter();
		return preparation;
	}
	private static void connexionClose(Connection preparation) throws ClassNotFoundException
	{
		ConnecH2.getConnec().connectionClose(preparation);
	}
	public ArrayList<Company> toutCompany() throws ClassNotFoundException
	{
		ArrayList<Company> listCompany= new ArrayList<Company>();
		try
		{
			Connection preparation = CompanyDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement(TOUTCOMPA) ;
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				long id=resultat.getLong("id");
				//System.out.println(name + id);
				Company compa = new Company.CompanyBuilder().setId(id).setName(name).build();
				listCompany.add(compa);
				//System.out.println(compa.getName()+" "+compa.getId());
			}
			CompanyDAO.connexionClose(preparation);
			return listCompany;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public Company trouverCompany(Long id) throws ClassNotFoundException
	{
		try
		{
			Connection preparation = CompanyDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement(TROUVERCOMPAID) ;
			prepare.setLong(1, id);
			ResultSet resultat=prepare.executeQuery();
			Company compa=null;
			if(resultat.next())
			{
				long idCompany = resultat.getLong("id");
				String name = resultat.getString("name");
				compa = new Company.CompanyBuilder().setId(idCompany).setName(name).build();
				//System.out.println(compa.getId() + " " + compa.getName() + " ");
			}
			CompanyDAO.connexionClose(preparation);
			return compa;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public Company trouverCompany(String name) throws ClassNotFoundException

	{
		
		try
		{
			Connection preparation = CompanyDAO.connexionOpen();
			PreparedStatement prepare = preparation.prepareStatement(TROUVERCOMPANOM) ;
			prepare.setString(1, name);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				long id = resultat.getLong("id");
				String nameCompany = resultat.getString("name");

				Company compa = new Company.CompanyBuilder().setId(id).setName(nameCompany).build();


				//System.out.println(compa.getId() + " " + compa.getName() + " ");
				CompanyDAO.connexionClose(preparation);
				return compa;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public int effacer(long companyId) throws ClassNotFoundException, SQLException
	{
		int value = 0;
		//preparation.setAutoCommit(false);
		try 
		{
			System.out.println("1");
			Connection preparation = CompanyDAO.connexionOpen();
			System.out.println("2"); 
			PreparedStatement prepare = preparation.prepareStatement(ComputerDAO.EFFACERPARCOMPA) ;
			prepare.setLong(1, companyId);
			value=prepare.executeUpdate();
			if (value >0)
			{
				prepare = preparation.prepareStatement(EFFACER) ;
				prepare.setLong(1, companyId);
				value=prepare.executeUpdate();
				if (value==1)
				{
					//preparation.commit();
				}
				else
				{
					//preparation.rollback();
				}
			}
			else
			{
				//preparation.rollback();
			}
			CompanyDAO.connexionClose(preparation);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return value;
	}
}