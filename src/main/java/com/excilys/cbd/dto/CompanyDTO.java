package com.excilys.cbd.dto;

public class CompanyDTO 
{
	private long id;
	private String name;
	public CompanyDTO() {
		
	}
	public CompanyDTO (String id)
	{
		this.id=Long.parseLong(id);
	}
	public CompanyDTO (Long id, String name)
	{
		this.id=id;
		this.name=name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CompanyDTO [id=" + id + ", name=" + name + "]";
	}
}
