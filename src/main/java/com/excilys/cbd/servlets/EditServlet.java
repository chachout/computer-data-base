package com.excilys.cbd.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cbd.dto.CompanyDTO;
import com.excilys.cbd.dto.ComputerDTO;
import com.excilys.cbd.mapper.ComputerMapper;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;
import com.excilys.cbd.service.ServiceCompany;
import com.excilys.cbd.service.ServiceComputer;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet(urlPatterns = "/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String idComputer;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<Company> listCompany;
		idComputer=request.getParameter("id");
		try 
		{
			listCompany = ServiceCompany.getInstance().getCompanyList();
			Computer comp = ServiceComputer.getInstance().findComputerById(Long.parseLong(idComputer));
			request.setAttribute("listCompany",listCompany);
			request.setAttribute("idComputer", idComputer);
			request.setAttribute("computerToUpdate", comp);
			request.getRequestDispatcher("views/editComputer.jsp").forward(request,response);
		} 
		catch (NumberFormatException | ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		Long id = Long.parseLong(request.getParameter("id"));
		String name = request.getParameter("computerName");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String company = request.getParameter("company");
		CompanyDTO compaDTO =new CompanyDTO(company);
		ComputerDTO compuDTO =new ComputerDTO(name,introduced,discontinued,compaDTO);
		compuDTO.setIdComputer(id);
		Computer comp = ComputerMapper.convertComputerDTOtoComputer(compuDTO);
		System.out.println(comp+"Servlet");
		try 
		{
			ServiceComputer.getInstance().editComputer(comp);
			request.getRequestDispatcher("ListServlet").forward(request,response);
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			doGet(request, response);
		}
		
	}

}
