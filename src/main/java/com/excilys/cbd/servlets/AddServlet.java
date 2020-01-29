package com.excilys.cbd.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.cbd.dto.*;
import com.excilys.cbd.mapper.ComputerMapper;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cbd.service.ServiceCompany;
import com.excilys.cbd.service.ServiceComputer;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet(urlPatterns = "/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Company> listCompany;
		try 
		{
			listCompany = ServiceCompany.getInstance().getCompanyList();
			request.setAttribute("listCompany",listCompany);
			request.getRequestDispatcher("views/addComputer.jsp").forward(request,response);
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company");
		CompanyDTO compaDTO =new CompanyDTO(company);
		ComputerDTO compuDTO =new ComputerDTO(name,introduced,discontinued,compaDTO);
		Computer comp = ComputerMapper.convertComputerDTOtoComputer(compuDTO);
		
			Optional<Computer> comput= ServiceComputer.getInstance().addComputer(comp);
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
