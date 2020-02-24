package service;

import java.util.List;


import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import javax.annotation.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import entities.Utilisateur;


@ManagedBean
public class UtilisateurService implements UtilisateurServiceLocal {
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@PersistenceContext
	private EntityManager em;
	
	public Utilisateur createUser(Utilisateur utilisateur) throws ServiceException, NamingException, NotSupportedException, SystemException 
	{
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		transaction.begin();
		this.em.persist(utilisateur);
		try {
			transaction.commit();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicMixedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HeuristicRollbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return utilisateur;
	}
	
	public Utilisateur updateUser(Utilisateur utilisateur)	throws ServiceException
	{
		this.em.merge(utilisateur);
		return utilisateur;
	}
	
	public Utilisateur deleteUser(Utilisateur utilisateur ) throws ServiceException
	{
		
		this.em.remove(utilisateur);
		
		return utilisateur;
	}
	
	public List<Utilisateur> getUsers() throws ServiceException
	{
		Query query= this.em.createQuery("SELECT u FROM Utilisateur AS u");
		List<Utilisateur> listUsers= query.getResultList();
		return listUsers;
	}
	

	public Utilisateur getUserByEmail(String email) throws ServiceException
	{
		try {
			String query = "SELECT u FROM Utilisateur u WHERE u.email=:email";
			Query tq = this.em.createQuery(query, Utilisateur.class);
			tq.setParameter("email", email);
	
			Utilisateur utilisateur = (Utilisateur) tq.getSingleResult();
			
			return utilisateur;
			
		}catch(Exception ex)
		{
			throw new ServiceException("Echec de récupèration de l'utilisateur à travers son email!");
		}
		
	}

	
	public Utilisateur getUserById(Long id) throws ServiceException
	{
		Utilisateur utilisateur = this.em.find(Utilisateur.class, id);
		return utilisateur;
	}
	
	public Utilisateur login(String email, String password) throws ServiceException {
		Query query = this.em.createNamedQuery("Utilisateur.login");
		query.setParameter("email", email);
		query.setParameter("password", password);
		try {
			return (Utilisateur) query.getSingleResult();
		}
		catch (NoResultException e) {
			throw new ServiceException("Utilisateur Inconnu",e);
		}
	}
	
}
