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
		//System.out.println(request.getParameter("tri")+ " "+request.getParameter("colonne"));
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
		//System.out.println(request.getParameter("search"));
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
				//System.out.println(taillePage);
				if (request.getParameter("page")!=null)	
				{
					//System.out.println(request.getParameter("page"));
					page=Integer.parseInt(request.getParameter("page"));
					if (page==maxPage)
					{
						//System.out.println("page max");
						computerList=classement(tri,colonne,ServiceComputer.getInstance().getCount()%10,page*taillePage);
					}	
					else
					{
						//System.out.println("page du milieu");
						computerList=classement(tri,colonne,taillePage,page*taillePage);
					}
				}
				else
				{
					//System.out.println("première page"+taillePage);
					page=1;
					computerList=ServiceComputer.getInstance().getComputerListPaginer(taillePage,0);
					computerList=classement(tri,colonne,taillePage,0);
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
				//System.out.println(computerList);
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
		//System.out.println(request.getParameter("page"));
		String[] listId = idAttacher.split(",");
		
		for (int i=0;i<listId.length;i++)
		{
			//System.out.println(listId[i]);
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
		//request.setAttribute("page",request.getParameter("page"));
		doGet(request, response);
	}
	public ArrayList<Computer> classement(int tri, String colonne, int limit, int offset) throws ClassNotFoundException
	{
		//System.out.println(tri +" "+ colonne);
		ArrayList<Computer> computerList=null;
		if (tri==0||colonne==null)
		{
			//System.out.println("cas 0");
			computerList=ServiceComputer.getInstance().getComputerListPaginer(limit,offset);
		}
		else
		{
			if (tri==1)
			{
				switch (colonne)
				{
				case "computName" :
					//System.out.println("cas 11");
					computerList=ServiceComputer.getInstance().getListOrderComputNameAsc(limit, offset);
					break;
				case "intro" :
					//System.out.println("cas 12");
					computerList=ServiceComputer.getInstance().getListOrderIntroAsc(limit, offset);
					break;
				case "disco" :
					//System.out.println("cas 13");
					computerList=ServiceComputer.getInstance().getListOrderDiscoAsc(limit, offset);
					break;
				case "compaName" :
					//System.out.println("cas 14");
					computerList=ServiceComputer.getInstance().getListOrderCompaNameAsc(limit, offset);
					break;
				}
			}
			else
			{
				switch (colonne)
				{
				case "computName" :
					//System.out.println("cas 21");
					computerList=ServiceComputer.getInstance().getListOrderComputNameDes(limit, offset);
					break;
				case "intro" :
					//System.out.println("cas 22");
					computerList=ServiceComputer.getInstance().getListOrderIntroDes(limit, offset);
					break;
				case "disco" :
					//System.out.println("cas 23");
					computerList=ServiceComputer.getInstance().getListOrderDiscoDes(limit, offset);
					break;
				case "compaName" :
					//System.out.println("cas 24");
					computerList=ServiceComputer.getInstance().getListOrderCompaNameDes(limit, offset);
					break;
				}
			}
		}
	return computerList;
	}
}
