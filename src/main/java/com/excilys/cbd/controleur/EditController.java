package com.excilys.cbd.controleur;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cbd.dto.CompanyDTO;
import com.excilys.cbd.dto.ComputerDTO;
import com.excilys.cbd.mapper.CompanyMapper;
import com.excilys.cbd.mapper.ComputerMapper;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;
import com.excilys.cbd.service.ServiceCompany;
import com.excilys.cbd.service.ServiceComputer;

@Controller
public class EditController {
	@Autowired
	public ServiceCompany serviceCompany;
	@Autowired
	public ServiceComputer serviceComputer;
	
	private String findComputUpdate(String idComputer, ModelMap dataMap) throws ClassNotFoundException {
		List<CompanyDTO>companyDTOList=new ArrayList<CompanyDTO>();
		List<Company>companyList=new ArrayList<Company>();
		companyList=serviceCompany.getCompanyList();
		companyList.stream().forEach(compa->companyDTOList.add(CompanyMapper.convertCompanytoCompanyDTO(compa)));
		dataMap.put("listCompany", companyDTOList);
		long id = Long.parseLong(idComputer);
		Computer comput = serviceComputer.findComputerById(id);
		ComputerDTO compDTO = ComputerMapper.convertComputertoComputerDTO(comput);
		dataMap.put("computerToUpdate",compDTO);
		return "editComputer";
	}
	private void updateComput(ComputerDTO compDTO, ModelMap dataMap) throws ClassNotFoundException {
		Computer comp = ComputerMapper.convertComputerDTOtoComputer(compDTO);
		serviceComputer.editComputer(comp);
	}
	@GetMapping("editComputer")
	public String getEditComputer(@RequestParam(value="id", defaultValue = "1") String idComputer,
			ModelMap dataMap) throws ClassNotFoundException {
		return findComputUpdate(idComputer, dataMap);
	}
	@PostMapping("editComputer")
	public String postEditComputer (@ModelAttribute("computerToUpdate")ComputerDTO compDTO,
			@RequestParam(value="maxPage", defaultValue = "1")int maxPage,
			ModelMap dataMap) throws ClassNotFoundException {
		updateComput(compDTO, dataMap);
		dataMap.put("computerToUpdate", compDTO);
		return "dashboard";
	}
}
