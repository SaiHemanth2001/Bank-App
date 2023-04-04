package com.sterlite.bankapplication.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.sterlite.bankapplication.model.Account_Details;


public interface Account_DetailsRepository extends JpaRepository<Account_Details,Long>{
	
	
}
