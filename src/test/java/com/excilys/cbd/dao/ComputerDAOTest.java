//package com.excilys.cbd.dao;
//
//import static org.junit.Assert.*;
//import java.sql.*;
//import java.time.LocalDate;
//import java.time.Month;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import com.excilys.cbd.model.Company;
//import com.excilys.cbd.model.Computer;
//
//import com.excilys.cbd.Logger.Logging;
//
//public class ComputerDAOTest {
//
//	@Before
//	public void setUp() throws Exception 
//	{
//		System.setProperty("test","true");
//	}
//	
//	@After
//	public void tearDown() throws Exception 
//	{
//		System.setProperty("test","false");
//	}
//
//	@Test
//	public void testcreer() throws ClassNotFoundException 
//	{
//		
//		String name = "MSI";
//		LocalDate introduced = LocalDate.of(2014, Month.APRIL, 17);
//		LocalDate discontinued = LocalDate.of(2016, Month.JUNE, 26);
//		long company_id = 1;
//		Company compa = CompanyDAO.getInstance().trouverCompany(company_id);
//		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build();			
//		int addValue = ComputerDAO.getInstance().creer(comp);
//		assertNotEquals(addValue, 0);
//		
//	}
//	@Test
//	public void testTrouverId() throws ClassNotFoundException
//	{
//		Computer comp = null;
//		try {
//			comp = ComputerDAO.getInstance().trouverid(6);
//			System.out.println(comp);
//			Logging.afficher("cc");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		assertTrue(comp!=null);
//	}
//}