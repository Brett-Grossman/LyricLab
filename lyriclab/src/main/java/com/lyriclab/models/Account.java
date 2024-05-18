package com.lyriclab.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="accounts")

public class Account {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Username cannot be blank!")
	@Size(min=3,max=30,message="Username needs to be between 3 and 30 charaters.")
	private String account_name;
	
	@NotEmpty(message="Please enter an email.")
	@Email(message="This is not a valid email.")
	private String email;
	
	@NotEmpty(message="Enter a Password.")
	@Size(min=8,max=100,message="Your password needs to be between 8 and 45 charaters.")
	private String password;
	
	@Transient
	@NotEmpty(message="Please confirm your password")
	@Size(min=8,max=100,message="Your confirmation password needs to be between 8 and 45 charaters.")
	private String confirmation;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;
	
	@Column(updatable=true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated_at;
	
	@OneToMany(mappedBy = "accounts", fetch = FetchType.LAZY)
	private List<Lyric> Lyrics;
	
	public Account() {}

	

	public Account(
			@NotEmpty(message = "Username cannot be blank!")
			@Size(min = 3, max = 30, message = "Username needs to be between 3 and 30 charaters.") String account_name,
			@NotEmpty(message = "Please enter an email.") 
			@Email(message = "This is not a valid email.") String email,
			@NotEmpty(message = "Enter a Password.") 
			@Size(min = 8, max = 100, message = "Your password needs to be between 8 and 45 charaters.") String password,
			@NotEmpty(message = "Please confirm your password") 
			@Size(min = 8, max = 100, message = "Your confirmation password needs to be between 8 and 45 charaters.") String confirmation) {
		super();
		this.account_name = account_name;
		this.email = email;
		this.password = password;
		this.confirmation = confirmation;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAccount_name() {
		return account_name;
	}


	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getConfirmation() {
		return confirmation;
	}


	public void setConfirmation(String confirmation) {
		this.confirmation = confirmation;
	}


	public Date getCreated_at() {
		return created_at;
	}


	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}


	public Date getUpdated_at() {
		return updated_at;
	}


	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}
	@PrePersist
	protected void onCreation() {
		this.created_at=new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updated_at= new Date();
	}
	
}