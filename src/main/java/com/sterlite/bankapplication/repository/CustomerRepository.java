package com.sterlite.bankapplication.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.sterlite.bankapplication.model.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long>{
	
	Optional<Customer> findByEmail(String email);
	
	@Query(value=" SELECT * FROM Customers e where e.email = ?1", nativeQuery = true)
	List<Customer> findByCustomerEmail(String email);

}
