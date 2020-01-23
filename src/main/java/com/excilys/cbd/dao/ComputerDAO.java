package com.excilys.cbd.dao;
import java.util.ArrayList;

import com.excilys.cbd.model.Computer;
import java.sql.*;
import java.time.LocalDate;

public class ComputerDAO
{
	public static String CREER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	public static String TOUTCOMPU = "SELECT * FROM computer";
	public static String PAGINATION = "SELECT * FROM computer LIMIT ?, ?";
	public static String TROUVERID = "SELECT * FROM computer WHERE id = ?";
	public static String TROUVERNOM = "SELECT * FROM computer WHERE name = ?";
	public static String MODIFIER = "UPDATE computeur SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	public static String EFFACER = "DELETE FROM computer WHERE id = ?";
	public static Connection connexion() throws ClassNotFoundException
	{
	
		Connection preparation = ConnecH2.getConnexion();
		return preparation;
	
	}
	public static ArrayList<Computer> toutComputer() throws ClassNotFoundException
	{
		
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		try
		{
			Connection preparation =ComputerDAO.connexion();
			PreparedStatement prepare = preparation.prepareStatement(TOUTCOMPU) ;
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				Timestamp intro = resultat.getTimestamp("introduced");
				Timestamp disco = resultat.getTimestamp("discontinued");
				LocalDate introduced = intro.toLocalDateTime().toLocalDate();
				LocalDate discontinued = disco.toLocalDateTime().toLocalDate();
				long company_id=resultat.getLong("company_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setIdCompany(company_id).build();			

				System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getIdCompany());
				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
			}
			ConnecH2.connexionClose();
			return listComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ConnecH2.connexionClose();
		return null;
	}
	public static ArrayList<Computer> pageComputer(int limit, int offset) throws ClassNotFoundException
	{
		Connection preparation =ComputerDAO.connexion();
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(PAGINATION);
			prepare.setInt(2, limit);
			prepare.setInt(1, offset);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				Timestamp intro = resultat.getTimestamp("introduced");
				Timestamp disco = resultat.getTimestamp("discontinued");
				LocalDate introduced = intro.toLocalDateTime().toLocalDate();
				LocalDate discontinued = disco.toLocalDateTime().toLocalDate();
				long company_id=resultat.getLong("company_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setIdCompany(company_id).build();			
			

				System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getIdCompany());
				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
			}
			ConnecH2.connexionClose();
			return listComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ConnecH2.connexionClose();
		return null;
	}
	public static Computer trouverid (long id) throws ClassNotFoundException
	{
		Connection preparation =ComputerDAO.connexion();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(TROUVERID) ;
			prepare.setLong(1, id);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 

				String name =resultat.getString("name");
				Timestamp intro = resultat.getTimestamp("introduced");
				Timestamp disco = resultat.getTimestamp("discontinued");
				LocalDate introduced = intro.toLocalDateTime().toLocalDate();
				LocalDate discontinued = disco.toLocalDateTime().toLocalDate();
				long company_id=resultat.getLong("company_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setIdCompany(company_id).build();			
			

				System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getIdCompany());
				if (comp.getName()!=null)
				{
					ConnecH2.connexionClose();
					return comp;
				}
				else
				{
					ConnecH2.connexionClose();
					System.out.println("Il n'y a pas d'ordinateur avec cet id");
					return null;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ConnecH2.connexionClose();
		return null;
	}
	public static Computer trouvernom (String name) throws ClassNotFoundException
	{
		Connection preparation =ComputerDAO.connexion();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(TROUVERNOM) ;
			prepare.setString(1,name);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 

				String nom =resultat.getString("name");
				Timestamp intro = resultat.getTimestamp("introduced");
				Timestamp disco = resultat.getTimestamp("discontinued");
				LocalDate introduced = intro.toLocalDateTime().toLocalDate();
				LocalDate discontinued = disco.toLocalDateTime().toLocalDate();
				long company_id=resultat.getLong("company_id");
				Computer comp = new Computer.ComputerBuilder(nom).setIntroduced(introduced).setDiscontinued(discontinued).setIdCompany(company_id).build();			
				

				System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getIdCompany());
				if (comp.getName()!=null)
				{
					ConnecH2.connexionClose();
					return comp;
				}
				else
				{
					ConnecH2.connexionClose();
					System.out.println("Il n'y a pas d'ordinateur avec ce nom");
					return null;
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ConnecH2.connexionClose();
		return null;
	}
	public static void modifier(long computer_id, String name, Timestamp introduced, Timestamp discontinued, long company_id) throws ClassNotFoundException
	{
		Connection preparation =ComputerDAO.connexion();
		Computer comp = new Computer.ComputerBuilder(null).build();			
		comp = trouverid(computer_id);
		if (comp.getName()!=null)
		{
			try
			{
				PreparedStatement prepare =preparation.prepareStatement(MODIFIER);
				prepare.setString(1, name);
				prepare.setTimestamp(2, introduced);
				prepare.setTimestamp(3, introduced);
				if (company_id > 0)
				{
					prepare.setLong(4, company_id);
				}
				else
				{
					prepare.setNull(4, java.sql.Types.BIGINT);
				}
				prepare.setLong(5, computer_id);
				prepare.executeUpdate();
				comp = trouverid(computer_id);
				System.out.println(comp.getName() + " " + comp.getIntroduced() + " " + comp.getDiscontinued() + " " + comp.getIdCompany());

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		ConnecH2.connexionClose();
	}
	public static void effacer(long computer_id) throws ClassNotFoundException
	{
		Connection preparation =ComputerDAO.connexion();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(EFFACER) ;
			prepare.setLong(1, computer_id);
			prepare.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ConnecH2.connexionClose();
	}
	public static int creer(Computer comp) throws ClassNotFoundException
	{
		Connection preparation = ComputerDAO.connexion();
		int value = 0;
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(CREER) ;
			prepare.setString(1, comp.getName());
			prepare.setTimestamp(2, Timestamp.valueOf(comp.getIntroduced().atStartOfDay()));
			prepare.setTimestamp(3, Timestamp.valueOf(comp.getDiscontinued().atStartOfDay()));
			if (comp.getIdCompany() >0)
			{
				prepare.setLong(4,comp.getIdCompany());
			}
			else
			{
				prepare.setNull(4, java.sql.Types.BIGINT);
			}
			value = prepare.executeUpdate();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		ConnecH2.connexionClose();
		return value;
	}
}