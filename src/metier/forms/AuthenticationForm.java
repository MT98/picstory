package metier.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class AuthenticationForm
{
	private static final String	CHAMP_LOGIN		= "login";
	private static final String	CHAMP_PASSWORD	= "password";
	private HttpServletRequest	request;
	private String				login;

	/*public AuthenticationForm(HttpServletRequest request)
	{
		this.request = request;
	}

	public Utilisateur connect() throws DaoException
	{
		login = getParamater(CHAMP_LOGIN);
		String password = getParamater(CHAMP_PASSWORD);

		List<Utilisateur> utilisateurs = UtilisateurDao.getList();
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getLogin().equals(login)
					&& utilisateur.getPassword().equals(password))
			{
				HttpSession session = request.getSession();
				session.setAttribute("utilisateur", utilisateur);
				return utilisateur;
			}
		}
		
		return null;
		
	}

	private String getParamater(String parametre)
	{
		String valeur = request.getParameter(parametre);
		valeur = valeur == null || valeur.trim().isEmpty() ? null
				: valeur.trim();
		return valeur;
	}

	public String getLogin()
	{
		return login;
	}*/
}
