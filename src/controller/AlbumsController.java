package controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entities.Album;
import entities.Photo;
import entities.Utilisateur;
import service.AlbumServiceLocal;
import service.PhotoServiceLocal;
import service.ServiceException;
import service.UtilisateurServiceLocal;

@WebServlet("/albums/*")
public class AlbumsController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UtilisateurSession utilisateurSession;
	
	@Inject
	AlbumServiceLocal albumService;
	
	@Inject
	PhotoServiceLocal photoService;
	
	@Inject
	UtilisateurServiceLocal utilisateurService;
	
	private Album album;
	
	//private SparqlUpdateService sparqlUpdateService;
	
	//@Inject
	//private SparqlQueryService sparqlQueryService;
	
	
	private static final String list_Album="/WEB-INF/listeAlbums.jsp";
	private static final String ADD_ALBUM="/WEB-INF/addAlbum.jsp";
	private static final String DISPLAY_ALBUM="/WEB-INF/detailsAlbum.jsp";
	private static final String SHARE_ALBUM="/WEB-INF/shareAlbum.jsp";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String requestedUrl = request.getRequestURI();
		utilisateurSession= (UtilisateurSession) request.getSession().getAttribute("utilisateurSession");
		if (requestedUrl.endsWith("/albums/list"))
		{
			try {
				request.setAttribute("albums", getListAlbumOwnedByCurrentUser(utilisateurSession));
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			getServletContext().getRequestDispatcher(list_Album)
					.forward(request, response);
		}else if (requestedUrl.endsWith("/albums/view"))
		{
			try {
				displayPhotosAlbums(request, response);
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (requestedUrl.endsWith("/albums/add"))
		{
			getServletContext().getRequestDispatcher(ADD_ALBUM)
			.forward(request, response);
		}else if (requestedUrl.endsWith("/albums/delete"))
		{
			try {
				deleteAlbum(request, response);
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (requestedUrl.endsWith("/albums/share"))
		{
			try {
				vuePartage(request,response);
			} catch (NumberFormatException | ServiceException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (requestedUrl.endsWith("/albums/shareWith"))
		{
			/*try {
				request.setAttribute("albums", albumService.listAlbumSharedWith(""));
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			getServletContext().getRequestDispatcher(list_Album)*/
		}
		else
		{
			response.sendRedirect(request.getContextPath());
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("utf-8");
		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("/albums/add"))
		{
			createAlbum(request, response);
		}else if (requestedUrl.endsWith("/albums/share"))
		{
			try {
				albumService.partager(request.getParameter("albumId"), request.getParameter("userId"));
			} catch (NumberFormatException | ServiceException | NamingException  e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.sendRedirect("list");
		}
	}
	
	private void vuePartage(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ServiceException, ServletException, IOException {
		
		Album album = albumService.getAlbumById(Long.parseLong(request.getParameter("id")));
		RequestDispatcher dispatcher = request.getRequestDispatcher(SHARE_ALBUM);
		request.setAttribute("album", album);
		request.setAttribute("utilisateurs", utilisateurService.getUsers());	
		dispatcher.forward(request, response);
		
	}

	private void displayPhotosAlbums(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException, NumberFormatException, ServiceException 
	{
			
		Album album= albumService.getAlbumById(Long.parseLong(request.getParameter("id")));
		
		try {
			request.setAttribute("photos", picturesFromAlbum(album));
			request.setAttribute("album", album);
			request.setAttribute("nombrePhoto", albumService.nombrePhotoAlbum(album));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher(DISPLAY_ALBUM)
		.forward(request, response);
		
	}

	/*public void uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NumberFormatException, ServiceException
	{
		String title = request.getParameter("title");
        
        Album album= albumService.getAlbumById(Long.parseLong(request.getParameter("id")));
        InputStream inputStream = null; // input stream of the upload file

        String message = null;
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("photo");
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        ByteArrayOutputStream outputStream=null;
        
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        byte[] imageBytes = outputStream.toByteArray();
        String uri=Base64.getEncoder().encodeToString(imageBytes);
        Photo photo=new Photo(album, title, uri, imageBytes);
        inputStream.close();
        outputStream.close();
        try {
			photoService.create(photo);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}*/
	
	public void deleteAlbum(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ServiceException, NamingException, IOException
	{
		albumService.deleteAlbumById(Long.parseLong(request.getParameter("id")));
		response.sendRedirect("list");
	}
	public Album getAlbum() {
		if (album==null) {
			album = new Album(utilisateurSession.getConnectedUser());
		}
		return album;
	}
	

	
	public void createAlbum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		String titre= String.valueOf(request.getParameter("titre"));
		String description= String.valueOf(request.getParameter("description"));
		utilisateurSession= (UtilisateurSession) request.getSession().getAttribute("utilisateurSession");
		Album album= new Album(titre,description,utilisateurSession.getConnectedUser());
		try {
			albumService.createAlbum(album);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher(list_Album)
				.forward(request, response);
		/*try {
			albumService.createAlbum(album);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list_Album;*/
	}
	
	public String displayAlbum(Long id) {
		return "album?faces-redirect=true&albumId="+id;
	}
	
	public List<Album> getListAlbumOwnedByCurrentUser(UtilisateurSession utilisateurSession) throws ServiceException {
		
		if(utilisateurSession !=null)
			return albumService.listAlbumOwnedBy(utilisateurSession.getConnectedUser());
		return null;
	}
	
	public List<Photo> getListPictureFromAlbum() throws ServiceException {
		if(album.getId() != 0){
			List<Photo> listPictures = photoService.listPhotoFromAlbum(album);
			return listPictures;
		}
		return null;
	}
	
	public List<Photo> picturesFromAlbum(Album album) throws ServiceException {
		if(album.getId() != 0){
			List<Photo> listPictures = photoService.listPhotoFromAlbum(album);
			return listPictures;
		}
		return null;
	}

}
