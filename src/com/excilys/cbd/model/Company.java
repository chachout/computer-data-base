package com.excilys.cbd.model;
import java.sql.*;
public class Company 
{
	private Long id;
	private String name;
	public Company(Long idCompany, String name) 
	{  
		super();
		this.id = idCompany;
		this.name = name;
	}
	public Long getIdCompany() 
	{
		return id;
	}
	public void setIdCompany(Long idCompany) 
	{
		this.id = idCompany;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name =name;
	}
}