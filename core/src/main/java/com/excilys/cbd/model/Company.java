package com.excilys.cbd.model;
import javax.persistence.*;
@Entity
@Table(name = "company")
public class Company 
{
	@Id
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	public Company()
	{
		
	}
	
	private Company(CompanyBuilder builder) 
	{
		this.id=builder.id;
		this.name=builder.name;
	}
	public Long getId() 
	{
		return id;
	}
	public void setId(long id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name =name;
	}
	public static class CompanyBuilder
	{
		private long id;
		private String name;
		
		public CompanyBuilder ()
		{
		}
		public CompanyBuilder setId(long id)
		{
			this.id=id;
			return this;
		}
		public CompanyBuilder setName(String name)
		{
			this.name=name;
			return this;
		}
		public Company build()
		{
			return new Company(this);
		}
	}
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
	
	
	
}