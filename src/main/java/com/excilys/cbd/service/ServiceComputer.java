package com.excilys.cbd.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.cbd.dao.ComputerDAO;
import com.excilys.cbd.model.Computer;

@Service
public class ServiceComputer 
{
	private ComputerDAO computerDao;
	
	private ServiceComputer(ComputerDAO computerDao)
	{
		this.computerDao=computerDao;
	}
	public ArrayList<Computer> getComputerList() throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.toutComputer();
		return listComput;	
	}
	public ArrayList<Computer> getComputerListPaginer(int tri, String colonne, int limit, int offset) throws ClassNotFoundException
	{
		ArrayList<Computer> listComput=computerDao.pageComputer(tri, colonne, limit, offset);
		return listComput;
	}
	public int getCount() throws ClassNotFoundException
	{
		int nombreComputer=computerDao.count();
		return nombreComputer;	
	}
	public Optional<Computer> addComputer(Computer comput) throws ClassNotFoundException
	{
		int i=computerDao.creer(comput);
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
		computerDao.modifier(comp);
	}
	public Computer findComputerById(Long id) throws ClassNotFoundException
	{
		Computer comp = computerDao.trouverid(id);
		return comp;
	}
	public void deleteComputer(long id) throws ClassNotFoundException
	{
		computerDao.effacer(id);
	}
	public ArrayList<Computer> findComputerByName(String name) throws ClassNotFoundException
	{
		ArrayList<Computer> computerList=computerDao.trouvernom(name);
		return computerList;
	}
}
