package controller;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.AlbumServiceLocal;
import service.ServiceException;

@WebServlet("/images/*")
public class StartApp extends HttpServlet {

	@Inject
	AlbumServiceLocal albumService;
	

}
