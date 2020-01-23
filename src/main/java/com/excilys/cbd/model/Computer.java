package com.excilys.cbd.model;
import java.time.*;
import java.sql.*;
public class Computer
{
	private long idComputer;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private long idCompany;
	/*public Computer(String name, LocalDate introduced, LocalDate discontinued, long idCompany) 
	{
		super();
		this.name=name;
		if (discontinued.isAfter(introduced))
		{
			this.introduced = introduced;
			this.discontinued = discontinued;
		}
		this.idCompany=idCompany;
	}
	public Computer(String name, long company_id) 
	{
		super();
		this.name = name;
		this.idCompany = company_id;
	}
	public Computer(String name, LocalDate introduced) 
	{
		super();
		this.name = name;
		this.introduced = introduced;
	}
	public Computer(String name, LocalDate introduced, long idCompany) 
	{
		super();
		this.name = name;
		this.introduced = introduced;
		this.idCompany = idCompany;
	}
	public Computer(String name, LocalDate introduced, LocalDate discontinued) 
	{
		super();
		this.name = name;
		if (discontinued.isAfter(introduced))
		{
			this.introduced = introduced;
			this.discontinued = discontinued;
		}
	}
	public Computer(String name) 
	{
		super();
		this.name = name;
	}*/
	private Computer(ComputerBuilder builder)
	{
		this.name=builder.name;
		this.introduced=builder.introduced;
		this.discontinued=builder.discontinued;
		this.idCompany=builder.idCompany;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public LocalDate getIntroduced() 
	{
		return introduced;
	}
	public void setIntroduced(LocalDate introduced) 
	{
		if (introduced != null)
		{
			if (this.discontinued != null)
			{
				if (this.discontinued.isAfter(introduced))
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
	public LocalDate getDiscontinued() 
	{
		return discontinued;
	}
	public void setDiscontinued(LocalDate discontinued) 
	{
		if (discontinued != null)
		{	
			if (this.introduced != null)
			{
				if (discontinued.isAfter(this.introduced))
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
	public long getIdCompany() 
	{
		return idCompany;
	}
	public void setCompany_id(long idCompany) 
	{
		this.idCompany = idCompany;
	}
	public void setIdCompany(long idCompany) {
		this.idComputer = idCompany;
	}
	public static class ComputerBuilder
	{
		private long idComputer;
		private String name;
		
		private LocalDate introduced;
		private LocalDate discontinued;
		private long idCompany;

		public ComputerBuilder (String name)
		{
			this.name=name;
		}
		public ComputerBuilder setIntroduced(LocalDate introduced)
		{
			this.introduced=introduced;
			return this;
		}
		public ComputerBuilder setDiscontinued(LocalDate discontinued)
		{
			this.discontinued=discontinued;
			return this;
		}
		public ComputerBuilder setIdCompany(long idCompany)
		{
			this.idCompany=idCompany;
			return this;
		}
		public Computer build()
		{
			return new Computer(this);
		}
	}
}