package controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entities.Album;
import entities.Photo;
import service.AlbumService;
import service.PhotoService;
import service.ServiceException;

@Stateless
@WebServlet("/albums/*")
public class AlbumsController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private UtilisateurSession utilisateurSession;
	
	@EJB
	private AlbumService albumService;
	
	@EJB
	private PhotoService photoService;
	
	private Album album;
	
	//private SparqlUpdateService sparqlUpdateService;
	
	//@Inject
	//private SparqlQueryService sparqlQueryService;
	
	
	private static final String list_Album="/WEB-INF/listeAlbums.jsp";
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		String requestedUrl = request.getRequestURI();
		if (requestedUrl.endsWith("/albums/list"))
		{
			try {
				request.setAttribute("albums", getListAlbumOwnedByCurrentUser());
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
		}
		
	}
	
	private void displayPhotosAlbums(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Album album= albumService.getAlbumById(Long.parseLong(request.getParameter("id")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			request.setAttribute("photos", picturesFromAlbum(album));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NumberFormatException, ServiceException
	{
		String title = request.getParameter("title");
        String uri = request.getParameter("uri");
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
	}
	public Album getAlbum() {
		if (album==null) {
			album = new Album(utilisateurSession.getConnectedUser());
		}
		return album;
	}
	
	public String checkAlbum() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String albumId = params.get("albumId");
        
        if(albumId == null || albumId == ""){
	    	return "list-album?faces-redirect=true";
	    } else {
	        return null; // Stay on current page.
	    }
	}
	
	public String createAlbum() {		
		try {
			albumService.createAlbum(album);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return list_Album;
	}
	
	public String displayAlbum(Long id) {
		return "album?faces-redirect=true&albumId="+id;
	}
	
	public List<Album> getListAlbumOwnedByCurrentUser() throws ServiceException {
		return albumService.listAlbumOwnedBy(utilisateurSession.getConnectedUser());
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
