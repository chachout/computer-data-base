package com.excilys.cbd.dao;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;

import java.sql.*;
import java.time.LocalDate;

@Repository
public class ComputerDAO
{
	static String CREER = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	public static String TOUTCOMPU = "SELECT * FROM computer";
	public static String TROUVERID = "SELECT * FROM computer WHERE id = ?";
	public static String TROUVERNOM = "SELECT  computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id WHERE LOWER(computer.name) LIKE ? OR LOWER(company.name) LIKE ? OR introduced LIKE ? OR discontinued LIKE ?;";
	public static String MODIFIER = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	public static String EFFACER = "DELETE FROM computer WHERE id = ?";
	public static String EFFACERPARCOMPA = "DELETE FROM computer WHERE company_id = ?";
	public static String SELECTION = "SELECT computer.name as computer_name, computer.id as computer_id, computer.introduced, computer.discontinued, computer.company_id, company.name as company_name FROM computer LEFT JOIN company on company.id=computer.company_id";
	public static String LIMIT = " LIMIT ?, ?";
	public static String ASCENDANT = " ASC";
	public static String DESCENDANT = " DESC";
	public static String ORDER = " ORDER BY ";
	@Autowired
	private CompanyDAO companyDao;
	@Autowired
	private ConnecHikari connecHikari;
	public ComputerDAO()
	{
	}

	public ArrayList<Computer> toutComputer() throws ClassNotFoundException
	{
		
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		try
		{
			Connection preparation = connecHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(TOUTCOMPU) ;
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = null;
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco!=null)
				{
					discontinued =disco.toLocalDate();
				}
				String companyName=resultat.getString("company_name");
				
				Company compa = new Company.CompanyBuilder().setName(companyName).build();
				long id =resultat.getLong("computer_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
				comp.setId(id);
				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
			}
			connecHikari.disconnect();
			return listComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public int count() throws ClassNotFoundException
	{	
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		int nombreComputer=0;
		try
		{
			Connection preparation = connecHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(TOUTCOMPU);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = null;
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco!=null)
				{
					discontinued =disco.toLocalDate();
				}
				Long company_id=resultat.getLong("company_id");
				
				Company compa = companyDao.trouverCompany(company_id);
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			

				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
				nombreComputer=listComputer.size();
			}
			connecHikari.disconnect();
			return nombreComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 0;
	}
	public ArrayList<Computer> pageComputer(int tri, String colonne, int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		String requete;
		try
		{
			Connection preparation = connecHikari.getConnection();
			if (tri==0||colonne==null)
			{
				requete = SELECTION + LIMIT;
			}
			else
			{
				if (tri==1)
				{
					requete = SELECTION + ORDER + colonne + ASCENDANT + LIMIT;
				}
				else
				{
					requete = SELECTION + ORDER + colonne + DESCENDANT + LIMIT;
				}
			}
			PreparedStatement prepare = preparation.prepareStatement(requete);
			prepare.setInt(2, limit);
			prepare.setInt(1, offset);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 
				String name =resultat.getString("computer_name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = null;
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				LocalDate discontinued = null;
				if (disco!=null)
				{
					discontinued = disco.toLocalDate();
				}
				String companyName=resultat.getString("company_name");
				Company compa = new Company.CompanyBuilder().setName(companyName).build();
				long id =resultat.getLong("computer_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
				comp.setId(id);

				if (comp.getName()!=null)
				{
					listComputer.add(comp);
				}
			}
			connecHikari.disconnect();
			return listComputer;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public Computer trouverid (long id) throws ClassNotFoundException
	{
		try
		{
			Connection preparation = connecHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(TROUVERID) ;
			prepare.setLong(1, id);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 

				String name =resultat.getString("name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = LocalDate.now();
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				
				LocalDate discontinued = LocalDate.now();
				if (disco!=null)
				{
					discontinued =disco.toLocalDate();
				}
				long company_id=resultat.getLong("company_id");
				
				Company compa = companyDao.trouverCompany(company_id);
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			

				if (comp.getName()!=null)
				{
					return comp;
				}
				
				else
				{
					return null;
				}
			}
			connecHikari.disconnect();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Computer> trouvernom (String recherche) throws ClassNotFoundException
	{
		ArrayList<Computer> listComputer= new ArrayList<Computer>();
		try
		{
			Connection preparation = connecHikari.getConnection();
			PreparedStatement prepare = preparation.prepareStatement(TROUVERNOM) ;
			recherche=recherche.toLowerCase();
			recherche="%"+recherche+"%";
			prepare.setString(1,recherche);
			prepare.setString(2,recherche);
			prepare.setString(3,recherche);
			prepare.setString(4,recherche);
			ResultSet resultat=prepare.executeQuery();
			while (resultat.next())
			{ 

				String name =resultat.getString("computer_name");
				Date intro = resultat.getDate("introduced");
				Date disco = resultat.getDate("discontinued");
				LocalDate introduced = LocalDate.now();
				if (intro!=null)
				{
					introduced =intro.toLocalDate();
				}
				
				LocalDate discontinued = LocalDate.now();
				if (disco!=null)
				{
					discontinued =disco.toLocalDate();
				}
				String companyName=resultat.getString("company_name");
				Company compa = new Company.CompanyBuilder().setName(companyName).build();
				long id =resultat.getLong("computer_id");
				Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
				comp.setId(id);
				listComputer.add(comp);
			}
			connecHikari.disconnect();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return listComputer;
	}
	public int modifier(Computer comp) throws ClassNotFoundException
	{
		int value = 0;
		Connection preparation = connecHikari.getConnection();
		if (comp.getName()!=null)
		{
			try
			{
				PreparedStatement prepare =preparation.prepareStatement(MODIFIER);
				prepare.setString(1, comp.getName());
				if (comp.getIntroduced()!=null)
				{
					prepare.setTimestamp(2, Timestamp.valueOf(comp.getIntroduced().atStartOfDay()));
				}
				if (comp.getDiscontinued() != null)
				{
					prepare.setTimestamp(3, Timestamp.valueOf(comp.getDiscontinued().atStartOfDay()));
				}
				if (comp != null)
				{
					if (comp.getCompany() != null)
					{
						if (comp.getCompany().getId() != null)
						{
							prepare.setLong(4, comp.getCompany().getId());
						}
						else
						{
							prepare.setNull(4, java.sql.Types.BIGINT);
						}
					}
				}
				prepare.setLong(5,comp.getId());
				value = prepare.executeUpdate();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		connecHikari.disconnect();
		return value;
	}
	public int effacer(long computerId) throws ClassNotFoundException
	{
		int value = 0;
		Connection preparation = connecHikari.getConnection();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(EFFACER) ;
			prepare.setLong(1, computerId);
			value=prepare.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		connecHikari.disconnect();
		return value;
	}
	public int effacerComputIdCompany(long companyId) throws ClassNotFoundException
	{
		int value = 0;
		Connection preparation = connecHikari.getConnection();
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(EFFACERPARCOMPA) ;
			prepare.setLong(1, companyId);
			value=prepare.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		connecHikari.disconnect();
		return value;
	}
	public int creer(Computer comp) throws ClassNotFoundException
	{
		Connection preparation = connecHikari.getConnection();
		int value = 0;
		try
		{
			PreparedStatement prepare = preparation.prepareStatement(CREER) ;
			prepare.setString(1, comp.getName());
			if (comp.getIntroduced()!=null)
			{
				prepare.setTimestamp(2, Timestamp.valueOf(comp.getIntroduced().atStartOfDay()));
			}
			if (comp.getDiscontinued() != null)
			{
				prepare.setTimestamp(3, Timestamp.valueOf(comp.getDiscontinued().atStartOfDay()));
			}
			if (comp != null)
			{
				if (comp.getCompany() != null)
				{
					if (comp.getCompany().getId() != null)
					{
						prepare.setLong(4, comp.getCompany().getId());
					}
					else
					{
						prepare.setNull(4, java.sql.Types.BIGINT);
					}
				}
			}
			value = prepare.executeUpdate();
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		connecHikari.disconnect();
		return value;
	}
}