package service;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
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
	
	public void create(Photo photo) throws NamingException  {
		Album album = photo.getAlbum();
		album.setOwner(this.em.merge(this.em.merge( album.getProprietaire())));
		photo.setAlbum(this.em.merge(this.em.merge(album)));
		UserTransaction transaction = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
		this.em.persist(photo);
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
	
	public void deletePhotoById(Long id) throws ServiceException {
		Photo photo = this.em.find(Photo.class, id);
		this.em.remove(photo);		
		// sparqlDeleteService.deletePicture(picture);
	}
	
	public void deletePhoto(Photo photo) {
		
		this.em.remove(photo);		
		// sparqlDeleteService.deletePicture(picture);
	}

}
