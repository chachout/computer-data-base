package com.excilys.cbd.dao;
import java.sql.*;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.excilys.cbd.model.Company;

@Repository
public class CompanyDAO 
{
	public static String TROUVERCOMPAID = "SELECT * FROM company WHERE id = ?";
	public static String TROUVERCOMPANOM = "SELECT * FROM company WHERE name = ?";
	public static String TOUTCOMPA = "SELECT * FROM company";
	public static String EFFACER = "DELETE FROM company WHERE id = ?";
	@Autowired
	private ConnecHikari connecHikari;
	private CompanyDAO()
	{
	}
	
	public ArrayList<Company> toutCompany() throws ClassNotFoundException
	{
		ArrayList<Company> listCompany= new ArrayList<Company>();
		try
		{
			Connection preparation = connecHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(TOUTCOMPA) ;
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				long id=resultat.getLong("id");
				Company compa = new Company.CompanyBuilder().setId(id).setName(name).build();
				listCompany.add(compa);
			}
			connecHikari.disconnect();
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
			Connection preparation = connecHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(TROUVERCOMPAID) ;
			prepare.setLong(1, id);
			ResultSet resultat=prepare.executeQuery();
			Company compa=null;
			if(resultat.next())
			{
				long idCompany = resultat.getLong("id");
				String name = resultat.getString("name");
				compa = new Company.CompanyBuilder().setId(idCompany).setName(name).build();
			}
			connecHikari.disconnect();
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
			Connection preparation = connecHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(TROUVERCOMPANOM) ;
			prepare.setString(1, name);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				long id = resultat.getLong("id");
				String nameCompany = resultat.getString("name");

				Company compa = new Company.CompanyBuilder().setId(id).setName(nameCompany).build();

				connecHikari.disconnect();
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
		Connection preparation = connecHikari.getConnection();
		preparation.setAutoCommit(false);
		try 
		{
			
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
					preparation.commit();
					
				}
				else
				{
					preparation.rollback();
				}
			}
			else
			{
				preparation.rollback();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			preparation.rollback();
		}
		preparation.setAutoCommit(true);
		connecHikari.disconnect();
		return value;
	}
}