package com.excilys.cbd.dao;

import static org.junit.Assert.*;
import java.sql.*;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.excilys.cbd.model.Computer;

import Logger.Logging;

public class CompanyDAOTest {

	@Before
	public void setUp() throws Exception 
	{
		System.setProperty("test","true");
	}
	
	@After
	public void tearDown() throws Exception 
	{
		System.setProperty("test","false");
	}

	@Test
	public void testcreer() throws ClassNotFoundException 
	{
		
		String name = "MSI";
		LocalDate introduced = null;
		LocalDate discontinued = null;
		long company_id = 1;
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setIdCompany(company_id).build();			
		int addValue = ComputerDAO.creer(comp);
		assertEquals(addValue, 1);
		
	}
	@Test
	public void testTrouverId() throws ClassNotFoundException
	{
		Computer comp = null;
		try {
			comp = ComputerDAO.trouverid(624L);
			Logging.afficher("cc");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(comp!=null);
	}
}