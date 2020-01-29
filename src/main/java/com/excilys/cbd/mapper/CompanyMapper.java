package com.excilys.cbd.mapper;


import com.excilys.cbd.dto.CompanyDTO;
import com.excilys.cbd.model.Company;

public class CompanyMapper 
{
	public static Company convertCompanyDTOtoCompany(CompanyDTO compaDTO)
	{
		long id=compaDTO.getId();
		Company compa = new Company.CompanyBuilder().setId(id).build();
		return compa;
	}
}
