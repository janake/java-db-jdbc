package hu.lsi.adatbazis.jpa.derby.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Post {

	@Id
	@GeneratedValue
	private Long id;
	
//	@ManyToAny - egy felhasznalonak lehet tobb treadje
//	@ManyToMany - tobb-tobb
//	@OneToMany - egy treadnek tobb usere lehet
//	@OneToOne - egy-egy
	@ManyToOne
	private User user;
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	private String content;
	
	public Post() {
		super();
	}
	
	public Post(Long id, User user,String content, LocalDate date) {
		this.id = id;
		this.user = user;
		this.content = content;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	private LocalDate date;
	
}
