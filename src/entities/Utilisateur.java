package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@NamedQueries({
    @NamedQuery(name="Utilisateur.findAll",
                query="SELECT u FROM Utilisateur u"),
    @NamedQuery(name="Utilisateur.login",
    query="SELECT u FROM Utilisateur u WHERE u.email=:email AND u.password=:password")
})
@Table(uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class Utilisateur implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp="^[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,64}$", message="{utilisateur.email.regex}")
	@NotNull
	private String email;
	
	@NotNull
	private String lastname;
	
	@NotNull(message="{utilisateur.firstname.notnull}")
	private String firstname;
	
	@NotNull
	private String password;
	
	@OneToMany(mappedBy="proprietaire", cascade=CascadeType.ALL)
	private List<Album> userAlbums;
	
	public Utilisateur ()
	{
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Album> getUserAlbums() {
		return userAlbums;
	}

	public void setUserAlbums(List<Album> userAlbums) {
		this.userAlbums = userAlbums;
	}
	

}
