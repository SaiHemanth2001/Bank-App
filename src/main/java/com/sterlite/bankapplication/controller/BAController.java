package com.sterlite.bankapplication.controller;



import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.sterlite.bankapplication.model.Customer;

import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = {"http://localhost:3000/"})
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class BAController {
	private final AuthenticationService service;


	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
		return ResponseEntity.ok(service.register(request));
	}
	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request){
		return ResponseEntity.ok(service.authenticate(request));
	}
	

	@PutMapping("/update/{id}")
	public ResponseEntity<AuthenticationResponse> update(@PathVariable("id") long id,@RequestBody RegisterRequest request){
		return ResponseEntity.ok(service.update(id,request));
	}
	
	@DeleteMapping("/delete/{id}")	
	public ResponseEntity<String> deleteById1(@PathVariable("id") long id){
		service.deleteById1(id);
		return ResponseEntity.ok("Deleted Customer");
	} 
	
	@GetMapping("/email/{email}")
	public List<Customer> getCustomers (@PathVariable String email){
		return service.getCustomersByemail(email);
	}
	
	
	
	
	
	
	
}
