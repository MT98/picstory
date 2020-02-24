package controller;

import java.io.IOException;

import java.sql.SQLException;

import javax.annotation.ManagedBean;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Utilisateur;

import service.AlbumServiceLocal;
import service.ServiceException;

import service.UtilisateurServiceLocal;

@ManagedBean
public class UtilisateurSession implements UtilisateurSessionLocal {
	
	@Inject
	UtilisateurServiceLocal utilisateurService;
	
	@Inject
	AlbumServiceLocal albumService;
	
	private String email;
	private String password;
	
	private Utilisateur connectedUser;
	
	public void login(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServiceException, ServletException {
		
		try {
			setEmail(request.getParameter("email"));
			setPassword(request.getParameter("password"));
			connectedUser = utilisateurService.login(this.getEmail(), this.getPassword());
		} catch (ServiceException e) {
			
			response.sendRedirect("login");
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("utilisateurSession",this);
		System.out.println(this.getEmail()+ this.getPassword());		
		 response.sendRedirect(request.getContextPath() + "/albums/list");	
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServiceException {
		
		
		connectedUser=null;
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("/login");
	}
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public  Utilisateur getConnectedUser() {
		return connectedUser;
	}
	
	public void setConnectedUser(Utilisateur utilisateur) {
		this.connectedUser = utilisateur;
	}

}
