package service;

import java.util.List;

import javax.naming.NamingException;

import entities.Album;
import entities.Photo;
import entities.Utilisateur;

public interface PhotoServiceLocal {
	
	public void create(Photo photo) throws NamingException;
	
	public Photo getPictureById(Long id) ;
	public Photo getPictureByURI(String uri);
	
	public List<Photo> listPhotoFromListURI(List<String> listURI);
	
	public List<Photo> listPhotoFromAlbum(Album a);
	
	public List<Photo> listPhotosOwnedBy(Utilisateur utilisateur) ;	
	public void deletePhotoById(Long id) throws ServiceException;
	public void deletePhoto(Photo photo) ;

}
