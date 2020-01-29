package com.excilys.cbd.model;
import java.time.*;
import com.excilys.cbd.model.Company;
public class Computer
{
	private long idComputer;
	private String name;
	private LocalDate introduced;
	private LocalDate discontinued;
	private Company compa;
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
		this.compa=builder.compa;
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
	public Company getCompany() 
	{
		return compa;
	}
	public void setCompany(Company compa) 
	{
		this.compa=compa;
	}
	public static class ComputerBuilder
	{
		private long idComputer;
		private String name;
		
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company compa;

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
		public ComputerBuilder setCompany(Company compa)
		{
			this.compa=compa;
			return this;
		}
		public Computer build()
		{
			return new Computer(this);
		}
	}
}