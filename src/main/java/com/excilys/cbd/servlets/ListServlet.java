package com.excilys.cbd.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.cbd.model.Computer;
import com.excilys.cbd.service.ServiceComputer;

/**
 * Servlet implementation class ListServlet
 */
@WebServlet(urlPatterns = "/ListServlet")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public int page;
	public int taillePage;
	public int maxPage;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		if (request.getParameter("taillePage")!=null)
		{
			taillePage=Integer.parseInt(request.getParameter("taillePage"));
		}
		else
		{
			taillePage=10;
		}	
		try 
		{
			maxPage=ServiceComputer.getInstance().getCount()/taillePage;
			//System.out.println(taillePage);
			if (request.getParameter("page")!=null)	
			{
				//System.out.println(request.getParameter("page"));
				page=Integer.parseInt(request.getParameter("page"));
				if (page==maxPage)
				{
					ArrayList<Computer> computerList=ServiceComputer.getInstance().getComputerListPaginer(ServiceComputer.getInstance().getCount()%10,page+1);
					request.setAttribute("listComput", computerList); 
					request.setAttribute("page",page);
					request.setAttribute("maxPage", maxPage);
					request.setAttribute("taillePage", taillePage);
					request.getRequestDispatcher("views/dashboard.jsp").forward(request,response);
				}
				else
				{
					ArrayList<Computer> computerList=ServiceComputer.getInstance().getComputerListPaginer(taillePage,page*taillePage);
					request.setAttribute("listComput", computerList); 
					request.setAttribute("page",page);
					request.setAttribute("maxPage", maxPage);
					request.setAttribute("taillePage", taillePage);
					request.getRequestDispatcher("views/dashboard.jsp").forward(request,response);
				}
			}
			else
			{
				page=1;
				ArrayList<Computer> computerList=ServiceComputer.getInstance().getComputerListPaginer(taillePage,0);
				request.setAttribute("listComput", computerList);
				request.setAttribute("page",page);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("taillePage", taillePage);
				request.getRequestDispatcher("views/dashboard.jsp").forward(request,response);
			}
			
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
		doGet(request, response);
	}

}
