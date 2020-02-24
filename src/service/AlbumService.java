package service;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entities.Album;
import entities.Utilisateur;

@ManagedBean
public class AlbumService implements AlbumServiceLocal {
	
	@PersistenceUnit
	private EntityManagerFactory emf;
	
	@PersistenceContext
	private EntityManager em;
	
	public Album createAlbum(Album album) throws ServiceException, NamingException, NotSupportedException, SystemException
	{
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		transaction.begin();
		this.em.persist(album);
		try {
			transaction.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return album;
	}
	
	public Album updateAlbum(Album album) throws ServiceException, NamingException
	{
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		try {
			transaction.begin();
		} catch (NotSupportedException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.em.merge(album);
		try {
			transaction.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return album;
	}
	
	public void deleteAlbumById(Long id ) throws ServiceException, NamingException
	{
		Album album = this.em.find(Album.class, id);
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		try {
			transaction.begin();
		} catch (NotSupportedException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.em.remove(this.em.merge(album));
		try {
			transaction.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Album> getAlbums() throws ServiceException
	{
		Query query= this.em.createQuery("SELECT a FROM Album AS a");
		List<Album> listAlbums= query.getResultList();
		return listAlbums;
	}
	
	public Album getAlbumById(Long id) throws ServiceException
	{
		Album album = this.em.find(Album.class, id);
		return album;
	}
	
	public void partager(String albumId, String userId) throws ServiceException, NamingException
	{
		Utilisateur utilisateur= this.em.find(Utilisateur.class, Long.parseLong(userId));
		Album album = this.em.find(Album.class, Long.parseLong(albumId));
		album.getPartageAvec().add(utilisateur);
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		try {
			transaction.begin();
		} catch (NotSupportedException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.em.merge(album);
		try {
			transaction.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
				| HeuristicRollbackException | SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Album> listAlbumOwnedBy(Utilisateur utilisateur)
	{
		Query query = this.em.createNamedQuery("Album.findAllOwned");
		query.setParameter("owner", utilisateur);
		return query.getResultList();
	}
	
	public List<Album> listAlbumSharedWith(Utilisateur utilisateur) {
		Query query = this.em.createNamedQuery("Album.findAlbumSharedWith");
		query.setParameter("sharedWith", utilisateur);
		return query.getResultList();
	}

	@Override
	public long nombrePhotoAlbum(Album album) {
		
		Query query = this.em.createQuery("SELECT COUNT(p) FROM Photo p WHERE p.album =:album");
		query.setParameter("album", album);
		long nombre= (long) query.getSingleResult();
		return nombre;
	}

}
