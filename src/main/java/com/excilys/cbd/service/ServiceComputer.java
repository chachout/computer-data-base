package com.excilys.cbd.service;

import java.util.ArrayList;
import java.util.Optional;

import com.excilys.cbd.dao.ComputerDAO;
import com.excilys.cbd.model.Computer;

public class ServiceComputer 
{
	private static ServiceComputer instance;
	private final ComputerDAO computerDao = ComputerDAO.getInstance();
	
	private ServiceComputer() throws ClassNotFoundException
	{
		
	}
	public static ServiceComputer getInstance() throws ClassNotFoundException
	{
		if (instance == null)
		{
			instance= new ServiceComputer();
			return instance;
		}
		else
		{
			return instance;
		}
	}
	public ArrayList<Computer> getComputerList() throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.toutComputer();
		return listComput;	
	}
	public ArrayList<Computer> getComputerListPaginer(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageComputer(limit, offset);
		return listComput;
	}
	public int getCount() throws ClassNotFoundException
	{
		int nombreComputer=computerDao.count();
		return nombreComputer;	
	}
	public Optional<Computer> addComputer(Computer comput) throws ClassNotFoundException
	{
		int i=ComputerDAO.getInstance().creer(comput);
		if (i==1)
		{
			return Optional.of(comput);
		}
		else 
		{
			return Optional.empty();
		}
	}
}
