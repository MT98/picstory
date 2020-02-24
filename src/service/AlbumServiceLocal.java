package service;

import java.util.List;

import javax.naming.NamingException;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import entities.Album;
import entities.Utilisateur;

public interface AlbumServiceLocal {
	
	public Album createAlbum(Album album) throws ServiceException, NamingException, NotSupportedException, SystemException;
	public Album updateAlbum(Album album) throws ServiceException, NamingException;
	
	public void deleteAlbumById(Long id ) throws ServiceException, NamingException;
	
	public List<Album> getAlbums() throws ServiceException;
	
	public Album getAlbumById(Long id) throws ServiceException;
	
	public void partager(String albumId, String userId) throws ServiceException, NamingException;
	
	public List<Album> listAlbumOwnedBy(Utilisateur utilisateur);
	
	public List<Album> listAlbumSharedWith(Utilisateur utilisateur) ;
	
	public long nombrePhotoAlbum(Album album);
	public List<Album> publicAlbum();

}
