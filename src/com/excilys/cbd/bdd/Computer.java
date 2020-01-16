package com.excilys.cbd.bdd;
import java.sql.*;
public class Computer
{
	Long computer_id;
	String name;
	Timestamp introduced;
	Timestamp discontinued;
	Long company_id;
	public Computer(String name, Timestamp introduced, Timestamp discontinued, Long company_id) 
	{
		super();
		this.name=name;
		if (discontinued.after(introduced))
		{
			this.introduced = introduced;
			this.discontinued = discontinued;
		}
		this.company_id=company_id;
	}
	public Computer(String name, Long company_id) 
	{
		super();
		this.name = name;
		this.company_id = company_id;
	}
	public Computer(String name, Timestamp introduced) 
	{
		super();
		this.name = name;
		this.introduced = introduced;
	}
	public Computer(String name, Timestamp introduced, Long company_id) 
	{
		super();
		this.name = name;
		this.introduced = introduced;
		this.company_id = company_id;
	}
	public Computer(String name, Timestamp introduced, Timestamp discontinued) 
	{
		super();
		this.name = name;
		if (discontinued.after(introduced))
		{
			this.introduced = introduced;
			this.discontinued = discontinued;
		}
	}
	public Computer(String name) 
	{
		super();
		this.name = name;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public Timestamp getIntroduced() 
	{
		return introduced;
	}
	public void setIntroduced(Timestamp introduced) 
	{
		if (introduced != null)
		{
			if (this.discontinued != null)
			{
				if (this.discontinued.after(introduced))
				{
					this.introduced = introduced;
				}
			}
			else
			{
				this.introduced = introduced;
			}
		}	
	}
	public Timestamp getDiscontinued() 
	{
		return discontinued;
	}
	public void setDiscontinued(Timestamp discontinued) 
	{
		if (discontinued != null)
		{	
			if (this.introduced != null)
			{
				if (discontinued.after(this.introduced))
				{
					this.discontinued = discontinued;
				}
			}
			else
			{
				this.discontinued = discontinued;
			}
		}
	}
	public Long getCompany_id() 
	{
		return company_id;
	}
	public void setCompany_id(Long company_id) 
	{
		this.company_id = company_id;
	}

	public void setComputer_id(Long computer_id) {
		this.computer_id = computer_id;
	}
}