package com.excilys.cbd.model;
import java.sql.*;
public class Company 
{
	Long id;
	String name;
	public Company(Long company_id, String name) 
	{  
		super();
		this.id = company_id;
		this.name = name;
	}
	public Long getCompany_id() 
	{
		return id;
	}
	public void setCompany_id(Long company_id) 
	{
		this.id = company_id;
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