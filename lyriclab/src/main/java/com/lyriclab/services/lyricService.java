package com.lyriclab.services;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyriclab.models.Lyric;
import com.lyriclab.repositories.lyricRepository;

@Service
public class lyricService {

	@Autowired
	private lyricRepository repo;
	
    public Lyric findById(Long id) {
        Optional<Lyric> result = repo.findById(id);
        if (result.isPresent()) {
            Lyric lyric = result.get();
            return lyric;
        }
        return null;
    }
	

	public List<Lyric> all() {
		return repo.findAll();
	}
	
	public Lyric create(Lyric lyric) {
		return repo.save(lyric);
	}
	public void delete(Long id) {
	    repo.deleteById(id);
	}
	public Lyric update(Lyric lyric) {

	    Lyric existingLyric = repo.findById(lyric.getId()).orElse(null);

	    if (existingLyric != null) {

	        existingLyric.setSong_name(lyric.getSong_name());
	        existingLyric.setGenre(lyric.getGenre());
	        existingLyric.setSongLyrics(lyric.getSongLyrics());

	        existingLyric.setUpdateCount(existingLyric.getUpdateCount() + 1);
	        
	        return repo.save(existingLyric);
	    }

	    return null;
	}
}
