package com.excilys.cbd.mapper;


import java.sql.*;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cbd.dto.CompanyDTO;
import com.excilys.cbd.model.Company;

public class CompanyMapper implements RowMapper<Company>
{
	public Company mapRow(ResultSet resultat, int i) throws SQLException 
	{
		Company compa = new Company.CompanyBuilder().build();
		compa.setId(resultat.getLong("id"));
		compa.setName(resultat.getString("name"));
		return compa;
	}
	
	public static Company convertCompanyDTOtoCompany(CompanyDTO compaDTO)
	{
		long id=compaDTO.getId();
		Company compa = new Company.CompanyBuilder().setId(id).build();
		return compa;
	}
}