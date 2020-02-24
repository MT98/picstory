package controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Base64;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import entities.Album;
import entities.Photo;
import service.AlbumServiceLocal;
import service.PhotoServiceLocal;
import service.ServiceException;


@WebServlet("/images/*")
@MultipartConfig
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	PhotoServiceLocal photoService;
	
	@Inject
	AlbumServiceLocal albumService;
	
	private static final String AJOUT_PHOTO="/WEB-INF/addPhoto.jsp";
	public ImageController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		request.setCharacterEncoding("utf-8");
		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("/images/add"))
		{
			try {
				request.setAttribute("albums", albumService.getAlbums());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			getServletContext().getRequestDispatcher(AJOUT_PHOTO)
					.forward(request, response);
		}else if(requestedUrl.endsWith("/images/delete"))
		{
			try {
				deleteImage(request, response);
			} catch (NumberFormatException | IOException | ServiceException | ServletException | NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
		{
			response.sendRedirect(request.getContextPath());
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String requestedUrl = request.getRequestURI();

		if (requestedUrl.endsWith("/images/add"))
		{
			try {
				uploadPhoto(request, response);
			} catch (NumberFormatException | IOException | ServletException | ServiceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void uploadPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, NumberFormatException, ServiceException
	{
		String title = request.getParameter("title");
        
        Album album= albumService.getAlbumById(Long.parseLong(request.getParameter("album")));
        Part filePart = request.getPart("photo");
        /*String fileName = extractFileName(filePart);
        filePart.write(fileName);
        File file = new File(fileName);
        byte[] bFile = new byte[(int) file.length()];
        
        try {
	     FileInputStream fileInputStream = new FileInputStream(file);
	     //convert file into array of bytes
	     fileInputStream.read(bFile);
	     fileInputStream.close();
        } catch (Exception e) {
	     e.printStackTrace();
        }*/
       InputStream inputStream = null; // input stream of the upload file
       

        String message = null;
        // obtains the upload file part in this multipart request
       
        if (filePart != null) {
            // prints out some information for debugging
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            // obtains input stream of the upload file
            inputStream = filePart.getInputStream();
        }
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        
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
        response.sendRedirect(request.getContextPath() + "/albums/list");	
	}
	
	
	private void deleteImage(HttpServletRequest request, HttpServletResponse response)
		    throws IOException, NumberFormatException, ServiceException, ServletException, NamingException {
		 
		photoService.deletePhotoById(Long.parseLong(request.getParameter("id")));
		response.sendRedirect(request.getContextPath() + "/albums/list");

	}

}
