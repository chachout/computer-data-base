package com.excilys.cbd.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.excilys.cbd.dao.CompanyDAO;
import com.excilys.cbd.dto.CompanyDTO;
import com.excilys.cbd.dto.ComputerDTO;
import com.excilys.cbd.exceptions.DateException;
import com.excilys.cbd.exceptions.NameException;
import com.excilys.cbd.mapper.ComputerMapper;
import com.excilys.cbd.model.Company;
import com.excilys.cbd.model.Computer;
import com.excilys.cbd.service.ServiceCompany;
import com.excilys.cbd.service.ServiceComputer;
import com.excilys.cbd.validation.ValidationBackComputer;




/**
 * Servlet implementation class AddServlet
 */
@WebServlet(urlPatterns = "/AddServlet")
@Controller
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int maxPage;
	public int taillePage;
	@Autowired
	private ServiceComputer serviceComputer;
	private ServiceCompany serviceCompany;
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
			listCompany = serviceCompany.getCompanyList();
			request.setAttribute("listCompany",listCompany);
			request.getRequestDispatcher("views/addComputer.jsp").forward(request,response);
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			String name = request.getParameter("computerName");
			String introduced = request.getParameter("introduced");
			String discontinued = request.getParameter("discontinued");
			String company = request.getParameter("company");
			CompanyDTO compaDTO =new CompanyDTO(company);
			ComputerDTO compuDTO =new ComputerDTO(name,introduced,discontinued,compaDTO); 
			Computer comp = ComputerMapper.convertComputerDTOtoComputer(compuDTO);
			StringBuilder erreur = new StringBuilder();
			try
			{
				ValidationBackComputer.validationComputer(comp);
				try 
				{
					Optional<Computer> comput= serviceComputer.addComputer(comp);
					taillePage=10;
					maxPage=serviceComputer.getCount()/taillePage;
					request.getRequestDispatcher("ListServlet?page="+ maxPage).forward(request,response);
				} 
				catch (ClassNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			catch (NameException e)
			{ 
				erreur.append("Le nom n'est pas rempli. Merci de corriger\n");
			}
			catch (DateException e1)
			{
				erreur.append("La date d'introduction n'est pas antérieur à la date de disparition. Merci de corriger\n");
			}
			request.setAttribute("erreur", erreur);
			request.setAttribute("computerToAdd", comp);
			doGet(request,response);
		
		
	}

}
