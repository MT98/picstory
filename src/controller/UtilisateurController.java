package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ServiceException;
import service.UtilisateurService;
import service.UtilisateurServiceLocal;

@WebServlet("/user/*")
public class UtilisateurController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String	VUE_AJOUT_UTILISATEUR = "/WEB-INF/addUser.jsp";
	private static final String				VUE_UPDATE_UTILISATEUR	= "/WEB-INF/modifierUtilisateur.jsp";

	private static final String				VUE_LIST_USER	= "/WEB-INF/listUsers.jsp";

	@Inject
	UtilisateurServiceLocal utilisateurService;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String requestedUrl = request.getRequestURI();
		if (requestedUrl.endsWith("/user/add"))
		{
			getServletContext().getRequestDispatcher(VUE_AJOUT_UTILISATEUR)
					.forward(request, response);
		}
		else if (requestedUrl.endsWith("/user/list"))
		{
			HttpSession session = request.getSession();
			try {
				request.setAttribute("users", utilisateurService.getUsers());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher(VUE_LIST_USER)
					.forward(request, response);
			
		}
		else if (requestedUrl.endsWith("/user/update"))
		{
			/*try {
				//afficherFormModif(request, response);
			}catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		else if (requestedUrl.endsWith("/user/delete"))
		{
			/*try {
				//deleteUser(request, response);
			}  catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		else
		{
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		String requestedUrl = request.getRequestURI();

		/*if (requestedUrl.endsWith("/clients/add"))
		{
			AjoutUtilisateurForm form = new AjoutUtilisateurForm(request);
			Client client = form.getUtilisateur();

			if (form.isValid())
			{
				try {
					ClientDao.ajouter(client);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			request.setAttribute("client", client);
			request.setAttribute("messageErreurs", form.getMessageErreurs());
			request.setAttribute("statusMessage", form.getStatusMessage());

			getServletContext().getRequestDispatcher(VUE_AJOUT_CLIENT)
					.forward(request, response);
		}
		else if(requestedUrl.endsWith("/clients/update"))
		{
			try {
				updateClient(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}
	
	

	private void deleteUser(HttpServletRequest request, HttpServletResponse response)
		   throws IOException {
		/* int id = Integer.parseInt(request.getParameter("id"));
		 try {
			ClientDao.deleteClient(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 response.sendRedirect("list");*/

	}
	

}
