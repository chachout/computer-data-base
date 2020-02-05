package com.excilys.cbd.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.cbd.dao.CompanyDAO;
import com.excilys.cbd.model.Company;


public class ServiceCompany 
{
	private static ServiceCompany instance;
	
	private ServiceCompany() throws ClassNotFoundException
	{
		
	}
	public static ServiceCompany getInstance() throws ClassNotFoundException
	{
		if (instance == null)
		{
			instance= new ServiceCompany();
			return instance; 
		}
		else
		{
			return instance;
		}
	}
	public ArrayList<Company> getCompanyList() throws ClassNotFoundException
	{
		ArrayList<Company> listCompa=CompanyDAO.getInstance().toutCompany();
		return listCompa;	
	}
	public Company getCompany(Long id) throws ClassNotFoundException
	{
		Company compa = CompanyDAO.getInstance().trouverCompany(id);
		return compa;
	}
	public void getDeleteCompany(Long id) throws ClassNotFoundException, SQLException
	{
		CompanyDAO.getInstance().effacer(id);
	}
}
