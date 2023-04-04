package com.sterlite.bankapplication.demo;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sterlite.bankapplication.controller.AuthenticationService;
import com.sterlite.bankapplication.model.Account_Details;
import com.sterlite.bankapplication.model.Customer;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/api/v1/demo")
@RequiredArgsConstructor
public class DemoController {
	
	private final AuthenticationService service ;
	

	
	@GetMapping(path ="getCustomer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id){
		return ResponseEntity.ok(service.getCustomerById(id));
	}
	
	@GetMapping("accDetails/{id}")
	public ResponseEntity<Account_Details> accountDetails(@PathVariable("id")long id){
		return ResponseEntity.ok(service.getAccountDetailsById(id));
	}
	
	@GetMapping("/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<Customer> getAllCustomers1(){
		return service.getAllCustomers();
	}
	
	

}
