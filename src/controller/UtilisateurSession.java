package controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Utilisateur;
import service.AlbumService;
import service.ServiceException;
import service.UtilisateurService;


public class UtilisateurSession implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UtilisateurService utilisateurService;
	
	@Inject
	private AlbumService albumService;
	
	private String email;
	private String password;
	
	private Utilisateur connectedUser;
	
	public void login(HttpServletRequest request, HttpServletResponse response)
		    throws SQLException, IOException, ServiceException {
		
		try {
			connectedUser = utilisateurService.login(email, password);
		} catch (ServiceException e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("Email ou mot de passe incorrects");
			// ajoute le message pour toute la page (1er param==null)
			facesContext.addMessage(null, facesMessage);
			response.sendRedirect("/login");
		}
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		session.setAttribute("utilisateurSession",this);
		
		response.sendRedirect("/albums/list");	
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServiceException {
		FacesContext context = FacesContext.getCurrentInstance();
		((HttpSession) context.getExternalContext().getSession(false)).invalidate();
		connectedUser=null;
		response.sendRedirect("/login");
	}
	
	public String homeRedirect(){
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		if(connectedUser != null && session != null){
			return "index.jsp?faces-redirect=true";
		}
		return null;
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
