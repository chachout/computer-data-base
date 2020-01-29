package com.excilys.cbd.dto;

import java.time.LocalDate;

import com.excilys.cbd.model.Company;

public class ComputerDTO 
{
	private long idComputer;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyDTO compa;
	public ComputerDTO(String name,String introduced, String discontinued, CompanyDTO compa)
	{
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.compa=compa;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getIdComputer() {
		return idComputer;
	}
	public void setIdComputer(long idComputer) {
		this.idComputer = idComputer;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}
	public CompanyDTO getCompa() {
		return compa;
	}
	public void setCompa(CompanyDTO compa) {
		this.compa = compa;
	}
	
}
