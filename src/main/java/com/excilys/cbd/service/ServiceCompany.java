package com.excilys.cbd.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cbd.dao.CompanyDAO;
import com.excilys.cbd.dao.ComputerDAO;
import com.excilys.cbd.model.Company;

@Service
public class ServiceCompany 
{
	private CompanyDAO companyDao;
	
	private ServiceCompany(CompanyDAO companyDao)
	{
		this.companyDao=companyDao;
	}
	
	public ArrayList<Company> getCompanyList() throws ClassNotFoundException
	{
		ArrayList<Company> listCompa=companyDao.toutCompany();
		return listCompa;	
	}
	public Company getCompany(Long id) throws ClassNotFoundException
	{
		Company compa = companyDao.trouverCompany(id);
		return compa;
	}
	public void getDeleteCompany(Long id) throws ClassNotFoundException, SQLException
	{
		companyDao.effacer(id);
	}
}
