package com.lyriclab.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lyriclab.models.Account;

@Repository

public interface AccountRepository extends CrudRepository<Account, Long>{
	
	Optional<Account> findByEmail(String email);
}