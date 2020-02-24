package service;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import entities.Album;
import entities.Photo;
import entities.Utilisateur;
import utils.JpaUtils;

@ManagedBean
public class PhotoService implements PhotoServiceLocal {
	
	
	
	private JpaUtils jpaUtils=new JpaUtils();
	@PersistenceContext
	private EntityManager em=jpaUtils.getEm() ;
	
	@Inject
	UtilisateurServiceLocal utilisateurService;
	
	public void create(Photo photo) throws NamingException  {
		//Album album = photo.getAlbum();
		//album.setOwner();
		//photo.setAlbum(this.em.merge(this.em.merge(album)));
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		try {
			transaction.begin();
		} catch (NotSupportedException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.em.persist(photo);
		try 
		{
			transaction.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
					| HeuristicRollbackException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	
		//sparqlUpdateService.insertPicture(p);
	}
	
	public Photo getPictureById(Long id) {
		Photo photo = this.em.find(Photo.class, id);
		this.em.refresh(photo);
		
		return photo;
	}
	
	public Photo getPictureByURI(String uri) {
		Query query = this.em.createNamedQuery("Photo.findPhotoByURI");
		query.setParameter("uri", this.em.merge(uri));
		return (Photo) query.getSingleResult();
	}
	
	public List<Photo> listPhotoFromListURI(List<String> listURI) {
		Query query = this.em.createNamedQuery("Photo.findPhotoByListUri");
		query.setParameter("uri", listURI);
		return query.getResultList();
	}
	
	
	public List<Photo> listPhotoFromAlbum(Album a){
		System.out.println(a.getId());
		Query query = this.em.createNamedQuery("Photo.findAllPhotosFromAlbum");
		query.setParameter("album", a);
		return query.getResultList();
	}
	
	public List<Photo> listPhotosOwnedBy(Utilisateur utilisateur)  {
		Query query = this.em.createNamedQuery("Picture.findAllOwned");
		query.setParameter("proprietaire", utilisateur);
		List<Photo> results = query.getResultList();
		return results;
	}
	
	public void deletePhotoById(Long id) throws ServiceException, NamingException {
		Photo photo = this.em.find(Photo.class, id);
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		try {
			transaction.begin();
		} catch (NotSupportedException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Photo photo1=this.em.merge(photo);
		this.em.remove(photo1);		
		
		try 
		{
			transaction.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
					| HeuristicRollbackException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		
	}
	
	public void deletePhoto(Photo photo) throws NamingException {
		
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		try {
			transaction.begin();
		} catch (NotSupportedException | SystemException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.em.remove(photo);
		try 
		{
			transaction.commit();
		} catch (SecurityException | IllegalStateException | RollbackException | HeuristicMixedException
					| HeuristicRollbackException | SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		// sparqlDeleteService.deletePicture(picture);
	}

}
