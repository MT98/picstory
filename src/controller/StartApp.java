package controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AlbumServiceLocal;
import service.ServiceException;

@WebServlet("/accueil")
public class StartApp extends HttpServlet {

	@Inject
	AlbumServiceLocal albumService;
	private static final String START="/WEB-INF/accueil.jsp";
	public void init() throws ServletException
	{
		
	}
	

	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("albums", albumService.publicAlbum());
		getServletContext().getRequestDispatcher(START)
		.forward(request, response);
	}
}
