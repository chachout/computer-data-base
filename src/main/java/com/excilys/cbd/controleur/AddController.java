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
public class AddController {
	@Autowired
	public ServiceCompany serviceCompany;
	@Autowired
	public ServiceComputer serviceComputer;
	
	private String listCompany(ModelMap dataMap) throws ClassNotFoundException{
		List<CompanyDTO>companyDTOList=new ArrayList<CompanyDTO>();
		List<Company>companyList=new ArrayList<Company>();
		companyList=serviceCompany.getCompanyList();
		companyList.stream().forEach(compa->companyDTOList.add(CompanyMapper.convertCompanytoCompanyDTO(compa)));
		dataMap.put("listCompany", companyDTOList);
		return "addComputer";
	}
	private void addComput(ComputerDTO compDTO, ModelMap dataMap) throws ClassNotFoundException {
		Computer comp = ComputerMapper.convertComputerDTOtoComputer(compDTO);
		serviceComputer.addComputer(comp);
	}
	
	@GetMapping("addComputer")
	public String getAddComputer(ModelMap dataMap) throws ClassNotFoundException {
		return listCompany(dataMap);
	}
	
	@PostMapping("addComputer")
	public String postAddComputer (@ModelAttribute("computerToAdd")ComputerDTO compDTO,
			@RequestParam(value="maxPage", defaultValue = "1")int maxPage,
			ModelMap dataMap) throws ClassNotFoundException {
		System.out.println(compDTO);
		addComput(compDTO, dataMap);
		dataMap.put("computerToAdd", compDTO);
		return "redirect:dashboard?taillePage=10&colonne=&tri=0&page="+maxPage;
	}
			
}
