package com.sterlite.bankapplication.controller;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sterlite.bankapplication.config.JwtService;
import com.sterlite.bankapplication.model.Account_Details;
import com.sterlite.bankapplication.model.Customer;
import com.sterlite.bankapplication.model.Role;
import com.sterlite.bankapplication.repository.Account_DetailsRepository;
import com.sterlite.bankapplication.repository.CustomerRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	private final CustomerRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final Account_DetailsRepository aRepository;
	
	public AuthenticationResponse register(RegisterRequest request) {
		
		var customer = Customer.builder()
				.accountNumber(request.getAccountNumber())
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.ifsc(request.getIfsc())
				.amount(request.getAmount())
				.role(Role.USER)
				.build();
		
		repository.save(customer);
		var accountDetails =Account_Details.builder()
				.accountNumber(request.getAccountNumber())
				.ifsc(request.getIfsc())
				.build();
		aRepository.save(accountDetails);
		var jwtToken  = jwtService.generateToken(customer);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}
	public String authenticate(AuthenticationRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getEmail(),
						request.getPassword()
					)
				);
		var customer = repository.findByEmail(request.getEmail())
				.orElseThrow();
		var jwtToken  = jwtService.generateToken(customer);
		
		return jwtToken;}
	
	 
	public Account_Details getAccountDetailsById(long id) {	
		
		return aRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Customer not found"));
	}
	
	public List<Customer> getAllCustomers() {
		return repository.findAll();
	}
	
	public Customer getCustomerById(long id) {
		return repository.findById(id).orElseThrow(()->new UsernameNotFoundException("Customer not found"));
	}
	public AuthenticationResponse update(long id,RegisterRequest request) {
		var customer = repository.findById(id).orElseThrow(()->new UsernameNotFoundException("Customer not found"));
		customer.setAccountNumber(request.getAccountNumber());
		customer.setFirstName(request.getFirstName());
		customer.setLastName(request.getLastName());
		customer.setEmail(request.getEmail());
		customer.setPassword(passwordEncoder.encode(request.getPassword()));
		customer.setIfsc(request.getIfsc());
		customer.setAmount(request.getAmount());
		customer.setRole(Role.USER);
		repository.save(customer);
		var accountDetails = aRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Customer not found"));
		accountDetails.setAccountNumber(request.getAccountNumber());
		accountDetails.setIfsc(request.getIfsc());
		
		aRepository.save(accountDetails);
		
		
		var jwtToken  = jwtService.generateToken(customer);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
		
	}
	public void deleteById1(long id) {
		repository.findById(id).orElseThrow(()->new UsernameNotFoundException("Customer not found"));
		aRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("Customer not found"));
		repository.deleteById(id);
		aRepository.deleteById(id);
		
	}
	
	List<Customer> getCustomersByemail( String email) {
		return repository.findByCustomerEmail(email);
	}
	

}
