package com.lyriclab.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lyriclab.models.Lyric;

@Repository
public interface lyricRepository extends CrudRepository<Lyric, Long>{
	
	List<Lyric> findAll();



}
