package service;

import java.util.List;

import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import entities.Utilisateur;

public interface UtilisateurServiceLocal {
	
	public Utilisateur createUser(Utilisateur utilisateur) throws ServiceException, NamingException, NotSupportedException, SystemException;
	
	public Utilisateur updateUser(Utilisateur utilisateur)	throws ServiceException;
	
	public Utilisateur deleteUser(Utilisateur utilisateur ) throws ServiceException;
	
	public List<Utilisateur> getUsers() throws ServiceException;
	
	public Utilisateur getUserById(Long id) throws ServiceException;
	
	public Utilisateur login(String email, String password) throws ServiceException ;
}
