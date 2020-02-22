package controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entities.Utilisateur;
import service.ServiceException;


public interface UtilisateurSessionLocal {
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServiceException;
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServiceException;
	
	public String homeRedirect();
	
	public String getEmail() ;

	public void setEmail(String email) ;

	public String getPassword();

	public void setPassword(String password);
	
	public  Utilisateur getConnectedUser();
	
	public void setConnectedUser(Utilisateur utilisateur);

}
