package com.excilys.cbd.mapper;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.cbd.dto.*;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;
public class ComputerMapper implements RowMapper<Computer>
{
	public Computer mapRow(ResultSet resultat, int i) throws SQLException {

		Computer comp = new Computer.ComputerBuilder(resultat.getString("computer_name")).build();
		comp.setId(resultat.getLong("computer_id"));
		Date intro = resultat.getDate("introduced");
		Date disco = resultat.getDate("discontinued");
		LocalDate introduced = null;
		if (intro!=null)
		{
			introduced =intro.toLocalDate();
		}
		LocalDate discontinued = null;
		comp.setIntroduced(introduced);
		if (disco!=null)
		{
			discontinued =disco.toLocalDate();
		}
		comp.setIntroduced(introduced);
		comp.setDiscontinued(discontinued);
		Company compa = new Company.CompanyBuilder().setId(resultat.getLong("company_id")).setName(resultat.getString("company_name")).build();
		comp.setCompany(compa);		
		return comp;
	}
	public static Computer convertComputerDTOtoComputer(ComputerDTO compDTO)
	{
		Long id=compDTO.getId();
		String name=compDTO.getName();
		String intro=compDTO.getIntroduced();
		String disco=compDTO.getDiscontinued();
		CompanyDTO compaDTO=compDTO.getCompanyDTO();
		LocalDate introduced = LocalDate.parse(intro);
		LocalDate discontinued = LocalDate.parse(disco);
		Company compa = CompanyMapper.convertCompanyDTOtoCompany(compaDTO);
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build(); 
		comp.setId(id);
		return comp;
	}
	public static ComputerDTO convertComputertoComputerDTO(Computer comp)
	{
		Long id=comp.getId();
		String name=comp.getName();
		String introduced=null;
		if(comp.getIntroduced()!=null) {
			introduced=comp.getIntroduced().toString();
		} 
		String discontinued=null;
		if(comp.getDiscontinued()!=null) {
			discontinued=comp.getDiscontinued().toString();
		} 		
		Company compa=comp.getCompany();
		
		CompanyDTO compaDTO = CompanyMapper.convertCompanytoCompanyDTO(compa);
		ComputerDTO compDTO = new ComputerDTO(name, introduced, discontinued, compaDTO); 
		compDTO.setId(id);
		return compDTO;
	}
}
