package com.excilys.cbd.mapper;
import java.time.LocalDate;

import com.excilys.cbd.dto.*;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;
public class ComputerMapper 
{
	public static Computer convertComputerDTOtoComputer(ComputerDTO compDTO)
	{
		String name=compDTO.getName();
		String intro=compDTO.getIntroduced();
		String disco=compDTO.getDiscontinued();
		CompanyDTO compaDTO=compDTO.getCompa();
		LocalDate introduced = LocalDate.parse(intro);
		LocalDate discontinued = LocalDate.parse(disco);
		Company compa = CompanyMapper.convertCompanyDTOtoCompany(compaDTO);
		Computer comp = new Computer.ComputerBuilder(name).setIntroduced(introduced).setDiscontinued(discontinued).setCompany(compa).build(); 
		return comp;
	}
}
