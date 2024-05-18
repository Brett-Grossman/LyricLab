package com.lyriclab.models;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Songs")

public class Lyric {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Please enter the song title.")
	@Size(min=1,max=30,message="the title of the song has to be between 1 and 30 characters.")
	//There is a song called "A" by Prince or "E" by Matt Mason, 
	//meaning it can be as small as one letter/number.
	private String song_name; 
	
	@NotEmpty(message="What is the genre of the song?")
	@Size(min=3,max=20,message="The genre has to be between 3 and 20 characters.")
	private String genre;
	
	@NotEmpty(message="Lyrics required.")
	@Size(min=5,max=300,message="Lyrics must be at least 5 characters.")
	private String songLyrics;
	
	@Column(updatable=true)
	@Min(0)
	private int UpdateCount;
		
	@Column(updatable=false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date created_at;
	
	@Column(updatable=true)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updated_at;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="account_id")
	private Account accounts;
	
	public Lyric() {}

	public Lyric(			
			@NotEmpty(message="Please enter the song title.")
			@Size(min=1,max=30,message="the title of the song has to be between 1 and 30 characters.") String song_name,
			@NotEmpty(message="What is the genre of this song?")
			@Size(min=3,max=20,message="The genre has to be between 3 and 20 characters.") String genre,
			@NotEmpty(message="Lyrics required.")
			@Size(min=5,max=300,message="Lyrics must be at least 5 characters.") String songLyrics) {
		
		super();
		this.song_name = song_name;
		this.genre = genre;
		this.songLyrics = songLyrics;	
	}
    @Transient
    private String user_name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getSongLyrics() {
		return songLyrics;
	}

	public void setSongLyrics(String songLyrics) {
		this.songLyrics = songLyrics;
	}

	public int getUpdateCount() {
		return UpdateCount;
	}

	public void setUpdateCount(int updateCount) {
		UpdateCount = updateCount;
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

	public Account getAccounts() {
		return accounts;
	}

	public void setAccounts(Account accounts) {
		this.accounts = accounts;
	}
    public String getUser_name() {
        if (accounts != null) {
            return accounts.getAccount_name();
        }
        return null;
    }
	
	
}
