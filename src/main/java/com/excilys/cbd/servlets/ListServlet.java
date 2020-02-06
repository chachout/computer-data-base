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
public class ListServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public int page;
	public int taillePage;
	public int maxPage;
	public int totalComputer;
	public String colonne;
	public int tri;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListServlet() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		ArrayList<Computer> computerList=null;
		if (request.getParameter("tri")==null)
		{
			tri=0;
		}
		else
		{
			tri=Integer.parseInt(request.getParameter("tri"));
		}
		tri%=3;
		colonne=request.getParameter("colonne");
		if (request.getParameter("search")==null||request.getParameter("search")=="")
		{
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
				totalComputer=ServiceComputer.getInstance().getCount();
				maxPage=totalComputer/taillePage;
				if (request.getParameter("page")!=null)	
				{
					page=Integer.parseInt(request.getParameter("page"));
					if (page==maxPage)
					{
						computerList=ServiceComputer.getInstance().getComputerListPaginer(tri,colonne,ServiceComputer.getInstance().getCount()%10,page*taillePage);
					}
					else
					{
						computerList=ServiceComputer.getInstance().getComputerListPaginer(tri,colonne,taillePage,page*taillePage);
					}
				}
				else
				{
					page=1;
					computerList=ServiceComputer.getInstance().getComputerListPaginer(tri,colonne,taillePage,0);
				}
				request.setAttribute("page",page);
				request.setAttribute("maxPage", maxPage);
				request.setAttribute("taillePage", taillePage);
			}
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			String search = request.getParameter("search");
			try 
			{
				computerList=ServiceComputer.getInstance().findComputerByName(search);
				totalComputer=computerList.size();
			} 
			catch (ClassNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		request.setAttribute("totalComputer", totalComputer);
		request.setAttribute("listComput", computerList);
		request.setAttribute("tri", tri);
		request.getRequestDispatcher("views/dashboard.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String idAttacher = request.getParameter("selection");
		String[] listId = idAttacher.split(",");
		
		for (int i=0;i<listId.length;i++)
		{
			try 
			{
				ServiceComputer.getInstance().deleteComputer(Long.parseLong(listId[i]));
			} 
			catch (NumberFormatException | ClassNotFoundException e) 
			{	
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}
}
