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
	public void editComputer(Computer comp) throws ClassNotFoundException
	{
		//System.out.println(comp+"Service");
		ComputerDAO.getInstance().modifier(comp);
	}
	public Computer findComputerById(Long id) throws ClassNotFoundException
	{
		Computer comp = ComputerDAO.getInstance().trouverid(id);
		return comp;
	}
	public void deleteComputer(long id) throws ClassNotFoundException
	{
		ComputerDAO.getInstance().effacer(id);
	}
	public ArrayList<Computer> findComputerByName(String name) throws ClassNotFoundException
	{
		ArrayList<Computer> computerList=ComputerDAO.getInstance().trouvernom(name);
		//System.out.println(computerList+"Service");
		return computerList;
	}
	public ArrayList<Computer> getListOrderComputNameAsc(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageTrierNomComputerAscendant(limit, offset);
		return listComput;
	}
	public ArrayList<Computer> getListOrderComputNameDes(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageTrierNomComputerDescendant(limit, offset);
		return listComput;
	}
	public ArrayList<Computer> getListOrderIntroAsc(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageTrierIntroducedAscendant(limit, offset);
		return listComput;
	}
	public ArrayList<Computer> getListOrderIntroDes(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageTrierIntroducedDescendant(limit, offset);
		return listComput;
	}
	public ArrayList<Computer> getListOrderDiscoAsc(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageTrierDiscontinuedAscendant(limit, offset);
		return listComput;
	}
	public ArrayList<Computer> getListOrderDiscoDes(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageTrierDiscontinuedDescendant(limit, offset);
		return listComput;
	}
	public ArrayList<Computer> getListOrderCompaNameAsc(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageTrierNomCompanyAscendant(limit, offset);
		return listComput;
	}
	public ArrayList<Computer> getListOrderCompaNameDes(int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageTrierNomCompanyDescendant(limit, offset);
		return listComput;
	}
}
