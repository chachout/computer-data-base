package com.excilys.cbd.dto;

import java.time.LocalDate;

import com.excilys.cbd.model.Company;

public class ComputerDTO 
{
	private long id;
	private String name;
	private String introduced;
	private String discontinued;
	private CompanyDTO companyDTO;
	public ComputerDTO(String name,String introduced, String discontinued, CompanyDTO companyDTO)
	{
		this.name=name;
		this.introduced=introduced;
		this.discontinued=discontinued;
		this.companyDTO=companyDTO;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}
	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}
	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + companyDTO + "]";
	}
}
