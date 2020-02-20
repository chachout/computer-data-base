package com.excilys.cbd.controleur;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cbd.dto.ComputerDTO;
import com.excilys.cbd.mapper.ComputerMapper;
import com.excilys.cbd.model.Computer;
import com.excilys.cbd.service.ServiceComputer;
@Controller
public class DashboardController {
	
	private int totalComputer;
	private int maxPage;
	@Autowired
	public ServiceComputer serviceComputer;
	
	private String search(String recherche,ModelMap dataMap) throws ClassNotFoundException{
		List<ComputerDTO>computerDTOList=new ArrayList<ComputerDTO>();
		List<Computer>computerList=new ArrayList<Computer>();
		computerList=serviceComputer.findComputerByName(recherche);
		totalComputer=computerList.size();
		dataMap.put("totalComputer", totalComputer);
		computerList.stream().forEach(comp->computerDTOList.add(ComputerMapper.convertComputertoComputerDTO(comp)));
		dataMap.put("listComput", computerDTOList);
		return "dashboard";
	}
	private String classement(int tri, String colonne, int taillePage,int page, ModelMap dataMap) throws ClassNotFoundException {
		List<ComputerDTO>computerDTOList=new ArrayList<ComputerDTO>();
		List<Computer>computerList=new ArrayList<Computer>();
		totalComputer=serviceComputer.getCount();
		maxPage=totalComputer/taillePage;
		tri%=3;
		if (page!=1) {
			if (page==maxPage) {
				computerList=serviceComputer.getComputerListPaginer(tri,colonne,serviceComputer.getCount()%10,page*taillePage);
			} else {
				computerList=serviceComputer.getComputerListPaginer(tri,colonne,taillePage,page*taillePage);
			}
		} else {
			computerList=serviceComputer.getComputerListPaginer(tri,colonne,taillePage,0);
		}
		computerList.stream().forEach(comp->computerDTOList.add(ComputerMapper.convertComputertoComputerDTO(comp)));
		dataMap.put("listComput", computerDTOList);
		dataMap.put("totalComputer", totalComputer);
		dataMap.put("page", page);
		dataMap.put("maxPage", maxPage);
		dataMap.put("taillePage", taillePage);
		dataMap.put("tri", tri);
		dataMap.put("colonne", colonne);
		return "dashboard";
	}
	private void deleteComputers(String selection, ModelMap dataMap) throws NumberFormatException, ClassNotFoundException
	{
		String[] listId = selection.split(",");
		for (int i=0;i<listId.length;i++) {
			serviceComputer.deleteComputer(Long.parseLong(listId[i]));
		}
	}
	@GetMapping("dashboard")
	public String getDashboard(@RequestParam(value="search", required = false) String recherche,
			@RequestParam(value="tri", defaultValue = "0") int tri,
			@RequestParam(value="colonne", defaultValue = "") String colonne,
			@RequestParam(value="page", defaultValue = "1")int page,
			@RequestParam(value="taillePage",defaultValue = "10")int taillePage,
			ModelMap dataMap) throws ClassNotFoundException {
		if (recherche==null||recherche=="") {
			return classement(tri, colonne, taillePage, page, dataMap);
		} else {
			return search(recherche, dataMap);
		}
		
	}
	@PostMapping("dashboard")
	public String postEditComputer (@RequestParam(value="selection", defaultValue = "")String selection,
			ModelMap dataMap) throws NumberFormatException, ClassNotFoundException {
		deleteComputers(selection,dataMap);
		return "redirect:dashboard";
	}
}
	